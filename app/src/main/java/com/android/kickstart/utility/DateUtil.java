package com.android.kickstart.utility;

import android.icu.util.Calendar;

import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    /**
     * Returns the current time without rounding.
     */
    public static Date today() {
        return new Date();
    }

    /**
     * Given a date, return the previous date of the given date (24 hrs before).
     */
    public static Date previousDate(Date when) {
        long time = when.getTime() - (24 * 60 * 60 * 1000);
        return new Date(time);
    }

    /**
     * Given a date, return the next date of the given date (24 hrs after).
     */
    public static Date nextDate(Date when) {
        long time = when.getTime() + (24 * 60 * 60 * 1000);
        return new Date(time);
    }

    /**
     * Given a date, return the previous date of the given days.
     */
    public static Date previousDate(Date when, int days) {
        long time = when.getTime() - (days * (24 * 60 * 60 * 1000));
        return new Date(time);
    }

    /**
     * Given a date, return the next date of the given days.
     */
    public static Date nextDate(Date when, int days) {
        long time = when.getTime() + (days * (24 * 60 * 60 * 1000));
        return new Date(time);
    }

}
