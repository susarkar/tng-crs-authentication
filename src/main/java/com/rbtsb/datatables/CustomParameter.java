package com.rbtsb.datatables;

import lombok.Data;

/**
 * @author kent
 */
@Data
public class CustomParameter {

    /**
     * Column's name
     */
    private String key;

    /**
     * Column's value
     */
    private String value;

    public CustomParameter() {

    }

    /**
     * @param key
     * @param value
     */
    public CustomParameter(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
