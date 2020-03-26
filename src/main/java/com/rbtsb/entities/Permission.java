package com.rbtsb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rbtsb.utils.Global;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = false, exclude = {"role", "module", "actions"})
@Table(name="PERMISSIONS")
@ToString(exclude = "role")
public class Permission extends MasterEntity implements Serializable {
	
	 private static final long serialVersionUID = 5898047976985972375L;

	    @Column(name = "NAME", nullable = false, length = Global.VARCHAR255)
	    private String name;

	    @Column(name = "DESCRIPTION", nullable = true, length = Global.VARCHAR255)
	    private String description;

	    /**
	     * Role (Many-To-Many)
	     */
	    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "permissions")
	    @JsonIgnore
	    private List<Role> role = new ArrayList<Role>();

	    /**
	     * Parent Module (Many-To-One)
	     */
	    @ManyToOne(fetch = FetchType.EAGER)
	    @Cascade({CascadeType.MERGE})
	    @JoinColumn
	    private Module module;

	    /**
	     * Action (One-to-Many)
	     */
	    @OneToMany(fetch = FetchType.LAZY, mappedBy = "permission")
	    @Cascade({CascadeType.ALL})
	    @JsonIgnore
	    private List<Action> actions = new ArrayList<Action>();

	    protected Permission() {

	    }

	    public Permission(String name, String description, Module module) {
	        this.name = name;
	        this.description = description;
	        this.module = module;
	    }

	    @Transient
	    public String getModuleId() {
	        return module != null ? module.getId() : null;
	    }

	    @Transient
	    public String getModuleName() {
	        return module != null ? module.getName() : null;
	    }

	    @Transient
	    public String getDecription(){
	        return this.description;
	    }

}
