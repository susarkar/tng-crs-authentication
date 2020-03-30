package com.rbtsb.datatables;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kent
 */
@Data
public class DataTablesInput {
    /**
     * Draw counter. This is used by DataTables to ensure that the Ajax returns
     * from server-side processing requests are drawn in sequence by DataTables
     * (Ajax requests are asynchronous and thus can return out of sequence).
     * This is used as part of the draw return parameter (see below).
     */
    @NotNull
    @Min(0)
    private Integer draw;

    /**
     * Paging first record indicator. This is the start point in the current
     * data set (0 index based - i.e. 0 is the first record).
     */
    @NotNull
    @Min(0)
    private Integer start;

    /**
     * Number of records that the table can display in the current draw. It is
     * expected that the number of records returned will be equal to this
     * number, unless the server has fewer records to return. Note that this can
     * be -1 to indicate that all records should be returned (although that
     * negates any benefits of server-side processing!)
     */
    @NotNull
    @Min(-1)
    private Integer length;

    /**
     * Global search parameter.
     */
    @NotNull
    private SearchParameter search;

    /**
     * Order parameter
     */
    @NotEmpty
    private List<OrderParameter> order;

    /**
     * Per-column search parameter
     */
    @NotEmpty
    private List<ColumnParameter> columns;

    private List<CustomParameter> customs;

    public DataTablesInput() {
        this.search = new SearchParameter();
        this.order = new ArrayList<OrderParameter>();
        this.columns = new ArrayList<ColumnParameter>();
        this.customs = new ArrayList<CustomParameter>();
    }
}
