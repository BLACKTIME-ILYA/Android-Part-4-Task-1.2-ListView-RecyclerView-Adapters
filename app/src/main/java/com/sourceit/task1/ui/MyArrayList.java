package com.sourceit.task1.ui;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by User on 05.02.2016.
 */
public class MyArrayList<Contact> extends ArrayList<Contact> implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.modCount);
    }

    public MyArrayList() {
    }

    private MyArrayList(Parcel in) {
        this.modCount = in.readInt();
    }

    public static final Creator<MyArrayList> CREATOR = new Creator<MyArrayList>() {
        public MyArrayList createFromParcel(Parcel source) {
            return new MyArrayList(source);
        }

        public MyArrayList[] newArray(int size) {
            return new MyArrayList[size];
        }
    };
}
