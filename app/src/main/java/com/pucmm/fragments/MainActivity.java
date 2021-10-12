package com.pucmm.fragments;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.pucmm.fragments.Fragments.BodyFragment;
import com.pucmm.fragments.Fragments.TitlesFragment;

public class MainActivity extends FragmentActivity implements TitlesFragment.OnHeadlineSelectedListener {

    private boolean hasCamera = false;
    private boolean hasPermissions = false;
    private static final Integer REQUEST_CODE_CAMERA = 1;
    private static final Integer REQUEST_CODE_READ_STORAGE = 2;
    private static final Integer REQUEST_CODE_WRITE_STORAGE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            hasCamera = true;
        }
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            hasPermissions = true;
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.CAMERA }, REQUEST_CODE_CAMERA);
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.READ_EXTERNAL_STORAGE }, REQUEST_CODE_READ_STORAGE);
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQUEST_CODE_WRITE_STORAGE);
        }


        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            TitlesFragment firstFragment = new TitlesFragment();
            firstFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,firstFragment).commit();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onArticleSelected(int position) {
        BodyFragment af = (BodyFragment) getSupportFragmentManager().findFragmentById(R.id.article_fragment);

        if (af != null) {
            af.updateArticleView(position);
        } else {
            BodyFragment newFragment = new BodyFragment();
            Bundle args = new Bundle();

            args.putInt(BodyFragment.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}