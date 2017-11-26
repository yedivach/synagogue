package com.example.shlez.synagogue;

import android.location.Address;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Shlez on 11/23/17.
 */

public class Prayer implements Serializable {

    String phone;
    String email;
    String name;
    String birthday;
    Address addr;


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


////    Address - Getter & Setter
//    public Address getAddr() { return addr; }
//    public void setAddr(Address addr) { this.addr = addr; }


}
