package com.loutasae.tolet;


import java.lang.ref.SoftReference;
import java.util.Date;

public class EachFlat {
    private int _id;
    private String city;
    private String locality;
    private String  date;
    private String rent;
    private String remarks;

    public EachFlat(String city, String locality, String rent, String remarks, String  date) {
        this.city = city;
        this.locality = locality;
        this.rent = rent;
        this.remarks = remarks;
        this.date = date;

    }

    public EachFlat() {

    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public void setDate(String  date) {
        this.date = date;
    }

    public int get_id() {
        return _id;
    }

    public String getCity() {
        return city;
    }

    public String getLocality() {
        return locality;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getRent() {
        return rent;
    }

    public String getDate() {
        return date;
    }
}
