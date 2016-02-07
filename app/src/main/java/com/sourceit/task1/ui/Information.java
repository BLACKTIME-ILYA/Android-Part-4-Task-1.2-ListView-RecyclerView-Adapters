package com.sourceit.task1.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sourceit.task1.R;
import com.sourceit.task1.model.Contact;
import com.sourceit.task1.utils.L;

public class Information extends AppCompatActivity {

    MainFragment mainFragment;

    InfoFragment infoFragment;
    Contact contact;
    int position;


    Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            if (msg.what == mainFragment.MSG)
                infoFragment.setAdress(contact.getAdress());
            infoFragment.setName(contact.getName());
            infoFragment.setEmail(contact.getEmail());
            infoFragment.setImage(contact.getImage());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("position", position);
            L.d("information Activity put position = " + position);
            startActivity(intent);
        }

        Intent intent = getIntent();
        infoFragment = InfoFragment.newInstance();
        contact = intent.getParcelableExtra("fragment_info");
        position = intent.getIntExtra("position", 0);
        L.d("Information Activity position = " + position);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activity_info, infoFragment, InfoFragment.class.getSimpleName());
        fragmentTransaction.commit();
        L.d("add info fragment");

        handler.sendEmptyMessageDelayed(mainFragment.MSG, mainFragment.UPDATE_TIME);
    }
}
