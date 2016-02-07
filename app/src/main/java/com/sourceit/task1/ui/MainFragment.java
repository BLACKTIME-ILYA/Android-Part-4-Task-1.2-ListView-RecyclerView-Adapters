package com.sourceit.task1.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.sourceit.task1.R;
import com.sourceit.task1.model.Contact;
import com.sourceit.task1.utils.L;

import java.util.ArrayList;

public class MainFragment extends Fragment {


    public static final int MSG = 1;

    public static final int UPDATE_TIME = 500;
    private View view;

    private Intent intent;
    int position_use;

    private InfoFragment infoFragment;
    private Contact contact_temp;

    private final int DEFAULT = 20;
    private final int ADD_ONE = 1;

    private ArrayAdapter<Contact> adapter;
    private ArrayList<Contact> contacts;
    private int numberOfContact;

    public Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {

            if (msg.what == MSG) {
                setTo(contact_temp);
            }
        }
    };

    private ListView contact_list;
    private Button add;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        init();

        if (savedInstanceState != null) {
            contacts = savedInstanceState.getParcelable("contacts");
            numberOfContact = contacts.size();
        } else addContacts(DEFAULT);


        adapter = new MyAdapter(getContext(), contacts);
        contact_list.setAdapter(adapter);
        contact_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position_use = position;
                openInformation(position);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContacts(ADD_ONE);
                adapter.notifyDataSetChanged();
            }
        });

//        if (((MainActivity)getActivity()).intent.hasExtra("position") != false){
        if (((MainActivity) getActivity()).intent.hasExtra("position") != false) {
            openInformation(((MainActivity) getActivity()).position);
        }

        return view;
    }

    private void addContacts(int count) {
        for (int i = 0; i < count; i++) {
            numberOfContact++;
            L.d("numberOfContact++" + numberOfContact);
            contacts.add(new Contact("Name" + numberOfContact, "Email@" + numberOfContact, "Adress " + numberOfContact, R.drawable.ic_contact_phone_black_48dp));
        }
    }

    private void init() {
        contact_list = (ListView) view.findViewById(R.id.contact_list);
        add = (Button) view.findViewById(R.id.button_add);
        contacts = new MyArrayList<>();
    }

    private void openInformation(int position) {
        contact_temp = contacts.get(position);

        ((MainActivity) getActivity()).change = true;
        ((MainActivity) getActivity()).contact = contact_temp;
        ((MainActivity) getActivity()).position = position_use;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            ((MainActivity) getActivity()).createInfoActivity();

        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            createInfo();
        }
    }

    private void createInfo() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (infoFragment == null) {
            infoFragment = InfoFragment.newInstance();

            //number elements if back actifity has extra
            fragmentTransaction.add(R.id.info_activity_container, infoFragment, InfoFragment.class.getSimpleName());
            fragmentTransaction.commit();
            handler.sendEmptyMessageDelayed(MSG, UPDATE_TIME);
        } else {
            setTo(contact_temp);
        }
    }

    public void setTo(Contact contact) {
        infoFragment.setImage(contact.getImage());
        infoFragment.setName(contact.getName());
        infoFragment.setEmail(contact.getEmail());
        infoFragment.setAdress(contact.getAdress());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("contacts", (Parcelable) contacts);
    }


}
