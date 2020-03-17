package com.rbtsb.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

//    @Autowired
//    private AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("admin", "P@ssw0rd",
                new ArrayList<>());
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return Optional.ofNullable(accountRepository.findByUsername(username))
//                .map(this::createUser)
//                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
//    }
//
//    private User createUser(Account account) {
//        return new User(account.getUsername(), account.getPassword(), createAuthority(account));
//    }
//
//
//    private List<GrantedAuthority> createAuthority(Account account) {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        authorities.add(new SimpleGrantedAuthority(account.getRole().toString()));
//        return authorities;
//    }
}