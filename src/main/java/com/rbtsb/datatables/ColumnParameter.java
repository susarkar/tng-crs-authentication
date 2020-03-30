package com.rbtsb.datatables;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author kent
 */
@Data
public class ColumnParameter {
    /**
     * Column's data source
     *
     * @see http://datatables.net/reference/option/columns.data
     */
    @NotBlank
    private String data;

    /**
     * Column's name/index
     * This field will be used to define the sort/query field name
     * E.g. if the data field is a Transient value which is not in DB, name can be used as actual field to query/sort
     *
     * @see http://datatables.net/reference/option/columns.name
     */
    private String name;

    /**
     * Flag to indicate if this column is searchable (true) or not (false).
     *
     * @see http://datatables.net/reference/option/columns.searchable
     */
    @NotNull
    private Boolean searchable;

    /**
     * Flag to indicate if this column is orderable (true) or not (false).
     *
     * @see http://datatables.net/reference/option/columns.orderable
     */
    @NotNull
    private Boolean orderable;

    /**
     * Search value to apply to this specific column.
     */
    @NotNull
    private SearchParameter search;

    public ColumnParameter() {
    }

    /**
     * @param data
     * @param name
     * @param searchable
     * @param orderable
     * @param search
     */
    public ColumnParameter(String data, String name, Boolean searchable,
                           Boolean orderable, SearchParameter search) {
        super();
        this.data = data;
        this.name = name;
        this.searchable = searchable;
        this.orderable = orderable;
        this.search = search;
    }
}
