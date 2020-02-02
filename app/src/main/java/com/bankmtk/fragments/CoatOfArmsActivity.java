package com.bankmtk.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import androidx.annotation.Nullable;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class CoatOfArmsActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation ==
        ORIENTATION_LANDSCAPE){
            finish();
            return;
        }
        if (savedInstanceState == null){
            CoatOfArmsFragment details = new CoatOfArmsFragment();
             details.setArguments(getIntent().getExtras());
             //getFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
//question  api, build for 29

        }
    }
}
