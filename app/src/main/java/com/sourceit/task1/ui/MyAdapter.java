package com.sourceit.task1.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sourceit.task1.R;
import com.sourceit.task1.model.Contact;
import com.sourceit.task1.utils.L;

import java.util.ArrayList;

/**
 * Created by User on 04.02.2016.
 */
public class MyAdapter extends ArrayAdapter<Contact> {
    public MyAdapter(Context context, ArrayList<Contact> objects) {
        super(context, R.layout.contact, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.contact, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) rowView.findViewById(R.id.contact_name);
            holder.email = (TextView) rowView.findViewById(R.id.contact_email);
            holder.image = (ImageView) rowView.findViewById(R.id.contact_icon);
            rowView.setTag(holder);
            L.d("row set Tag...");
        } else holder = (ViewHolder) rowView.getTag();

        Contact contact = getItem(position);
        holder.name.setText(contact.getName());
        holder.email.setText(contact.getEmail());
        holder.image.setImageResource(contact.getImage());

        return rowView;
    }

    class ViewHolder {
        public TextView name;
        public TextView email;
        public ImageView image;
    }
}
