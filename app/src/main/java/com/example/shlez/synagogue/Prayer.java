package com.example.shlez.synagogue;

import java.io.Serializable;

/**
 * Created by Shlez on 11/23/17.
 */

public class Prayer implements Serializable {

    private String phone;
    private String email;
    private String name;
    private String birthday;
    private String address;
    private String imageURL;
    private String isGabay = "false";

//    Parameterless Constructor
    public Prayer () {}


//    Phone - Getter & Setter
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }


//    Email - Getter & Setter
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }


//    Name- Getter & Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


//    Birthday - Getter & Setter
    public String getBirthday() { return birthday; }
    public void setBirthday(String birthday) { this.birthday= birthday; }


//    Address - Getter & Setter
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }


//    ImageURL - Getter & Setter
    public String getImageURL() { return imageURL; }
    public void setImageURL(String imageURL) { this.imageURL = imageURL; }

// Is Gabay *NO SET*
    public String getIsGabay(){
        return this.isGabay;
    }



}
