package com.example.kolanchik;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Race implements Serializable {

    String name;
    String img;
    Integer credits;
    Integer needInSlaves;
    Integer needInBlood;
    Integer needInAlchemy;
    Integer needInMagicItems;
    Integer needInWeapon;
    Integer needInMedicine;
    Integer needInBijouterie;
    Integer needInTechnologies;

    public Race() {}

    public Race(String name, String img, Integer credits, Integer needInSlaves,
                Integer needInBlood, Integer needInAlchemy, Integer needInMagicItems,
                Integer needInWeapon, Integer needInMedicine, Integer needInBijouterie,
                Integer needInTechnologies) {

        this.name = name;
        this.img = img;
        this.credits = credits;
        this.needInSlaves = needInSlaves;
        this.needInBlood = needInBlood;
        this.needInAlchemy = needInAlchemy;
        this.needInMagicItems = needInMagicItems;
        this.needInWeapon = needInWeapon;
        this.needInMedicine = needInMedicine;
        this.needInBijouterie = needInBijouterie;
        this.needInTechnologies = needInTechnologies;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public Race(String name, Integer credits, Integer needInSlaves, Integer needInBlood,
                Integer needInAlchemy, Integer needInMagicItems, Integer needInWeapon,
                Integer needInMedicine, Integer needInBijouterie, Integer needInTechnologies) {
        this.name = name;
        this.credits = credits;
        this.needInSlaves = needInSlaves;
        this.needInBlood = needInBlood;
        this.needInAlchemy = needInAlchemy;
        this.needInMagicItems = needInMagicItems;
        this.needInWeapon = needInWeapon;
        this.needInMedicine = needInMedicine;
        this.needInBijouterie = needInBijouterie;
        this.needInTechnologies = needInTechnologies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getNeedInSlaves() {
        return needInSlaves;
    }

    public void setNeedInSlaves(Integer needInSlaves) {
        this.needInSlaves = needInSlaves;
    }

    public Integer getNeedInBlood() {
        return needInBlood;
    }

    public void setNeedInBlood(Integer needInBlood) {
        this.needInBlood = needInBlood;
    }

    public Integer getNeedInAlchemy() {
        return needInAlchemy;
    }

    public void setNeedInAlchemy(Integer needInAlchemy) {
        this.needInAlchemy = needInAlchemy;
    }

    public Integer getNeedInMagicItems() {
        return needInMagicItems;
    }

    public void setNeedInMagicItems(Integer needInMagicItems) {
        this.needInMagicItems = needInMagicItems;
    }

    public Integer getNeedInWeapon() {
        return needInWeapon;
    }

    public void setNeedInWeapon(Integer needInWeapon) {
        this.needInWeapon = needInWeapon;
    }

    public Integer getNeedInMedicine() {
        return needInMedicine;
    }

    public void setNeedInMedicine(Integer needInMedicine) {
        this.needInMedicine = needInMedicine;
    }

    public Integer getNeedInBijouterie() {
        return needInBijouterie;
    }

    public void setNeedInBijouterie(Integer needInBijouterie) {
        this.needInBijouterie = needInBijouterie;
    }

    public Integer getNeedInTechnologies() {
        return needInTechnologies;
    }

    public void setNeedInTechnologies(Integer needInTechnologies) {
        this.needInTechnologies = needInTechnologies;
    }
}
