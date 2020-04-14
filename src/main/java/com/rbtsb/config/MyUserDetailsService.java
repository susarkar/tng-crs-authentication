package com.rbtsb.config;

import com.rbtsb.controller.FeignProxy;
import com.rbtsb.entities.Account;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    FeignProxy feignProxy;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = feignProxy.getAccountByUserName(username);
        log.debug("Entered into loadUserByUsername..." + username);
        return Optional.ofNullable(account)
                .map(this::createUser)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    private User createUser(Account account) {
        log.debug("Entered into createUser...{} " + account);
        return new User(account.getUsername(), "P@ssw0rd", getAuthorities(account));
    }


    public List<GrantedAuthority> getAuthorities(Account account) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String authority : account.getAuthorities()
        ) {
            authorities.add(new SimpleGrantedAuthority(authority));
        }
        return authorities;
    }
}