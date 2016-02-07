package com.sourceit.task1.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sourceit.task1.R;
import com.sourceit.task1.model.Contact;
import com.sourceit.task1.utils.L;

public class MainActivity extends AppCompatActivity {

    int position;

    Contact contact;

    Intent intent;
    boolean change;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        if (savedInstanceState != null) {
            position = savedInstanceState.getInt("position");
            change = savedInstanceState.getBoolean("change");
            contact = savedInstanceState.getParcelable("contact");
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            L.d("position" + position);
            if (change == true) {
                L.d("change " + change);
                createInfoActivity();
            }
        }

        intent = getIntent();
        L.d("intent has position = " + intent.hasExtra("position"));
        L.d("getIntent MainActivity = " + intent.getIntExtra("position", 0));
        if (intent.hasExtra("position") != false) {

            position = intent.getIntExtra("position", 0);
        }


        if (mainFragment == null) {
            L.d("activity hasnt fragment list");
        } else L.d("activity has fragment list");
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        mainFragment = MainFragment.newInstance();
        fragmentTransaction.add(R.id.main_activity_container, mainFragment, MainFragment.class.getSimpleName());
        fragmentTransaction.commit();

    }

    public void createInfoActivity() {
        //put in activity
        intent = new Intent(this, Information.class);

        if (contact != null) {
            intent.putExtra("fragment_info", contact);
        }

        intent.putExtra("position", position);

        L.d("start Activity Information, put position = " + position);

        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);
        outState.putBoolean("change", change);
        outState.putParcelable("contact", contact);
    }
}
