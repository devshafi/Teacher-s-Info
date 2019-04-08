package com.jaap.teacherhinfo.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Person extends RealmObject implements Parcelable {

    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private byte[] profileImage;
    private String fullName;
    private String designation;
    private String address;
    private String expertiseIn;
    private String emailAddress;
    private String mobileNo;

    // no arg constructor
    public Person(){

    }

    // constructor with default parameter
    public Person(String fullName, String designation, String address, String expertiseIn, String emailAddress, String mobileNo) {
        this.fullName = fullName;
        this.designation = designation;
        this.address = address;
        this.expertiseIn = expertiseIn;
        this.emailAddress = emailAddress;
        this.mobileNo = mobileNo;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }

    public String getId(){
        return this.id;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExpertiseIn() {
        return expertiseIn;
    }

    public void setExpertiseIn(String expertiseIn) {
        this.expertiseIn = expertiseIn;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", profileImage=" + Arrays.toString(profileImage) +
                ", fullName='" + fullName + '\'' +
                ", designation='" + designation + '\'' +
                ", address='" + address + '\'' +
                ", expertiseIn='" + expertiseIn + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeByteArray(this.profileImage);
        dest.writeString(this.fullName);
        dest.writeString(this.designation);
        dest.writeString(this.address);
        dest.writeString(this.expertiseIn);
        dest.writeString(this.emailAddress);
        dest.writeString(this.mobileNo);
    }

    protected Person(Parcel in) {
        this.id = in.readString();
        this.profileImage = in.createByteArray();
        this.fullName = in.readString();
        this.designation = in.readString();
        this.address = in.readString();
        this.expertiseIn = in.readString();
        this.emailAddress = in.readString();
        this.mobileNo = in.readString();
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}