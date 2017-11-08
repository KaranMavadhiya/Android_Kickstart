package com.android.kickstart.fragment;

import android.content.Context;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.android.kickstart.R;

import butterknife.BindView;
import butterknife.OnClick;

public class MainFragment extends BaseFragment {

    private TextView tvMain;

    @Override
    public int getFragmentView() {
        return R.layout.frament_main;
    }

    @Override
    protected void initializeComponent(View view) {

        tvMain = (TextView) view.findViewById(R.id.frament_main_tv_main);
        tvMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        /*
         * Logic for hide keyboard on click
         */
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
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

            case R.id.frament_main_tv_main:
                Snackbar.make(view, "Fragment Text clicked", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }
}
