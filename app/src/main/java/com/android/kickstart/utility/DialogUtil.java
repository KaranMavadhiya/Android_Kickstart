package com.android.kickstart.utility;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.text.Spanned;

public class DialogUtil {

    /**
     * @param context : Context of Activity or Fragment
     * @param title : Title that you need to show in AlertDialog
     * @param message : Message that you need to show in AlertDialog
     * @param positiveButtonName : Positive button name
     */
    public static void showAlertDialog(Context context, String title, String message, String positiveButtonName) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveButtonName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    /**
     * @param context : Context of Activity or Fragment
     * @param title : Title that you need to show in AlertDialog
     * @param message : Message that you need to show in AlertDialog
     * @param positiveButtonName : Positive button name
     * @param isCancelable : true for cancelable, false for non cancelable
     */
    public static void showAlertDialog(Context context, String title, String message, String positiveButtonName, boolean isCancelable) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setCancelable(isCancelable);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveButtonName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    /**
     * @param context : Context of Activity or Fragment
     * @param title : Title that you need to show in AlertDialog
     * @param message : Spannable message that you need to show in AlertDialog
     * @param positiveButtonName : Positive button name
     * @param mAlertDialogListener : AlertDialog callback listener
     */
    public static void showAlertDialog(Context context, String title, Spanned message, String positiveButtonName, final AlertDialogListener mAlertDialogListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveButtonName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mAlertDialogListener.onPositiveButtonClick(dialog, which);
            }
        });
        alertDialog.show();
    }

    /**
     * @param context : Context of Activity or Fragment
     * @param title : Title that you need to show in AlertDialog
     * @param message : Message that you need to show in AlertDialog
     * @param positiveButtonName : Positive button name
     * @param mAlertDialogListener : AlertDialog callback listener
     */
    public static void showAlertDialog(Context context, String title, String message, String positiveButtonName, final AlertDialogListener mAlertDialogListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveButtonName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mAlertDialogListener.onPositiveButtonClick(dialog, which);
            }
        });
        alertDialog.show();
    }

    /**
     * @param context : Context of Activity or Fragment
     * @param title : Title that you need to show in AlertDialog
     * @param message : Spannable message that you need to show in AlertDialog
     * @param positiveButtonName : Positive button name
     * @param isCancelable : true for cancelable, false for non cancelable
     * @param mAlertDialogListener : AlertDialog callback listener
     */
    public static void showAlertDialog(Context context, String title, Spanned message, String positiveButtonName, boolean isCancelable, final AlertDialogListener mAlertDialogListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setCancelable(isCancelable);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveButtonName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mAlertDialogListener.onPositiveButtonClick(dialog, which);
            }
        });
        alertDialog.show();
    }

    /**
     * @param context : Context of Activity or Fragment
     * @param title : Title that you need to show in AlertDialog
     * @param message : Message that you need to show in AlertDialog
     * @param positiveButtonName : Positive button name
     * @param isCancelable : true for cancelable, false for non cancelable
     * @param mAlertDialogListener : AlertDialog callback listener
     */
    public static void showAlertDialog(Context context, String title, String message, String positiveButtonName, boolean isCancelable, final AlertDialogListener mAlertDialogListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setCancelable(isCancelable);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveButtonName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mAlertDialogListener.onPositiveButtonClick(dialog, which);
            }
        });
        alertDialog.show();
    }


    public interface AlertDialogListener {
        public void onPositiveButtonClick(DialogInterface dialog, int which);
    }


    /**
     * @param context : Context of Activity or Fragment
     * @param title : Title that you need to show in AlertDialog
     * @param message : Message that you need to show in AlertDialog
     * @param positiveButtonName : Positive button name
     * @param negativeButtonName : Negative button name
     */
    public static void showConfirmDialog(Context context, String title, String message, String positiveButtonName, String negativeButtonName) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveButtonName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.setNegativeButton(negativeButtonName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    /**
     * @param context : Context of Activity or Fragment
     * @param title : Title that you need to show in AlertDialog
     * @param message : Message that you need to show in AlertDialog
     * @param positiveButtonName : Positive button name
     * @param negativeButtonName : Negative button name
     * @param mDialogListener : AlertDialog callback listener
     */
    public static void showConfirmDialog(Context context, String title, String message, String positiveButtonName, String negativeButtonName, final ConfirmDialogListener mDialogListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveButtonName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mDialogListener.onPositiveButtonClick(dialog, which);
            }
        });
        alertDialog.setNegativeButton(negativeButtonName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mDialogListener.onNegativeButtonClick(dialog, which);
            }
        });
        alertDialog.show();
    }

    /**
     * @param context : Context of Activity or Fragment
     * @param title : Title that you need to show in AlertDialog
     * @param message : Message that you need to show in AlertDialog
     * @param positiveButtonName : Positive button name
     * @param negativeButtonName : Negative button name
     * @param isCancelable : true for cancelable, false for non cancelable
     * @param mDialogListener : AlertDialog callback listener
     */
    public static void showConfirmDialog(Context context, String title, String message, String positiveButtonName, String negativeButtonName,Boolean isCancelable, final ConfirmDialogListener mDialogListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(isCancelable);
        alertDialog.setPositiveButton(positiveButtonName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mDialogListener.onPositiveButtonClick(dialog, which);
            }
        });
        alertDialog.setNegativeButton(negativeButtonName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mDialogListener.onNegativeButtonClick(dialog, which);
            }
        });
        alertDialog.show();
    }

    /**
     * @param context : Context of Activity or Fragment
     * @param title : Title that you need to show in AlertDialog
     * @param message : Spannable message that you need to show in AlertDialog
     * @param positiveButtonName : Positive button name
     * @param negativeButtonName : Negative button name
     * @param mDialogListener : AlertDialog callback listener
     */
    public static void showConfirmDialog(Context context, String title, Spanned message, String positiveButtonName, String negativeButtonName, final ConfirmDialogListener mDialogListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(positiveButtonName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mDialogListener.onPositiveButtonClick(dialog, which);
            }
        });
        alertDialog.setNegativeButton(negativeButtonName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mDialogListener.onNegativeButtonClick(dialog, which);
            }
        });
        alertDialog.show();
    }

    /**
     * @param context : Context of Activity or Fragment
     * @param title : Title that you need to show in AlertDialog
     * @param message : Spannable message that you need to show in AlertDialog
     * @param positiveButtonName : Positive button name
     * @param negativeButtonName : Negative button name
     * @param isCancelable : true for cancelable, false for non cancelable
     * @param mDialogListener : AlertDialog callback listener
     */
    public static void showConfirmDialog(Context context, String title, Spanned message, String positiveButtonName, String negativeButtonName,Boolean isCancelable, final ConfirmDialogListener mDialogListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(isCancelable);
        alertDialog.setPositiveButton(positiveButtonName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mDialogListener.onPositiveButtonClick(dialog, which);
            }
        });
        alertDialog.setNegativeButton(negativeButtonName, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mDialogListener.onNegativeButtonClick(dialog, which);
            }
        });
        alertDialog.show();
    }

    public interface ConfirmDialogListener {
        public void onPositiveButtonClick(DialogInterface dialog, int which);

        public void onNegativeButtonClick(DialogInterface dialog, int which);
    }

    /**
     * @param context : Context of Activity or Fragment
     * @param title : Title that you need to show in AlertDialog
     * @param message : Message that you need to show in AlertDialog
     */
    public static void GPSAlertDialog(final Context context, String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }
}