package org.dashia18.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {
    public static final String JSON_TYPE = "json";
    public static final String ADMIN_USER = "admin";

    public static final String BANK_API_URI = "bank/";
    public static final String COMMON_API_URI = "java/related/";
    public static final String SORT_FIELD_PARAM = "sortField";
    public static final String SORT_DIRECTION_PARAM = "sortDirection";
    public static final String DEFAULT_SORTING_DIRECTION = "DESC";
    public static final String DEFAULT_TIMEZONE_VALUE = "GMT";
    public static final String DEFAULT_SORTING_FIELD = "id";

    public static final String AUTHORITY_USER_WRITE = "User.Write";
    public static final String AUTHORITY_USER_READ = "User.Read";
}
