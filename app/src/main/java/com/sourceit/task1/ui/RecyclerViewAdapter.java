package com.sourceit.task1.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sourceit.task1.R;
import com.sourceit.task1.model.Contact;

import java.util.ArrayList;

/**
 * Created by User on 11.02.2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<Contact> contacts;
    private OnItemClickWatcher<Contact> watcher;

    public RecyclerViewAdapter(ArrayList<Contact> contacts, OnItemClickWatcher<Contact> watcher) {
        this.contacts = contacts;
        this.watcher = watcher;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact, viewGroup, false);
        return new ViewHolder(v, watcher, contacts);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.name.setText(contacts.get(position).getName());
        holder.email.setText(contacts.get(position).getAdress());
        holder.image.setImageResource(contacts.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    @Override
    public void onClick(View v) {
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView email;
        private ImageView image;

        public ViewHolder(View item, final OnItemClickWatcher<Contact> watcher, final ArrayList<Contact> contacts) {
            super(item);
            name = (TextView) item.findViewById(R.id.contact_name);
            email = (TextView) item.findViewById(R.id.contact_email);
            image = (ImageView) item.findViewById(R.id.contact_icon);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    watcher.onItemClick(v, getAdapterPosition(), contacts.get(getAdapterPosition()));
                }
            });
        }
    }
}
