package com.android.kickstart.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

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

    public static boolean needLoadMore(final GridLayoutManager layoutManager) {
        // int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        // int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

        return needLoadMore(lastVisibleItemPosition, totalItemCount);
    }

    public static boolean needLoadMore(final LinearLayoutManager layoutManager) {
        // int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        // int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

        return needLoadMore(lastVisibleItemPosition, totalItemCount);
    }

    public static boolean cancelAsyncTask(AsyncTask asyncTask) {
        if (asyncTask != null && asyncTask.getStatus().equals(AsyncTask.Status.RUNNING)) {
            asyncTask.cancel(true);
        }
        return false;
    }

    /**
     * Launch web page into third party app.
     */
    public static void openWebPage(final Activity activity, final String url) {
        if (activity != null && !TextUtils.isEmpty(url)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            if (intent.resolveActivity(activity.getPackageManager()) != null) {
                activity.startActivity(intent);
            }
        }
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
}