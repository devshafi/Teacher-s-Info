package com.jaap.teacherhinfo.models;

public class Person {

    private String fullName;
    private String designation;
    private String address;
    private String expertiseIn;
    private String emailAddress;
    private String mobileNo;

    public Person(String fullName, String designation, String address, String expertiseIn, String emailAddress, String mobileNo) {
        this.fullName = fullName;
        this.designation = designation;
        this.address = address;
        this.expertiseIn = expertiseIn;
        this.emailAddress = emailAddress;
        this.mobileNo = mobileNo;
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
                "fullName='" + fullName + '\'' +
                ", designation='" + designation + '\'' +
                ", address='" + address + '\'' +
                ", expertiseIn='" + expertiseIn + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                '}';
    }
}