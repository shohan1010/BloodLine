package com.example.bloodline.Request;

public class User_Request {
    String Age,Blood_Group,Email,Location,Name,Phone,Note;

    public User_Request() {
    }

    public User_Request(String age, String blood_Group, String email, String location, String name, String phone, String note) {
        Age = age;
        Blood_Group = blood_Group;
        Email = email;
        Location = location;
        Name = name;
        Phone = phone;
        Note = note;

    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getBlood_Group() {
        return Blood_Group;
    }

    public void setBlood_Group(String blood_Group) {
        Blood_Group = blood_Group;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
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

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }


}
