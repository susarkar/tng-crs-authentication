package com.rbtsb.datatables;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author kent
 */
@Data
public class OrderParameter {
    /**
     * Column to which ordering should be applied. This is an index reference to
     * the columns array of information that is also submitted to the server.
     */
    @NotNull
    @Min(0)
    private Integer column;

    /**
     * Ordering direction for this column. It will be asc or desc to indicate
     * ascending ordering or descending ordering, respectively.
     */
    @NotNull
    @Pattern(regexp = "[desc|asc]")
    private String dir;

    public OrderParameter() {
    }

    /**
     * Order parameter
     *
     * @param column
     * @param dir
     */
    public OrderParameter(Integer column, String dir) {
        this.column = column;
        this.dir = dir;
    }
}
