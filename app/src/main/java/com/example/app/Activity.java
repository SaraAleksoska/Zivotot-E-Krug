package com.example.app;

import com.google.firebase.auth.FirebaseUser;


public class Activity {

    public String typeA, descA, time, rep, urg, loc, own, vol, state, ownName, volName, volTel, volmail;

    public Activity(){

    }

    public Activity(String typeA, String descA, String time, String rep, String urg, String loc, String own) {
        this.typeA = typeA;
        this.descA = descA;
        this.time = time;
        this.rep = rep;
        this.urg = urg;
        this.loc = loc;
        this.own = own;
        this.vol = "none";
        this.volName ="/";
        this.state = "active";
        this.ownName = "none";
        this.volTel = "/";
        this.volmail ="/";
    }

    public String getVolmail() {
        return volmail;
    }

    public void setVolmail(String volmail) {
        this.volmail = volmail;
    }

    public String getVolTel() {
        return volTel;
    }

    public void setVolTel(String volTel) {
        this.volTel = volTel;
    }

    public String getOwnName() {
        return ownName;
    }

    public String getVolName() {
        return volName;
    }

    public void setVolName(String volName) {
        this.volName = volName;
    }

    public void setOwnName(String ownName) {
        this.ownName = ownName;
    }

    public String getTypeA() {
        return typeA;
    }

    public String getDescA() {
        return descA;
    }

    public String getTime() {
        return time;
    }

    public String getRep() {
        return rep;
    }

    public String getUrg() {
        return urg;
    }

    public String getLoc() {
        return loc;
    }

    public String getOwn() {
        return own;
    }

    public String getVol() {
        return vol;
    }

    public String getState() {
        return state;
    }

    public void setTypeA(String typeA) {
        this.typeA = typeA;
    }

    public void setDescA(String descA) {
        this.descA = descA;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public void setUrg(String urg) {
        this.urg = urg;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public void setOwn(String own) {
        this.own = own;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

}