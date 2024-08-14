package org.dashia18.util;

import java.util.List;
import lombok.Data;

@Data
public class Page<T> {
    public static final String DEFAULT_OFFSET = "0";
    public static final String OFFSET_PARAM = "offset";
    public static final String LIMIT_PARAM = "limit";
    public static final String DEFAULT_LIMIT = "1000";
    private List<T> content;
    private Integer offset;
    private Integer limit;
    private Integer totalElements;
    private Integer numberOfElements;
    private String sortField;
    private String sortDirection;
}
