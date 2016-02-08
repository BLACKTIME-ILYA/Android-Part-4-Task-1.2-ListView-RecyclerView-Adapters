package com.sourceit.task1.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sourceit.task1.R;

public class InfoFragment extends Fragment {

    View view;

    private ImageView image;
    private TextView name;
    private TextView email;
    private TextView adress;

    private int position;

    public static InfoFragment newInstance(int position) {
        InfoFragment fragment = new InfoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    public InfoFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_info, container, false);

        init();

        position = getArguments().getInt("position");
        setFields(position);

        return view;
    }

    private void init() {
        image = (ImageView) view.findViewById(R.id.information_icon);
        name = (TextView) view.findViewById(R.id.information_name);
        email = (TextView) view.findViewById(R.id.information_email);
        adress = (TextView) view.findViewById(R.id.information_adress);
    }

    public void setFields(int position) {
        this.image.setImageResource(Contacts.contacts.get(position).getImage());
        this.name.setText(Contacts.contacts.get(position).getName());
        this.email.setText(Contacts.contacts.get(position).getEmail());
        this.adress.setText(Contacts.contacts.get(position).getAdress());
    }
}
