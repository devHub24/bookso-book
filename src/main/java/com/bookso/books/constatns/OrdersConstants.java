package com.bookso.books.constatns;

import java.util.Map;

public class OrdersConstants {

    //Order Status Constants
    public static final String INITIATED = "INITIATED";
    public static final String PACKED = "PACKED";
    public static final String OUT_FOR_DELIVERY = "OUT-FOR-DELIVERY";
    public static final String DELIVERED = "DELIVERED";
    public static final String CANCELLED = "CANCELLED";
    public static final String FAILED = "FAILED";

    //OrdersStatusMap
    public static final Map<String, String> orderStatusMaps = Map.ofEntries(
            Map.entry("OS1", INITIATED),
            Map.entry("OS2", PACKED),
            Map.entry("OS3", OUT_FOR_DELIVERY),
            Map.entry("OS4", DELIVERED),
            Map.entry("OS5", FAILED),
            Map.entry("OS6", CANCELLED)
    );
}
