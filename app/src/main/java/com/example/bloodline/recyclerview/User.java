package com.example.bloodline.recyclerview;

public class User {
    String Age,Blood_Group,Email,Location,Name,Phone,Image;

    public User() {
    }



    public User(String age, String blood_Group, String email, String location, String name, String phone,String image) {
        Age = age;
        Blood_Group = blood_Group;
        Email = email;
        Location = location;
        Name = name;
        Phone = phone;
        Image = image;

    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
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


}
