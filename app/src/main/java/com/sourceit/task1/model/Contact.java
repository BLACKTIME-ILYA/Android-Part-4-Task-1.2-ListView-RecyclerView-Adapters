package com.sourceit.task1.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 04.02.2016.
 */
public class Contact implements Parcelable {
    private String name;
    private String email;
    private String adress;
    private int image;

    public Contact(String name, String email, String adress, int image) {
        this.name = name;
        this.email = email;
        this.adress = adress;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAdress() {
        return adress;
    }

    public int getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.adress);
        dest.writeInt(this.image);
    }

    private Contact(Parcel in) {
        this.name = in.readString();
        this.email = in.readString();
        this.adress = in.readString();
        this.image = in.readInt();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}