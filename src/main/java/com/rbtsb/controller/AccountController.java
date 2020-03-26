//package com.rbtsb.controller;
//
//import com.rbtsb.entities.Account;
//import lombok.extern.log4j.Log4j2;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.security.Principal;
//
//@RestController
//@RequestMapping(value = {"/api/account"})
//@Log4j2
//public class AccountController {
//
//    @Autowired
//    private AccountProxy accountProxy;
//
//
//    @RequestMapping(value = "/current", method = RequestMethod.GET)
//    public ResponseEntity<?> accounts(Principal principal, HttpServletRequest request) throws Exception {
//        log.debug("REST request to get current Account : {}", principal.getName());
//        Account account = accountProxy.getAccountByUserName(principal.getName());
//        return new ResponseEntity<>(account, HttpStatus.OK);
//    }
//
//    @GetMapping("/hello")
//    public ResponseEntity<?> firstPage() {
//        return new ResponseEntity<>("Hello Account", HttpStatus.OK);
//    }
//
//}
