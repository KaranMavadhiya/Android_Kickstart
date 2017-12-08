package com.android.kickstart.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class IntentUtil {

    private static String message = "Sorry! No Activity found to perform this action, Please install application.";

    /**
     * @param url : web URL
     * @return : Intent
     */

    public static Intent getOpenURLIntent(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        return intent;
    }

    /**
     * @param context      : Activity/Fragment Context
     * @param url          : web URL
     * @param chooserTitle : Chooser Title
     * @throws ActivityNotFoundException : Exception
     */
    public static void openURL(Context context, String url, String chooserTitle) {
        Intent intent = getOpenURLIntent(url);
        Intent chooser = Intent.createChooser(intent, chooserTitle);
        try {
            context.startActivity(chooser);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param number : Phone Number
     * @return : Intent
     */
    public static Intent getCallPhoneIntent(String number) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse(String.format("tel:%s", number)));
        return callIntent;
    }

    /**
     * @param context      : Activity/Fragment Context
     * @param number       : Phone Number
     * @param chooserTitle : Chooser Title
     * @throws ActivityNotFoundException : Exception
     */
    public static void callPhone(Context context, String number, String chooserTitle) {
        Intent intent = getCallPhoneIntent(number);
        Intent chooser = Intent.createChooser(intent, chooserTitle);
        try {
            context.startActivity(chooser);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param number : Phone Number
     * @param body   : Message Text
     * @return : Intent
     */
    public static Intent getSendSMSIntent(String number, String body) {
        Intent callIntent = new Intent(Intent.ACTION_SENDTO);
        callIntent.setData(Uri.parse(String.format("sms:%s", number)));
        callIntent.putExtra("sms_body", body);
        return callIntent;
    }

    /**
     * @param context      : Activity/Fragment Context
     * @param number       : Phone Number
     * @param body         : Message Text
     * @param chooserTitle : Chooser Title
     * @throws ActivityNotFoundException : Exception
     */
    public static void sendSMS(Context context, String number, String body, String chooserTitle) {
        Intent intent = getSendSMSIntent(number, body);
        Intent chooser = Intent.createChooser(intent, chooserTitle);
        try {
            context.startActivity(chooser);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    public static class PackageName {
        public static String WhatsApp = "com.whatsapp";
        public static String LinkedIn = "com.linkedin.android";
        public static String Twitter = "com.twitter.android";
        public static String Facebook = "com.facebook.katana";
        public static String GooglePlus = "com.google.android.apps.plus";
    }

    /**
     * @param context : Activity/Fragment Context
     * @param packageName : Application Package Name
     * @param message : Message that you want to send
     */
    public static void shareMessage(Context context, String packageName, String message) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.setPackage(packageName);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param to       : Email address for TO for multiple email address you can add comma separated string ie. "abc@xyz.com, xyz@abc.com"
     * @param cc       : Email address for CC
     * @param subject  : Email subject
     * @param content  : Email Body
     * @param filePath : Email attachment ArrayList of string
     * @return : Intent
     */
    public static Intent getSendEmailIntent(String to, String cc, String subject, CharSequence content, ArrayList<String> filePath) {
        Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
        mailIntent.setData(Uri.parse(String.format("mailto:%s", to.trim())));
        mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});

        // convert from paths to Android friendly Parcelable Uri's
        if (filePath != null && filePath.size() > 0) {
            ArrayList<Uri> uris = new ArrayList<Uri>();
            for (String file : filePath) {
                File fileIn = new File(file);
                Uri u = Uri.fromFile(fileIn);
                uris.add(u);
            }
            mailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
        }

        if (cc.trim().length() > 0)
            mailIntent.putExtra(Intent.EXTRA_CC, new String[]{cc});

        if (subject.trim().length() > 0)
            mailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);

        if (content.toString().trim().length() > 0)
            mailIntent.putExtra(Intent.EXTRA_TEXT, content);

        return mailIntent;
    }

    /**
     * @param context : Activity/Fragment Context
     * @param to      : Email address for TO for multiple email address you can add comma separated string ie. "abc@xyz.com, xyz@abc.com"
     * @throws ActivityNotFoundException : Exception
     */
    public static void sendEmailFromGmail(Context context, String to) throws ActivityNotFoundException {
        Intent mailIntent = getSendEmailIntent(to, "", "", "", null);
        try{
            context.startActivity(mailIntent);
        }catch (ActivityNotFoundException e){
            e.printStackTrace();
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param context : Activity/Fragment Context
     * @param to      : Email address for TO for multiple email address you can add comma separated string ie. "abc@xyz.com, xyz@abc.com"
     * @param cc      : Email address for CC
     * @throws ActivityNotFoundException : Exception
     */
    public static void sendEmailFromGmail(Context context, String to, String cc) throws ActivityNotFoundException {
        Intent mailIntent = getSendEmailIntent(to, cc, "", "", null);
        try{
            context.startActivity(mailIntent);
        }catch (ActivityNotFoundException e){
            e.printStackTrace();
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param context : Activity/Fragment Context
     * @param to      : Email address for TO for multiple email address you can add comma separated string ie. "abc@xyz.com, xyz@abc.com"
     * @param subject : Subject of email
     * @throws ActivityNotFoundException : Exception
     */
    public static void sendEmailFromGmail(Context context, String to, String subject, CharSequence content) throws ActivityNotFoundException {
        Intent mailIntent = getSendEmailIntent(to, "", subject, content, null);
        try{
            context.startActivity(mailIntent);
        }catch (ActivityNotFoundException e){
            e.printStackTrace();
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param context : Activity/Fragment Context
     * @param to      : Email address for TO for multiple email address you can add comma separated string ie. "abc@xyz.com, xyz@abc.com"
     * @param cc      : Email address for CC
     * @param subject : Subject of email
     * @param content : Email Body
     * @throws ActivityNotFoundException : Exception
     */
    public static void sendEmailFromGmail(Context context, String to, String cc, String subject, CharSequence content) throws ActivityNotFoundException {
        Intent mailIntent = getSendEmailIntent(to, cc, subject, content, null);
        try{
            context.startActivity(mailIntent);
        }catch (ActivityNotFoundException e){
            e.printStackTrace();
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param context  : Activity/Fragment Context
     * @param to       : Email address for TO for multiple email address you can add comma separated string ie. "abc@xyz.com, xyz@abc.com"
     * @param cc       : Email address for CC
     * @param subject  : Subject of email
     * @param content  : Email Body
     * @param filePath : Email attachment ArrayList of string
     * @throws ActivityNotFoundException : Exception
     */
    public static void sendEmailFromGmail(Context context, String to, String cc, String subject, CharSequence content, ArrayList<String> filePath) throws ActivityNotFoundException {
        Intent mailIntent = getSendEmailIntent(to, cc, subject, content, filePath);
        try{
            context.startActivity(mailIntent);
        }catch (ActivityNotFoundException e){
            e.printStackTrace();
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param latitude    : Map latitude
     * @param longitude   : Map longitude
     * @param markerTitle : Market title
     * @return : Intent
     */
    public static Intent getMapCoordinatesIntent(double latitude, double longitude, String markerTitle) {
        String uri = "geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude;
        if (markerTitle != null && markerTitle.length() > 0) {
            uri += "(" + markerTitle + ")";
        }
        return new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
    }

    /**
     * @param context     : Activity/Fragment Context
     * @param latitude    : Map latitude
     * @param longitude   : Map longitude
     * @param markerTitle : Market title
     * @throws ActivityNotFoundException
     */
    public static void showMapCoordinates(Context context, double latitude, double longitude, String markerTitle) throws ActivityNotFoundException {
        Intent mapIntent = getMapCoordinatesIntent(latitude, longitude, markerTitle);
        try{
            context.startActivity(mapIntent);
        }catch (ActivityNotFoundException e){
            e.printStackTrace();
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param latitudeFrom  : Map from latitude
     * @param longitudeFrom : Map from longitude
     * @param latitudeTo    : Map to latitude
     * @param longitudeTo   : Map to longitude
     * @param routeTitle    : Map route title
     * @return : Intent
     */
    public static Intent getMapRouteIntent(double latitudeFrom, double longitudeFrom, double latitudeTo, double longitudeTo, String routeTitle) {
        Uri uri = Uri.parse("http://maps.google.com/maps?saddr=" + latitudeFrom + "," + longitudeFrom + "&daddr=" + latitudeTo + "," + longitudeTo + "");
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    /**
     * @param context       : Activity/Fragment Context
     * @param latitudeFrom  : Map from latitude
     * @param longitudeFrom : Map from longitude
     * @param latitudeTo    : Map to latitude
     * @param longitudeTo   : Map to longitude
     * @param routeTitle    : Map route title
     * @throws ActivityNotFoundException : Exception
     */
    public static void showMapRoute(Context context, double latitudeFrom, double longitudeFrom, double latitudeTo, double longitudeTo, String routeTitle) throws ActivityNotFoundException {
        Intent mapIntent = getMapRouteIntent(latitudeFrom, longitudeFrom, latitudeTo, longitudeTo, routeTitle);
        try{
            context.startActivity(mapIntent);
        }catch (ActivityNotFoundException e){
            e.printStackTrace();
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param context : Activity/Fragment Context
     */
    public static void rateThisApp(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent launchMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(launchMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    /**
     * @param context     : Activity/Fragment Context
     * @param packageName : Name of the package
     */
    public static void launchMarket(Context context, String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent mIntent = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(mIntent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}
