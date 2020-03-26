package com.rbtsb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rbtsb.dto.BaseEntityDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 7509737276421745100L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @JsonIgnore
    public void setDTO(BaseEntityDTO baseEntityDTO) {
        this.id = baseEntityDTO.getId();
    }
}
