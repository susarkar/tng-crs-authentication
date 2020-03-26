package com.rbtsb.controller;

import com.rbtsb.entities.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "tng-crs-masterdata")//Service Id of EmployeeSerach service
@Service
public interface AccountProxy {
//    @RequestMapping("/employee/find/{id}")
//    public EmployeeInfo findById(@PathVariable(value = "id") Long id);
//
//    @RequestMapping("/employee/findall")
//    public Collection<EmployeeInfo> findAll();

//    @RequestMapping(method = RequestMethod.GET, value = "/employee/{username}")
//    public Account getAccountByUserName();

    @RequestMapping("/user-management/accounts/find-by-username/{username}")
    public ResponseEntity<?> getAccountByUserName(@PathVariable(value = "username") String username);

    @GetMapping("/docker/check")
    public String checkDocker();

}
