package com.sourceit.task1.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sourceit.task1.R;
import com.sourceit.task1.model.Contact;
import com.sourceit.task1.utils.L;

public class MainFragment extends Fragment {

    private View view;
    private InfoFragment infoFragment;

    private final int DEFAULT = 20;
    private final int ADD_ONE = 1;
    private int position_use;

    private RecyclerView contact_list;
    private Button add;

    public static MainFragment newInstance(int position) {
        MainFragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        position_use = getArguments().getInt("position");
        init();

        if (Contacts.contacts == null) {
            Contacts.contacts = new MyArrayList<>();
            addContacts(DEFAULT);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        contact_list.setLayoutManager(layoutManager);
        contact_list.setAdapter(new RecyclerViewAdapter(Contacts.contacts, new OnItemClickWatcher<Contact>() {
            @Override
            public void onItemClick(View v, int position, Contact item) {
                position_use = position;
                openInformation(position_use);
            }
        }));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContacts(ADD_ONE);
                contact_list.getAdapter().notifyDataSetChanged();
            }
        });

        if (((MainActivity) getActivity()).intent.hasExtra("position") || ((MainActivity) getActivity()).infoCreatedOrChanged) {
            openInformation(position_use);
        }
        return view;
    }

    private void init() {
        contact_list = (RecyclerView) view.findViewById(R.id.contact_list);
        add = (Button) view.findViewById(R.id.button_add);
    }

    private void addContacts(int count) {
        for (int i = 0; i < count; i++) {
            Contacts.numberOfContact++;
            L.d("numberOfContact++" + Contacts.numberOfContact);
            Contacts.contacts.add(new Contact("Name" + Contacts.numberOfContact, "Email@" + Contacts.numberOfContact, "Adress " + Contacts.numberOfContact, R.drawable.ic_contact_phone_black_48dp));
        }
    }

    private void openInformation(int position) {

        ((MainActivity) getActivity()).setInfoCreatedOrChanged(true);
        L.d("main fragment set position = " + position_use);
        ((MainActivity) getActivity()).setPosition(position_use);

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
            infoFragment = InfoFragment.newInstance(position_use);
            fragmentTransaction.add(R.id.info_activity_container, infoFragment, InfoFragment.class.getSimpleName());
            fragmentTransaction.commit();
        } else {
            infoFragment.setFields(position_use);
        }
    }
}
