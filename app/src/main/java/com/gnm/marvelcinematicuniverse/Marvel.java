package com.gnm.marvelcinematicuniverse;

import android.os.Parcel;
import android.os.Parcelable;

public class Marvel implements Parcelable {
    private String name, year, photo, isi, quoter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getQuoter() {
        return quoter;
    }

    public void setQuoter(String quoter) {
        this.quoter = quoter;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.year);
        dest.writeString(this.photo);
        dest.writeString(this.isi);
        dest.writeString(this.quoter);
    }

    public Marvel() {
    }
    protected Marvel(Parcel in) {
        this.name = in.readString();
        this.year = in.readString();
        this.photo = in.readString();
        this.isi = in.readString();
        this.quoter = in.readString();
    }
    public static final Parcelable.Creator<Marvel> CREATOR = new Parcelable.Creator<Marvel>() {
        @Override
        public Marvel createFromParcel(Parcel source) {
            return new Marvel(source);
        }
        @Override
        public Marvel[] newArray(int size) {
            return new Marvel[size];
        }
    };
}
