package com.sourceit.task1.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sourceit.task1.R;
import com.sourceit.task1.utils.L;

public class MainActivity extends AppCompatActivity {

    public int position;
    public boolean infoCreatedOrChanged;

    public void setPosition(int position) {
        this.position = position;
    }

    public void setInfoCreatedOrChanged(boolean infoCreatedOrChanged) {
        this.infoCreatedOrChanged = infoCreatedOrChanged;
    }

    public Intent intent;
    private MainFragment mainFragment;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        intent = getIntent();

        if (intent.hasExtra("position")) {
            position = intent.getIntExtra("position", 0);
        }

        if (savedInstanceState != null) {
            position = savedInstanceState.getInt("position");
            infoCreatedOrChanged = savedInstanceState.getBoolean("change");
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            L.d("position" + position);
            if (infoCreatedOrChanged == true) {
                L.d("change " + infoCreatedOrChanged);
                createInfoActivity();
            }
        }

        L.d("intent has position = " + intent.hasExtra("position"));
        L.d("getIntent MainActivity = " + intent.getIntExtra("position", 0));
        if (intent.hasExtra("position")) {
            L.d("intent in main has extra position");
        } else {
            L.d("intent in main hasnt extra position");
        }

        if (mainFragment == null) {
            L.d("activity hasnt fragment list");
        } else L.d("activity has fragment list");

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        mainFragment = MainFragment.newInstance(position);
        fragmentTransaction.add(R.id.main_activity_container, mainFragment, MainFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }

    public void createInfoActivity() {
        Intent intentToInfoActivity = new Intent(this, Information.class);
        intentToInfoActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intentToInfoActivity.putExtra("position", position);
        L.d("start Activity Information, put position = " + position);
        startActivity(intentToInfoActivity);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);
        outState.putBoolean("change", infoCreatedOrChanged);
    }
}
