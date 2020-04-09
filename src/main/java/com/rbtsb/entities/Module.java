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
@EqualsAndHashCode(callSuper = false, exclude = {"role", "permissions"})
@Table(name = "MODULES")
@ToString(exclude = "role")
public class Module extends MasterEntity implements Serializable {
	
	 private static final long serialVersionUID = 4383183989151616768L;

	    @Column(name = "NAME", nullable = false, length = Global.VARCHAR255)
	    private String name;

	    @Column(name = "DESCRIPTION", nullable = true, length = Global.VARCHAR255)
	    private String description;

	    /**
	     * Role (Many-To-Many)
	     */
	    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "modules")
	    @JsonIgnore
	    private List<Role> role = new ArrayList<Role>();

	    /**
	     * Permissions (One-to-Many)
	     */
	    @OneToMany(fetch = FetchType.LAZY, mappedBy = "module")
	    @Cascade({CascadeType.ALL})
	    @JsonIgnore
	    private List<Permission> permissions = new ArrayList<Permission>();

//	    @ManyToOne(fetch = FetchType.EAGER)
//	    @Cascade({CascadeType.MERGE})
//	    @JoinColumn
//	    private Sector sector;

	    protected Module() {

	    }

//	    public Module(String name, String description, Sector sector) {
//	        this.name = name;
//	        this.description = description;
//	        this.sector = sector;
//	    }

	    @Transient
	    public String getText(){
	        return this.description;
	    }

}
