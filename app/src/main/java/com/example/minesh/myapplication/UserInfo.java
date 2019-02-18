package com.example.minesh.myapplication;

public class UserInfo {
    String Name;
    String ID;

    public UserInfo(){}
    public UserInfo(String name, String ID) {
        this.Name = name;
        this.ID = ID;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
