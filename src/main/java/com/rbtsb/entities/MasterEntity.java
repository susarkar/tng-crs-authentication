package com.rbtsb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rbtsb.dto.MasterEntityDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdBy","createdDate", "lastModifiedBy","lastModifiedDate"},
        allowGetters = true
)
public abstract class MasterEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8766891123160277608L;


    private Boolean deleted = false;


    private Boolean active = true;

    private Boolean restricted = false;

    @CreatedDate
    private Date createdDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private Date lastModifiedDate;

    @LastModifiedBy
    private String lastModifiedBy;
    
    @PrePersist
    void onCreate() {
        this.setCreatedDate(new Date());
        this.setLastModifiedDate(new Date());
        this.setCreatedBy("admin");
        this.setLastModifiedBy("admin");
//        this.setCreatedBy(SecurityContextUtil.getCurrentUsername());
//        this.setLastModifiedBy(SecurityContextUtil.getCurrentUsername());
    }

    @PreUpdate
    void onUpdate() {
        this.setLastModifiedDate(new Date());
        this.setLastModifiedBy("admin");
        //this.setLastModifiedBy(SecurityContextUtil.getCurrentUsername());
    }

    @JsonIgnore
    public void setDTO(MasterEntityDTO masterEntityDTO) {
        super.setDTO(masterEntityDTO);
        this.deleted = StringUtils.isEmpty(masterEntityDTO.getDeleted()) ? this.deleted : masterEntityDTO.getDeleted();
    }


}
