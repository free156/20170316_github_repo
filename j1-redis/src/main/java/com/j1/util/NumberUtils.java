package com.j1.util;


/**
 * Created by foxzen on 9/8/14.
 */
public class NumberUtils {

    private static int maxInteger(int n, int current) {
        if (n < 10)
            return current;
        return maxInteger(n / 10, current * 10);
    }

    public static int zeroOther(int n) {
        int maxLimit = maxInteger(n, 1);
        return n / maxLimit * maxLimit;
    }

    public static String shorthand(int n) {
        if (n < 10000)
            return String.valueOf(n);
        if (n < 100000)
            return (String.valueOf(n / 10000) + (n % 10000 == 0 ? "万" : "万多"));
        if (n < 100000000)
            return String.valueOf(NumberUtils.zeroOther(n) / 10000) + (n % 10000 == 0 ? "万" : "多万");
        return String.valueOf(n / 100000000) + "亿";
    }
    
    public static Integer parseIntWithDefault(String str, Integer defaultVal) {
    	if(str == null || "".equals(str.trim())) return defaultVal;
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defaultVal;
    }
}
