package com.rbtsb.controller;

import com.rbtsb.config.AuthenticationRequest;
import com.rbtsb.config.AuthenticationResponse;
import com.rbtsb.config.JwtUtil;
import com.rbtsb.config.MyUserDetailsService;
import com.rbtsb.entities.*;
import com.rbtsb.model.RedisObject;
import com.rbtsb.repository.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/api")
@Log4j2
public class SessionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ActionRepository actionRepository;
    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private AccountProxy accountProxy;

//    @RequestMapping(value = "/current", method = RequestMethod.GET)
//    public ResponseEntity<?> accounts(Principal principal, HttpServletRequest request) throws Exception {
//        log.debug("REST request to get current Account : {}", principal.getName());
//        Account account = accountProxy.getAccountByUserName(principal.getName());
//        return new ResponseEntity<>(account, HttpStatus.OK);
//    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            log.debug("createAuthenticationToken for username-- {}", authenticationRequest.getUsername());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (Exception ex) {
            log.error("Error in createAuthenticationToken--{} ", ex.getMessage());
//            return new ResponseEntity<>(messageSource.getMessage("login.bad.credentials", null, null),
//                    HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<String>("Invalid username or password.", HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        /**
         * Remove the existing token form the redis for the user.
         */
        String redisToken = redisRepository.findById(userDetails.getUsername());
        log.info("Existing Token in Redis for username " + userDetails.getUsername() + " is --" + redisToken);
        if (redisToken != null) {
            redisRepository.delete(userDetails.getUsername());
        }
        /**
         * Generate a new token and store in Redis for the user
         */
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        log.info("Generated ne JWT Token for username " + userDetails.getUsername() + " is --" + jwt);
        RedisObject redisObject = new RedisObject(userDetails.getUsername(), jwt);
        redisRepository.add(redisObject);
        log.info("saving the Token in Redis for username " + userDetails.getUsername() + " is --" + jwt);
/*
        Map<Object, Object> aa = redisRepository.findAllObjects();
        for (Map.Entry<Object, Object> entry : aa.entrySet()) {
            String key = (String) entry.getKey();
            log.info("KEY--" + key);
            log.info(" aa.get(key).toString()--" + aa.get(key).toString());
        }
        String redisObject2 = redisRepository.findById(userDetails.getUsername());
        log.info("findById--" + redisObject2);
*/
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("token-test")
    public ResponseEntity<?> tokenTest(@RequestBody String token) {
        ResponseEntity<?> account = null;
        try {
            log.info("validating the Token--" + token);
            String username = jwtTokenUtil.extractUsername(token);
            log.info("Extracting the username from token--" + username);
            account = accountProxy.getAccountByUserName(username);
            return new ResponseEntity<>(account.getBody(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error inside the tokenTest--" + e);
        }
        return null;
    }


    @RequestMapping(value = "/validate-token", method = RequestMethod.POST)
    public ResponseEntity<?> validateToken(@RequestBody String token) throws Exception {

        try {
            log.info("validating the Token--" + token);
            String username = jwtTokenUtil.extractUsername(token);
            log.info("Extracting the username from token--" + username);
            /**
             * Find the token in redis
             */
            String redisToken = redisRepository.findById(username);
            if (!redisToken.equals(token)) {
                log.info("Token not match with Redis Token--" + token);
                return new ResponseEntity<String>("Invalid Token.", HttpStatus.UNAUTHORIZED);
            } else {
                log.info("Token match with Redis Token--" + token);
            }
            /**
             * Load userDetails by username
             */
            final UserDetails userDetails = this.userDetailsService
                    .loadUserByUsername(username);
            log.info("User Details--{}", userDetails);
            /**
             * If username match and token not expired then token is valid.
             */

            if (username.equals(userDetails.getUsername()) && !jwtTokenUtil.isTokenExpired(token)) {
                log.info("Token is valid-- " + token);
                /*
                ResponseEntity<?> account = accountProxy.getAccountByUserName(username);
                return new ResponseEntity<>(account.getBody(), HttpStatus.OK);*/

                RestTemplate restTemplate = new RestTemplate();
//                String uri = "http://tng-crs-masterdata/master-data/user-management/accounts/find-by-username/" + username;
                //String uri = "http://dev.rbtsb.ml/master-data/user-management/accounts/find-by-username/" + username;
                String uri = "http://192.168.1.240:9001/master-data/user-management/accounts/find-by-username/" + username;
                Account result = restTemplate.getForObject(uri, Account.class);
                return new ResponseEntity<>(result, HttpStatus.OK);

                /*return accountRepository.findByUsernameReturnOptional(username)
                        .map(account -> {
                            account.setAuthorities(getAuthorities(account));
                            return new ResponseEntity<>(account, HttpStatus.OK);
                        })
                        .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
                    */
//                return ResponseEntity.ok(true);
            } else {
                //find redis token based on the username
                if (redisToken.equals(token)) {
                    redisRepository.delete(username);
                }
                log.info("Token is not valid-- " + token);
//                return new ResponseEntity<>(messageSource.getMessage("login.bad.credentials", null, null),
//                        HttpStatus.UNAUTHORIZED);
                return new ResponseEntity<String>("Invalid Token.", HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception ex) {
            log.error("Error in validate-token--{} ", ex.getMessage());
//            return new ResponseEntity<>(messageSource.getMessage("login.bad.credentials", null, null),
//                    HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<String>("Invalid Token.", HttpStatus.UNAUTHORIZED);

        }
    }

    public List<String> getAuthorities(Account account) {
        List<String> authorities = new ArrayList<>();

        Role role = account.getRole();
        if (role != null) {
            authorities.add(role.getName());

            List<Module> modules = moduleRepository.findByRoleId(role.getId());
            for (Module module : modules)
                authorities.add(module.getName());

            List<Permission> permissions = permissionRepository.findByRoleId(role.getId());
            for (Permission permission : permissions)
                authorities.add(permission.getName());

            List<Action> actions = actionRepository.findByRoleId(role.getId());
            for (Action action : actions)
                authorities.add(action.getName());

            return authorities;
        }

        return null;
    }


}
