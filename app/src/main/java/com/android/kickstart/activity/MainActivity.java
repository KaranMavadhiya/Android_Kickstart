package com.android.kickstart.activity;

import android.content.Context;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.android.kickstart.R;
import com.android.kickstart.fragment.MainFragment;

public class MainActivity extends BaseActivity {


    @Override
    public int getActivityView() {
        return R.layout.activity_main;
    }

    @Override
    public void initializeComponents() {

        TextView tvMain = findViewById(R.id.activity_main_tv_main);
        tvMain.setOnClickListener(this);

        //replace MainFragment to fragment_container
        replaceFragment(R.id.activity_main_fragment_container,getFragmentManager(),new MainFragment(),false,false);
    }


    @Override
    public void onClick(View view) {
        /*
         * Logic for hide keyboard on click
         */
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }

        /*
         * Logic to Prevent the Launch Twice if User makes
         * the Tap(Click) very Fast.
         */
        if (SystemClock.elapsedRealtime() - lastClickedTime < MAX_CLICK_INTERVAL) {
            return;
        }
        lastClickedTime = SystemClock.elapsedRealtime();

        switch (view.getId()){

            case R.id.activity_main_tv_main:
                Snackbar.make(view, "Activity Text clicked", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }
}
