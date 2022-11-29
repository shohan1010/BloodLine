package com.example.bloodline.Ambulance;

public class User_Ambulance {
    String Name,Phone,Location;

    public User_Ambulance() {
    }

    public User_Ambulance(String name, String phone, String location) {
        Name = name;
        Phone = phone;
        Location = location;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
