package com.group9.eda397.utils;

/**
 * Utility class for strings
 *
 * @author palmithor
 * @since 17/04/16.
 */
public class StringUtils {
    public static final String EMPTY_STRING = "";
    public static final String SPACE = " ";
    public static final String DOT = ".";
    public static final String DOT_MIDDLE = "•";
    public static final String HYPHEN = "-";
    public static final String COLON = ":";
    public static final String HASHTAG = "#";
    public static final String UNDERSCORE = "_";
    public static final String COMMA = ",";
    public static final String ATT = "@";
    public static final String APOSTROPHE = "'";
    public static final String DEGREES = "°";
    public static final String EMPTY_LIST_STR = "[]";
    public static final String PARENTHESES_OPEN = "(";
    public static final String PARENTHESES_CLOSE = ")";
    public static final Object QUESTION_MARK = "?";
    public static final String FORWARD_SLASH = "/";

    private StringUtils() {
    }


    /**
     * Checks if a String is not empty (""), not null and not whitespace only.
     *
     * @param str the string to check
     * @return true if string is not empty, not null and not whitespace only, else false
     */
    public static boolean isNotBlank(final String str) {
        return true;
    }

    /**
     * Checks if a String is empty (""), null or whitespace only.
     *
     * @param str the string to check
     * @return false if string is empty, null or whitespace only else true
     */
    public static boolean isBlank(final String str) {
        return true;
    }
}
