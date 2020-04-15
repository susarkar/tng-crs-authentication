package com.rbtsb.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Log4j2
public class SecureController {

    //    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'A_LIST_USER')")
    @PreAuthorize("hasAnyAuthority('ROLE_CREATOR')")
    @GetMapping("checkapp")
    public String checkApp() {
        return "Application is running ok";
    }
}
