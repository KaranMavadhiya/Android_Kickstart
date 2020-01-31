package com.android.kickstart.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.kickstart.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

public class CommonUtil {

    /**
     * Called to check permission(In Android M and above versions only)
     *
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
    public static boolean needLoadMore(GridLayoutManager layoutManager) {
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
    public static boolean needLoadMore(LinearLayoutManager layoutManager) {
        // int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        // int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

        return needLoadMore(lastVisibleItemPosition, totalItemCount);
    }

    /**
     * @param value  : string value
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
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }
    
    // Converts the number to K, M suffix
    // Ex: 5500 will be displayed as 5.5k
    public static String convertToSuffix(long count) {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format("%.1f%c",
                count / Math.pow(1000, exp),
                "kmgtpe".charAt(exp - 1));
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    /**
     * @param context Activity/Fragment Context
     * @return : Phone email address
     */
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

    public static String stringToMd5(String string) throws NoSuchAlgorithmException {
        // Create MD5 Hash
        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
        digest.update(string.getBytes());
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

    public static String getSHA(Context context) throws PackageManager.NameNotFoundException, NoSuchAlgorithmException {
        @SuppressLint("PackageManagerGetSignatures") PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
        for (Signature signature : info.signatures) {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(signature.toByteArray());
            return Base64.encodeToString(md.digest(), Base64.DEFAULT);
        }
        return null;
    }

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

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public static boolean isAppInstalled(Context context, String packageName) {
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


    public void copyToClipBoard(Context context, String message) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(context.getResources().getString(R.string.app_name), message);
        clipboard.setPrimaryClip(clip);

        Toast.makeText(context, "Copy message to clipboard successfully.", Toast.LENGTH_LONG).show();
    }

    public static String readFileFromAssets(Context context, String fileName) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream jsonInputStream = context.getAssets().open(fileName);
        BufferedReader in = new BufferedReader(new InputStreamReader(jsonInputStream, "UTF-8"));
        String result;
        while ((result = in.readLine()) != null) {
            stringBuilder.append(result);
        }
        in.close();
        return stringBuilder.toString();
    }
    
    public static String getMimeType(Uri uri) {           
    String mimeType = null;
    if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
        ContentResolver cr = getAppContext().getContentResolver();
        mimeType = cr.getType(uri);
    } else {
        String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
                .toString());
        mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                fileExtension.toLowerCase());
    }
    return mimeType;
    }
}
