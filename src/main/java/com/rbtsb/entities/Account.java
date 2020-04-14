package com.rbtsb.entities;

import lombok.Data;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Data
public class Account {

    private String username;

    private String email;

    private String fullName;

    private Integer loginAttempts;

    private Boolean forceChangePassword;

    private String resetToken;

    private Date requestResetDate;

    private Date resetDate;

    @Transient
    private List<String> authorities;

//    @Transient
//    private List<GrantedAuthority> authorities;
}
