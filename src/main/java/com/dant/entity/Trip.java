package com.dant.entity;

import java.io.Serializable;

public class Trip implements Serializable {
    private String vendor_name;
    private String PickUp_DateTime;
    private String DropOff_DateTime;
    private String Passager_count;
    private String Trip_Distance;
    private String Start_Lon;
    private String Start_Lat;
    private String Rate_Code;
    private String Store_and_forward;
    private String End_Lon;
    private String End_Lat;
    private String Payment_Type;
    private String Fare_Amt;
    private String Surcharge;
    private String mta_tax;
    private String Tip_amt;
    private String Tolls_Amt;
    private String Total_amt;

    public Trip(String[] tabline){
        this.vendor_name = tabline[0];
        PickUp_DateTime = tabline[1];
        DropOff_DateTime = tabline[2];
        Passager_count = tabline[3];
        Trip_Distance = tabline[4];
        Start_Lon = tabline[5];
        Start_Lat = tabline[6];
        Rate_Code = tabline[7];
        Store_and_forward = tabline[8];
        End_Lon = tabline[9];
        End_Lat = tabline[10];
        Payment_Type = tabline[11];
        Fare_Amt = tabline[12];
        Surcharge = tabline[13];
        this.mta_tax = tabline[14];
        Tip_amt = tabline[15];
        Tolls_Amt = tabline[16];
        Total_amt = tabline[17];
    }
    public Trip(String vendor_name, String pickUp_DateTime, String dropOff_DateTime, String passager_count, String trip_Distance, String start_Lon, String start_Lat, String rate_Code, String store_and_forward, String end_Lon, String end_Lat, String payment_Type, String fare_Amt, String surcharge, String mta_tax, String tip_amt, String tolls_Amt, String total_amt) {
        this.vendor_name = vendor_name;
        PickUp_DateTime = pickUp_DateTime;
        DropOff_DateTime = dropOff_DateTime;
        Passager_count = passager_count;
        Trip_Distance = trip_Distance;
        Start_Lon = start_Lon;
        Start_Lat = start_Lat;
        Rate_Code = rate_Code;
        Store_and_forward = store_and_forward;
        End_Lon = end_Lon;
        End_Lat = end_Lat;
        Payment_Type = payment_Type;
        Fare_Amt = fare_Amt;
        Surcharge = surcharge;
        this.mta_tax = mta_tax;
        Tip_amt = tip_amt;
        Tolls_Amt = tolls_Amt;
        Total_amt = total_amt;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "vendor_name='" + vendor_name + '\'' +
                ", PickUp_DateTime='" + PickUp_DateTime + '\'' +
                ", DropOff_DateTime='" + DropOff_DateTime + '\'' +
                ", Passager_count='" + Passager_count + '\'' +
                ", Trip_Distance='" + Trip_Distance + '\'' +
                ", Start_Lon='" + Start_Lon + '\'' +
                ", Start_Lat='" + Start_Lat + '\'' +
                ", Rate_Code='" + Rate_Code + '\'' +
                ", Store_and_forward='" + Store_and_forward + '\'' +
                ", End_Lon='" + End_Lon + '\'' +
                ", End_Lat='" + End_Lat + '\'' +
                ", Payment_Type='" + Payment_Type + '\'' +
                ", Fare_Amt='" + Fare_Amt + '\'' +
                ", Surcharge='" + Surcharge + '\'' +
                ", mta_tax='" + mta_tax + '\'' +
                ", Tip_amt='" + Tip_amt + '\'' +
                ", Tolls_Amt='" + Tolls_Amt + '\'' +
                ", Total_amt='" + Total_amt + '\'' +
                '}';
    }


    public String getVendor_name() {
        return vendor_name;
    }

    public String getPickUp_DateTime() {
        return PickUp_DateTime;
    }

    public String getDropOff_DateTime() {
        return DropOff_DateTime;
    }

    public String getPassager_count() {
        return Passager_count;
    }

    public String getTrip_Distance() {
        return Trip_Distance;
    }

    public String getStart_Lon() {
        return Start_Lon;
    }

    public String getStart_Lat() {
        return Start_Lat;
    }

    public String getRate_Code() {
        return Rate_Code;
    }

    public String getStore_and_forward() {
        return Store_and_forward;
    }

    public String getEnd_Lon() {
        return End_Lon;
    }

    public String getEnd_Lat() {
        return End_Lat;
    }

    public String getPayment_Type() {
        return Payment_Type;
    }

    public String getFare_Amt() {
        return Fare_Amt;
    }

    public String getSurcharge() {
        return Surcharge;
    }

    public String getMta_tax() {
        return mta_tax;
    }

    public String getTip_amt() {
        return Tip_amt;
    }

    public String getTolls_Amt() {
        return Tolls_Amt;
    }

    public String getTotal_amt() {
        return Total_amt;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public void setPickUp_DateTime(String pickUp_DateTime) {
        PickUp_DateTime = pickUp_DateTime;
    }

    public void setDropOff_DateTime(String dropOff_DateTime) {
        DropOff_DateTime = dropOff_DateTime;
    }

    public void setPassager_count(String passager_count) {
        Passager_count = passager_count;
    }

    public void setTrip_Distance(String trip_Distance) {
        Trip_Distance = trip_Distance;
    }

    public void setStart_Lon(String start_Lon) {
        Start_Lon = start_Lon;
    }

    public void setStart_Lat(String start_Lat) {
        Start_Lat = start_Lat;
    }

    public void setRate_Code(String rate_Code) {
        Rate_Code = rate_Code;
    }

    public void setStore_and_forward(String store_and_forward) {
        Store_and_forward = store_and_forward;
    }

    public void setEnd_Lon(String end_Lon) {
        End_Lon = end_Lon;
    }

    public void setEnd_Lat(String end_Lat) {
        End_Lat = end_Lat;
    }

    public void setPayment_Type(String payment_Type) {
        Payment_Type = payment_Type;
    }

    public void setFare_Amt(String fare_Amt) {
        Fare_Amt = fare_Amt;
    }

    public void setSurcharge(String surcharge) {
        Surcharge = surcharge;
    }

    public void setMta_tax(String mta_tax) {
        this.mta_tax = mta_tax;
    }

    public void setTip_amt(String tip_amt) {
        Tip_amt = tip_amt;
    }

    public void setTolls_Amt(String tolls_Amt) {
        Tolls_Amt = tolls_Amt;
    }

    public void setTotal_amt(String total_amt) {
        Total_amt = total_amt;
    }
}

