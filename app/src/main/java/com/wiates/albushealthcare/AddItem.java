package com.wiates.albushealthcare;

public class AddItem {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getYp() {
        return blood;
    }

    public void setYp(String yp) {
        this.blood = yp;
    }

    String name;
    String age;
    String gender;
    String weight;
    String height;
    String dn;
    String blood;

    public AddItem(String name, String age, String gender, String weight, String height, String dn, String yp) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.dn = dn;
        this.blood = yp;
    }
}
