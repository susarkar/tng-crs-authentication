package com.rbtsb.entities;


import com.rbtsb.enums.EnumAccountStatus;
import com.rbtsb.utils.Global;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false, exclude = { "role" })
@Entity
@Table(name = "accounts")
@ToString(exclude = { "role"})
public class Account extends MasterEntity implements Serializable {
	private static final long serialVersionUID = -77806658444851291L;

	@Column(name = "USERNAME", nullable = false, length = Global.VARCHAR32)
	private String username;

//    @JsonIgnore
//	@Column(name = "PASSWORD", nullable = false, length = Global.VARCHAR255)
//	private String password;

	@Column(name = "EMAIL", nullable = false, length = Global.VARCHAR64)
	private String email;

	@Column(name = "FULLNAME", nullable = false, length = Global.VARCHAR255)
	private String fullName;

	@Column(name = "LOGIN_ATTEMPTS")
	private Integer loginAttempts = 0;

	@Column(name = "FORCE_CHANGE_PASSWORD")
	private Boolean forceChangePassword = true;

	@Size(max = 20)
	@Column(name = "RESET_TOKEN", length = 20)
	private String resetToken;

	@Column(name = "REQUEST_RESET_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestResetDate;

	@Column(name = "RESET_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date resetDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	@NotNull
	private Role role;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "STATUS", nullable = false)
	private EnumAccountStatus status = EnumAccountStatus.ACTIVE;

	@Transient
	private List<String> authorities;

	public int getLoginAttempts() {
		return loginAttempts != null ? loginAttempts : 0;
	}

	public EnumAccountStatus getStatus() {
		return status != null ? status : EnumAccountStatus.ACTIVE;
	}

	public Account() {
	}

	public Account(String username, String email, String fullname, Role role) {
		this.username = username;
		//this.password = password;
		this.email = email;
		this.fullName = fullname;
		this.role = role;
	}

}
