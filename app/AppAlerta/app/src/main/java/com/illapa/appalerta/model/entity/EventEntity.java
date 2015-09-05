package com.illapa.appalerta.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by emedinaa on 05/09/15.
 */
public class EventEntity implements Parcelable {

    private double lat;
    private double lng;
    private String uuid;
    private int category;
    private String obs;

    public EventEntity( String uuid, int category, double lat, double lng,String obs) {
        this.lat = lat;
        this.lng = lng;
        this.uuid = uuid;
        this.category = category;
        this.obs = obs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.lat);
        dest.writeDouble(this.lng);
        dest.writeString(this.uuid);
        dest.writeInt(this.category);
        dest.writeString(this.obs);
    }

    public EventEntity() {
    }

    protected EventEntity(Parcel in) {
        this.lat = in.readDouble();
        this.lng = in.readDouble();
        this.uuid = in.readString();
        this.category = in.readInt();
        this.obs = in.readString();
    }

    public static final Parcelable.Creator<EventEntity> CREATOR = new Parcelable.Creator<EventEntity>() {
        public EventEntity createFromParcel(Parcel source) {
            return new EventEntity(source);
        }

        public EventEntity[] newArray(int size) {
            return new EventEntity[size];
        }
    };

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
