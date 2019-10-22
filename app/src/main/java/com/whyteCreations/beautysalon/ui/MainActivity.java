package com.whyteCreations.beautysalon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.whyteCreations.beautysalon.Fragment.HomeFragment;
import com.whyteCreations.beautysalon.Fragment.ProfileFragment;
import com.whyteCreations.beautysalon.R;
import com.whyteCreations.beautysalon.Search.SearchFragment;
import com.whyteCreations.beautysalon.Utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    private boolean clickAgainToExit=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        loadFragment(new HomeFragment());

        init();


    }

    private void loadFragment(HomeFragment fragment) {
        String backStateName = fragment.getClass().getName();
        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null) { //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.fl_container, fragment, backStateName);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(backStateName);
            ft.commit();
        }

    }

    private void init() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        loadFragment(new HomeFragment());
                        return true;
                    case R.id.navigation_profile:
                       loadFragment(new ProfileFragment());
                        return true;
                    case R.id.navigation_search:
                        loadFragment(new SearchFragment());
                        return true;
                    case R.id.navigation_notification:
                        // TODO
                        return true;
                    case R.id.navigation_favourite:
                        // TODO
                        return true;
                }
                return false;
            }
        });
    }

    private void loadFragment(ProfileFragment profileFragment) {
        String backStateName = profileFragment.getClass().getName();
        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null) { //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.fl_container, profileFragment, backStateName);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    private void loadFragment(SearchFragment fragment) {
        String backStateName = fragment.getClass().getName();
        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null) { //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.fl_container, fragment, backStateName);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }


    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        // fragmentManager.popBackStackImmediate();
        if (fragmentManager.getBackStackEntryCount() == 1) {

            if (clickAgainToExit) {

                super.onBackPressed();
                finish();
                return;
            }
            clickAgainToExit = true;
            AppUtils.ShortToast(MainActivity.this, getResources().getString(R.string.app_backpress));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    clickAgainToExit = false;
                }
            }, 2000);
        } else {

            super.onBackPressed();
        }
    }
}
