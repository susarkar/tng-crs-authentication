package com.rbtsb.datatables;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.Arrays;

/**
 * @param <T>
 * @author kent
 */
public class DataTablesSpecification<T> implements Specification<T> {

    private final static String OR_SEPARATOR = "+";

    private final static String ATTRIBUTE_SEPARATOR = ".";

    private final DataTablesInput input;

    public DataTablesSpecification(DataTablesInput input) {
        this.input = input;
    }

    /**
     * Creates a WHERE clause for the given {@link DataTablesInput}.
     *
     * @return a {@link Predicate}, must not be {@literal null}.
     */
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        Predicate predicate = criteriaBuilder.conjunction();

        // check for each searchable column whether a filter value exists
        for (ColumnParameter column : input.getColumns()) {
            String filterValue = column.getSearch().getValue();
            if (column.getSearchable() && StringUtils.hasText(filterValue)) {
                Expression<String> expression = getExpression(root, column.getData());

                if (filterValue.contains(OR_SEPARATOR)) {
                    // the filter contains multiple values, add a 'WHERE .. IN'
                    // clause
                    // Note: "\\" is added to escape special character '+'
                    String[] values = filterValue.split("\\" + OR_SEPARATOR);
                    if (values.length > 0 && isBoolean(values[0])) {
                        Object[] booleanValues = new Boolean[values.length];
                        for (int i = 0; i < values.length; i++) {
                            booleanValues[i] = Boolean.valueOf(values[i]);
                        }
                        predicate = criteriaBuilder.and(predicate, expression.as(Boolean.class).in(booleanValues));
                    } else {
                        predicate = criteriaBuilder.and(predicate, expression.in(Arrays.asList(values)));
                    }
                } else {
                    // the filter contains only one value, add a 'WHERE .. LIKE'
                    // clause
                    if (isBoolean(filterValue)) {
                        predicate = criteriaBuilder.and(predicate,
                                criteriaBuilder.equal(expression.as(Boolean.class), Boolean.valueOf(filterValue)));
                    } else {
                        predicate = criteriaBuilder.and(predicate, criteriaBuilder
                                .like(criteriaBuilder.lower(expression), "%" + filterValue.toLowerCase() + "%"));
                    }
                }
            }
        }

        // check whether a global filter value exists
        String globalFilterValue = input.getSearch().getValue();
        if (StringUtils.hasText(globalFilterValue)) {
            Predicate matchOneColumnPredicate = criteriaBuilder.disjunction();
            // add a 'WHERE .. LIKE' clause on each searchable column
            for (ColumnParameter column : input.getColumns()) {
                if (column.getSearchable()) {
                    Expression<String> expression = getExpression(root, column.getData());

                    matchOneColumnPredicate = criteriaBuilder.or(matchOneColumnPredicate, criteriaBuilder
                            .like(criteriaBuilder.lower(expression), "%" + globalFilterValue.toLowerCase() + "%"));
                }
            }
            predicate = criteriaBuilder.and(predicate, matchOneColumnPredicate);
        }

        // filters deleted
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.isFalse(root.get("deleted")));

        return predicate;
    }

    /**
     * @param filterValue
     * @return
     */
    private boolean isBoolean(String filterValue) {
        return "TRUE".equalsIgnoreCase(filterValue) || "FALSE".equalsIgnoreCase(filterValue);
    }

    /**
     * Use expression like to find record
     *
     * @param root
     * @param columnData
     * @return
     */
    private Expression<String> getExpression(Root<T> root, String columnData) {
        if (columnData.contains(ATTRIBUTE_SEPARATOR)) {
            // columnData is like "joinedEntity.attribute" so add a join clause
            String[] values = columnData.split("\\" + ATTRIBUTE_SEPARATOR);
            return root.join(values[0], JoinType.LEFT).get(values[1]).as(String.class);
        } else {
            // columnData is like "attribute" so nothing particular to do
            return root.get(columnData).as(String.class);
        }
    }
}
