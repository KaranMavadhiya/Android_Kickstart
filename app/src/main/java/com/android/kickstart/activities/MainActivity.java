package com.android.kickstart.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.kickstart.R;
import com.android.kickstart.fragments.MainFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    public int getActivityView() {
        return R.layout.activity_main;
    }

    @Override
    public void initializeComponents() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(R.id.activity_main_fragment_container, getFragmentManager(), new MainFragment(), true, false);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle;
        switch (item.getItemId()) {

            case R.id.nav_camera:
                //replace MainFragment to fragment_container
                bundle = new Bundle();
                bundle.putString("Title","Import");
                mainFragment.setArguments(bundle);
                replaceFragment(R.id.activity_main_fragment_container, getFragmentManager(),mainFragment, true, false);
                break;
            case R.id.nav_gallery:
                //replace MainFragment to fragment_container
                bundle = new Bundle();
                bundle.putString("Title", "Gallery");
                mainFragment.setArguments(bundle);
                replaceFragment(R.id.activity_main_fragment_container, getFragmentManager(),mainFragment, true, false);
                break;
            case R.id.nav_slideshow:
                //replace MainFragment to fragment_container
                bundle = new Bundle();
                bundle.putString("Title", "Slideshow");
                mainFragment.setArguments(bundle);
                replaceFragment(R.id.activity_main_fragment_container, getFragmentManager(),mainFragment, true, false);
                break;
            case R.id.nav_manage:
                //replace MainFragment to fragment_container
                bundle = new Bundle();
                bundle.putString("Title", "Tools");
                mainFragment.setArguments(bundle);
                replaceFragment(R.id.activity_main_fragment_container, getFragmentManager(),mainFragment, true, false);
                break;
            case R.id.nav_share:
                //replace MainFragment to fragment_container
                bundle = new Bundle();
                bundle.putString("Title", "Share");
                mainFragment.setArguments(bundle);
                replaceFragment(R.id.activity_main_fragment_container, getFragmentManager(),mainFragment, true, false);
                break;
            case R.id.nav_send:
                //replace MainFragment to fragment_container
                bundle = new Bundle();
                bundle.putString("Title", "Send");
                mainFragment.setArguments(bundle);
                replaceFragment(R.id.activity_main_fragment_container, getFragmentManager(),mainFragment, true, false);
                break;
        }

        // Handle navigation view item clicks here.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
