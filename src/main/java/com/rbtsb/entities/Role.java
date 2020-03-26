package com.rbtsb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rbtsb.utils.Global;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Data
@EqualsAndHashCode(callSuper = false, exclude = {"accounts", "modules", "permissions", "actions", "sectors"})
@Entity
@Table(name = "ROLES")
public class Role extends MasterEntity implements Serializable {

	 private static final long serialVersionUID = -315919384999465060L;

	    @Column(name = "NAME", nullable = false, length = Global.VARCHAR255)
	    private String name;

	    @Column(name = "DESCRIPTION", length = Global.VARCHAR255)
	    private String description;

	    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	    @JsonIgnore
	    private Set<Account> accounts = new HashSet<Account>();

//	    @ManyToMany(fetch = FetchType.LAZY)
//	    @JoinTable(joinColumns = {@JoinColumn(nullable = false)}, inverseJoinColumns = {@JoinColumn(nullable = false)})
//	    @JsonIgnore
//	    private Set<Sector> sectors = new HashSet<Sector>();

	    @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(joinColumns = {@JoinColumn(nullable = false)}, inverseJoinColumns = {@JoinColumn(nullable = false)})
	    @JsonIgnore
	    private Set<Module> modules = new HashSet<Module>();

	    @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(joinColumns = {@JoinColumn(nullable = false)}, inverseJoinColumns = {@JoinColumn(nullable = false)})
	    @JsonIgnore
	    private Set<Permission> permissions = new HashSet<Permission>();

	    @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(joinColumns = {@JoinColumn(nullable = false)}, inverseJoinColumns = {@JoinColumn(nullable = false)})
	    @JsonIgnore
	    private Set<Action> actions = new HashSet<Action>();

//	    @Transient
//	    Set<Sector> tempSectors = new HashSet<Sector>();

	    @Transient
	    Set<Module> tempModules = new HashSet<Module>();

	    @Transient
	    Set<Permission> tempPermissions = new HashSet<Permission>();

	    @Transient
	    Set<Action> tempActions = new HashSet<Action>();

	    @Transient
	    public String getText(){
	        return this.description;
	    }

	    public Role(){}

	    public Role(String name, String description) {
			this.name = name;
			this.description = description;
		}

//		public Role(String name, String description, Set<Sector> sectors, Set<Module> modules,
//                    Set<Permission> permissions, Set<Action> actions) {
//			this.name = name;
//			this.description = description;
//			this.sectors = sectors;
//			this.modules = modules;
//			this.permissions = permissions;
//			this.actions = actions;
//		}
}

