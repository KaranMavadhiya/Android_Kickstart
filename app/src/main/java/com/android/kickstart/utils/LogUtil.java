package com.android.kickstart.utils;

public class LogUtil {

    private static String tagName = "LogUtil";

    /**
     * @param Tag :  Log TAG
     * @param message : Log message
     */
    public static void w(String Tag, String message) {
        android.util.Log.w(tagName, Tag + " : " + message);
    }

    /**
     * @param Tag :  Log TAG
     * @param message : Log message
     */
    public static void d(String Tag, String message) {
        android.util.Log.d(tagName, Tag + " : " + message);
    }

    /**
     * @param Tag :  Log TAG
     * @param message : Log message
     */
    public static void e(String Tag, String message) {
        android.util.Log.e(tagName, Tag + " : " + message);
    }

    /**
     * @param Tag :  Log TAG
     * @param message : Log message
     * @param e : Throwable
     */
    public static void e(String Tag, String message, Throwable e) {
        android.util.Log.e(tagName, Tag + " : " + message, e);
    }

    /**
     * @param Tag :  Log TAG
     * @param message : Log message
     */
    public static void i(String Tag, String message) {
        android.util.Log.i(tagName, Tag + " : " + message);
    }

    /**
     * @param Tag :  Log TAG
     * @param message : Log message
     */
    public static void v(String Tag, String message) {
        android.util.Log.v(tagName, Tag + " : " + message);
    }
}