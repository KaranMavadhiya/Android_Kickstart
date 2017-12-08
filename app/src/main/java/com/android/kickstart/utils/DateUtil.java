package com.android.kickstart.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * @return : Returns the current time without rounding.
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * @param format : Date format that you want
     * @return : Returns the current date
     */
    public static String getCurrentDate(String format) {
        return dateToString(new Date(), format);
    }

    /**
     * @param when : Date
     * @return : Given a date, return the previous date of the given date (24 hrs before).
     */
    public static Date previousDate(Date when) {
        long time = when.getTime() - (24 * 60 * 60 * 1000);
        return new Date(time);
    }

    /**
     * Given a date, return the next date of the given date (24 hrs after).
     */
    /**
     * @param when : Date
     * @return : Given a date, return the next date of the given date (24 hrs after).
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
     * @param when : Date
     * @param days : Number of days
     * @return : Given a date, return the next date of the given days.
     */
    public static Date nextDate(Date when, int days) {
        long time = when.getTime() + (days * (24 * 60 * 60 * 1000));
        return new Date(time);
    }

    /**
     * @param dateString : Date in string
     * @param format     : format of dateString
     * @return : Date
     * @throws ParseException : Exception
     */
    public static java.util.Date stringToDate(String dateString, String format) throws ParseException {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setLenient(false);
        return simpleDateFormat.parse(dateString);
    }


    /**
     * @param date   : Date
     * @param format : Format of Date
     * @return : dateString
     */
    public static String dateToString(java.util.Date date, String format) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * @param year  : Year
     * @param month : Month
     * @return : Return number of days of given year and month
     */
    public static int getDaysOfMonth(int year, int month) {
        if (String.valueOf(year).length() > 4 || month > 12 || month < 0)
            return -1;

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

}
