package com.rbtsb.entities;

import com.rbtsb.enums.EnumAccountStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Enumerated(value = EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private EnumAccountStatus status = EnumAccountStatus.ACTIVE;

    @Transient
    private List<String> authorities;

//    @Transient
//    private List<GrantedAuthority> authorities;
}
