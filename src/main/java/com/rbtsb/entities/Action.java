package com.rbtsb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rbtsb.utils.Global;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "ACTIONS")
@ToString(exclude = "role")
public class Action extends MasterEntity implements Serializable {
	private static final long serialVersionUID = 8705451231589030649L;

    @Column(name = "NAME", nullable = false, length = Global.VARCHAR255)
    private String name;

    @Column(name = "DESCRIPTION", nullable = true, length = Global.VARCHAR255)
    private String description;

    /**
     * Role (Many-To-Many)
     */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "actions")
    @JsonIgnore
    private List<Role> role = new ArrayList<Role>();

    /**
     * Parent Permission (Many-To-One)
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Permission permission;

    protected Action() {

    }

    public Action(String name, String description, Permission permission) {
        this.name = name;
        this.description = description;
        this.permission = permission;
    }

    @Transient
    public String getPermissionId() {
        return permission != null ? permission.getId() : null;
    }

    @Transient
    public String getPermissionName() {
        return permission != null ? permission.getDescription() : null;
    }

    @Transient
    public String getText(){
        return this.description;
    }
}
