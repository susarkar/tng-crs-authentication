package com.rbtsb.controller;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rbtsb.pojos.AuditLogPojo;

@FeignClient("tng-crs-log")
@RibbonClient("tng-crs-log")
@Service
public interface FeignLogProxy {
	
	@RequestMapping(value = "/log/audit", method = RequestMethod.POST)
    public void addAuditLog(@RequestBody AuditLogPojo auditLogPojo);		

}
