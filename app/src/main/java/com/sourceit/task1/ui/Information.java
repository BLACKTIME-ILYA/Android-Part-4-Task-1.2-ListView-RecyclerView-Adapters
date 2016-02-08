package com.sourceit.task1.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sourceit.task1.R;
import com.sourceit.task1.utils.L;

public class Information extends AppCompatActivity {

    private InfoFragment infoFragment;

    private int position;

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        if (savedInstanceState != null) {
            position = savedInstanceState.getInt("position");
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            Intent intentToMainActivity = new Intent(this, MainActivity.class);
            intentToMainActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intentToMainActivity.putExtra("position", position);
            L.d("information Activity put position = " + position);
            startActivity(intentToMainActivity);
        }

        Intent intent = getIntent();

        if (intent.hasExtra("position")) {
            position = intent.getIntExtra("position", 0);
        }

        infoFragment = InfoFragment.newInstance(position);
        L.d("Information Activity position = " + position);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activity_info, infoFragment, InfoFragment.class.getSimpleName());
        fragmentTransaction.commit();
        L.d("add info fragment");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);
    }
}
