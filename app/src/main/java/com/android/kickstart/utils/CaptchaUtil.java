package com.android.kickstart.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class CaptchaUtil {

    public CaptchaUtil() {

    }

    /**
     * @param digit : number of digit
     * @return : Random generated string
     */
    public static String generateCaptcha(int digit) {
        String CapchaString = "";
        String[] CharacterSet = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "a", "B", "b", "C", "c", "D", "d", "E", "e", "F", "f", "G", "g", "H", "h", "I", "i", "J",
                "j", "K", "k", "L", "l", "M", "m", "N", "n", "O", "o", "P", "p", "Q", "q", "R", "r", "S", "s", "T", "t", "U", "u", "V", "v", "W", "w", "X", "x", "Y", "y", "Z", "z"};
        Random mRandom = new Random();
        for (int i = 0; i < digit; i++) {
            CapchaString += CharacterSet[mRandom.nextInt(62)] + " ";
        }
        return CapchaString;
    }

    /**
     * @param charSequence : CharSequence
     * @return : Bitmap of CharSequence
     */
    public static Bitmap generateCaptchaBitmap(Context context, CharSequence charSequence) {
        LinearLayout view = new LinearLayout(context);
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        view.setOrientation(LinearLayout.HORIZONTAL);

        Random mRandom = new Random();
        TextView textView;
        for (int i = 0; i < charSequence.length(); i++) {
            textView = new TextView(context);
            textView.setPadding(5, 5, 5, 5);
            textView.setText(charSequence.charAt(i));
            textView.setTextColor(Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256)));
            view.addView(textView);
        }

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }
}
