package org.daria.serebriakova.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

    public static final String SORT_FIELD_PARAM = "sortField";
    public static final String SORT_DIRECTION_PARAM = "sortDirection";
    public static final String DEFAULT_SORTING_DIRECTION = "DESC";
    public static final String DEFAULT_TIMEZONE_VALUE = "GMT";
    public static final String DEFAULT_SORTING_FIELD = "id";
}
