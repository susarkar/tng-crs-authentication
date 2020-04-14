package com.rbtsb.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.rbtsb.utils.FieldAttributes;
import lombok.Data;

import java.io.Serializable;

@Data
public class AuditLogPojo implements Serializable {


    private static final long serialVersionUID = 3314554531937811484L;

    @FieldAttributes(nullable = false)
    @JsonProperty(value = "Timestamp")
    private String timestamp;

    @FieldAttributes(nullable = false)
    @JsonProperty(value = "api")
    private String api;

    @FieldAttributes(nullable = false)
    @JsonProperty(value = "action")
    private String action;

    @FieldAttributes(nullable = false)
    @JsonProperty(value = "username")
    private String username;

    @FieldAttributes(nullable = false)
    @JsonProperty(value = "data")
    private JsonNode data;

}