package com.android.kickstart.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Pattern;

public class ValidationUtil {

    /**
     * @param string string value
     * @return true if string is null
     */
    public static boolean isNullString(final String string) {
        return TextUtils.isEmpty(string) || string.equalsIgnoreCase("null");
    }

    /**
     * @param view : view can be EditText, TextView or Spinner
     * @return true if string is null
     */
    public boolean isNullString(View view) {
        if (view != null && view instanceof EditText) {
            EditText editText = (EditText) view;
            return isNullString(editText.getText().toString().trim());
        } else if (view != null && view instanceof TextView) {
            TextView textView = (TextView) view;
            return isNullString(textView.getText().toString().trim());
        } else if (view != null && view instanceof Spinner) {
            Spinner spinner = (Spinner) view;
            return isNullString(spinner.getSelectedItem().toString().trim());
        }
        return false;
    }

    /**
     * @param name to be verified
     * @return true valid name, false invalid name
     */
    public boolean isValidName(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]{3,20}$");
        return !isNullString(name) && pattern.matcher(name).matches();
    }

    /**
     * @param age to be verified
     * @return true valid age, false invalid age
     */
    public boolean isValidAge(String age) {
        Pattern pattern = Pattern.compile("^[1-9]{1,3}$");
        return !isNullString(age) && pattern.matcher(age).matches();
    }

    /**
     * @param email to be verified
     * @return true valid email, false invalid email
     */
    public static boolean isValidEmail(final String email) {
        return !isNullString(email) && (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    /**
     * @param pinCode to be verified
     * @return true valid pin code , false invalid pin code
     */
    public boolean isValidPinCode(String pinCode) {
        Pattern pattern = Pattern.compile("^[0-9]{6}$");
        return !isNullString(pinCode) && pattern.matcher(pinCode).matches();
    }

    /**
     * @param phoneNumber to be verified
     * @return true valid phone number, false invalid phone number
     */
    public boolean isValidPhoneNumber(String phoneNumber) {
        return !isNullString(phoneNumber) && Patterns.PHONE.matcher(phoneNumber).matches();
    }

    /**
     * @param mobile number to be verified
     * @return true valid mobile number, false invalid mobile number
     */
    public boolean isValidMobile(String mobile) {
        Pattern pattern = Pattern.compile("^[789]\\d{9,9}$");
        return !isNullString(mobile) & pattern.matcher(mobile).matches();
    }

    /**
     * @param url to be verified
     * @return true valid email id, false invalid email id
     */
    public static boolean isValidUrl(final String url) {
        return !isNullString(url) && Patterns.WEB_URL.matcher(url).matches();
    }

    /**
     * IP Addresses format
     */
    private static final String IPADDRESS_STRING_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    /**
     * @param ip to be verified
     * @return true valid ip address, false invalid ip address
     */
    public boolean isValidIp(String ip) {
        return !isNullString(ip) && Pattern.compile(IPADDRESS_STRING_PATTERN).matcher(ip).matches();
    }

    /**
     * Checks Intent Activity Available or Not
     *
     * @param context : Activity/Fragment Context to getPackageManager
     * @param intent : Intent
     * @return : true if Activity for intent is available else false
     */
    private static boolean isActivityForIntentAvailable(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        List list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    /**
     * ^ : start of string
     * [ : beginning of character group
     * a-z : any lowercase letter
     * A-Z : any uppercase letter
     * 0-9 : any digit
     * _ : underscore
     * ] : end of character group
     * * : zero or more of the given characters
     * $ : end of string
     */

    /**
     * @param string string value
     * @return true if string contains number and alphabet both
     */
    public static boolean isAlphaNumeric(String string) {
        String pattern = "^([0-9]+[a-zA-Z]+|[a-zA-Z]+[0-9]+)[0-9a-zA-Z]*$";
        return !isNullString(string) && string.matches(pattern);
    }

    /**
     * @param userName string value
     * @return true if userName valid (Allow only a-z A-Z 0-9 _ .)
     */
    public static boolean isValidUserName(String userName) {
        String pattern = "^[a-zA-Z0-9_.]+$";
        return !isNullString(userName) && userName.matches(pattern);
    }

    /**
     * @param context Activity/Fragment Context to check network available or not
     * @return true if network is connected
     */
    public static boolean isInternetAvailable(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
        } else {
            return false;
        }
    }

    /**
     * InputFilter allows Number and Character only
     */
    public static InputFilter inputFilterAllowAlphaNumericOnly = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence src, int start, int end, Spanned dest, int dstart, int dend) {
            if (src.toString().matches("[a-zA-Z0-9 ]*")) {
                return src;
            }
            return "";
        }
    };
}
