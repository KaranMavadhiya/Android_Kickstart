package com.android.kickstart.utility;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CommonUtil {

    /*
     * Called to check permission(In Android M and above versions only)
     * @param permission, which we need to pass
     * @return true, if permission is granted else false
     */
    public static boolean checkForPermission(final Context context, final String permission) {
        int result = ContextCompat.checkSelfPermission(context, permission);
        //If permission is granted then it returns 0 as result
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private static boolean needLoadMore(int lastVisibleItemPosition, int totalItemCount) {
        final int max = (int) (totalItemCount * 0.8);
        return (lastVisibleItemPosition) >= max && lastVisibleItemPosition >= 0;
    }

    /**
     * @param layoutManager : GridLayoutManager of Recycler View
     * @return true if required load more
     */
    public static boolean needLoadMore(final GridLayoutManager layoutManager) {
        // int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        // int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

        return needLoadMore(lastVisibleItemPosition, totalItemCount);
    }

    /**
     * @param layoutManager : LinearLayoutManager of Recycler View
     * @return true if required load more
     */
    public static boolean needLoadMore(final LinearLayoutManager layoutManager) {
        // int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        // int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

        return needLoadMore(lastVisibleItemPosition, totalItemCount);
    }

    /**
     * @param value : string value
     * @param format : conversion format
     * @return : DecimalFormat string
     */
    public static String formatDecimal(String value, String format) {
        String input = value.replaceAll(",", "");
        DecimalFormat mDecimalFormat = new DecimalFormat(format);
        return mDecimalFormat.format(Double.valueOf(input));
    }

    /**
     * density equals
     * .75 on ldpi (120 dpi)
     * 1.0 on mdpi (160 dpi; baseline)
     * 1.5 on hdpi (240 dpi)
     * 2.0 on xhdpi (320 dpi)
     * 3.0 on xxhdpi (480 dpi)
     * 4.0 on xxxhdpi (640 dpi)
     **/

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    /**
     * @param context : Context of Activity or Fragment
     * @param url : URL that you want to open
     */
    public static void openWebPage(final Context context, final String url) {
        if (context != null && !TextUtils.isEmpty(url)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            }
        }
    }

    /**
     * @param context : Context of Activity or Fragment
     * @param TO : email address
     * @param CC : email address
     * @param Subject : subject of email
     * @param Body : predefined body of email
     * @param filePaths : attachment path
     */
    public static void sendMailFromGmail(Context context, String TO, String CC, String Subject, String Body, ArrayList<String> filePaths) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        emailIntent.setType("message/rfc822");
        emailIntent.setData(Uri.parse(TO.toLowerCase().trim()));
        emailIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{TO});
        emailIntent.putExtra(Intent.EXTRA_CC, new String[]{CC});
        ArrayList<Uri> uris = new ArrayList<Uri>();
        // convert from paths to Android friendly Parcelable Uri's
        if (filePaths != null) {

            for (String file : filePaths) {
                File fileIn = new File(file);
                Uri u = Uri.fromFile(fileIn);
                uris.add(u);
            }
        }
        emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, Subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, Body);
        try {
            context.startActivity(emailIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getDefaultEmail(Context context) {
        AccountManager accountManager = AccountManager.get(context);
        Account account = getAccount(accountManager);

        if (account == null) {
            return null;
        } else {
            return account.name;
        }
    }

    private static Account getAccount(AccountManager accountManager) {
        Account[] accounts = accountManager.getAccountsByType("com.google");
        Account account;
        if (accounts.length > 0) {
            account = accounts[0];
        } else {
            account = null;
        }
        return account;
    }

    public static String stringToMd5(final String mString) throws NoSuchAlgorithmException {
        // Create MD5 Hash
        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
        digest.update(mString.getBytes());
        byte messageDigest[] = digest.digest();

        // Create Hex String
        StringBuilder hexStringBuilder = new StringBuilder();
        for (byte aMessageDigest : messageDigest) {
            String HexString = Integer.toHexString(0xFF & aMessageDigest);
            while (HexString.length() < 2)
                HexString = "0" + HexString;
            hexStringBuilder.append(HexString);
        }
        return hexStringBuilder.toString();
    }

    // GET SHA
    public static String getSHA(Context context) throws PackageManager.NameNotFoundException, NoSuchAlgorithmException {
        PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
        for (Signature signature : info.signatures) {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(signature.toByteArray());
            return Base64.encodeToString(md.digest(), Base64.DEFAULT);
        }
        return null;
    }

    // SET TYPE FACE
    public static void setTypeFace(Context context, View mView, String tfName) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), tfName);
        if (mView instanceof TextView) {
            TextView mTextView = (TextView) mView;
            mTextView.setTypeface(font);
        } else if (mView instanceof EditText) {
            EditText mEditText = (EditText) mView;
            mEditText.setTypeface(font);
        } else if (mView instanceof Button) {
            Button mButton = (Button) mView;
            mButton.setTypeface(font);
        }
    }

    // GET STATUS BAR HEIGHT
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    // GET NAVIGATION BAR HEIGHT
    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    // CHECK APP INSTALLED OR NOT
    public static boolean isAppInstalled(Context context,String packageName) {
        PackageManager pm = context.getPackageManager();
        pm.getLaunchIntentForPackage(packageName);
        try {
            // this line will trigger exception if not found
            pm.getApplicationInfo(packageName, 0);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    // RATE THIS APP
    public static void rateThisApp(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent launchMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(launchMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    // LAUNCH APP IN MARKET
    public static void launchMarket(Context context,String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent mIntent = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(mIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}