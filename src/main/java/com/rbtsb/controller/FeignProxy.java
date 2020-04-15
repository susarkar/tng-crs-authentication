package com.rbtsb.controller;

import com.rbtsb.entities.Account;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "tng-crs-masterdata")
@RibbonClient(name = "tng-crs-masterdata")
@Service
public interface FeignProxy {
    @RequestMapping("/master-data/rest/feign-user/find-by-username/{username}")
    public Account getAccountByUserName(@PathVariable(value = "username") String username);
}
