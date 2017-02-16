package com.dyvoker.androidresume.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dyvoker.androidresume.R;
import com.dyvoker.androidresume.TabsAdapter;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_PHONE_CALL = 1;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        NestedScrollView scrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        scrollView.setFillViewport(true);

        //Set up "Call me" button
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        FloatingActionButton callMeButton = (FloatingActionButton) findViewById(R.id.callMeButton);
        callMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, R.string.call_me_request, Snackbar.LENGTH_LONG)
                        .setAction("CALL", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)
                                        != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(MainActivity.this,
                                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
                                } else {
                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getString(R.string.phone_number)));
                                    startActivity(intent);
                                }
                            }
                        });
                snackbar.show();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager());
        adapter.addFragment(new AboutMeFragment(), getString(R.string.about_me));
        adapter.addFragment(new ExperienceFragment(), getString(R.string.experience));
        adapter.addFragment(new ProjectsFragment(), getString(R.string.projects));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PHONE_CALL:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getString(R.string.phone_number)));
                    startActivity(intent);
                } else {
                    Snackbar.make(coordinatorLayout, R.string.call_phone_perm_denied, Snackbar.LENGTH_SHORT)
                            .show();
                }
        }
    }

}
