package com.android.kickstart.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {

    private static int TYPE_WIFI;
    private static int TYPE_MOBILE;
    private static int TYPE_NOT_CONNECTED;

    static {
        TYPE_NOT_CONNECTED = 0;
        TYPE_MOBILE = 2;
        TYPE_WIFI = 1;
    }

    /**
     *
     * @param context to check network available or not
     * @return 0 if not connected 1 if connected with WIFI 2 if connected with mobile
     */
    public static int getConnectivityStatus(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        if (null != activeNetwork && activeNetwork.isConnected()) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    /**
     * @param context to check network available or not
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
}