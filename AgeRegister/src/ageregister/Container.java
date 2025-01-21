/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageregister;

/**
 *
 * @author Oshan
 */
public class Container {
    private String name;
    private String GNdiv;
    private String address;
    private String contact;
    private String birthday;
    private String QR;
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGNdiv() {
        return GNdiv;
    }

    public void setGNdiv(String GNdiv) {
        this.GNdiv = GNdiv;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    
    public String getQR() {
        return QR;
    }
       
    public void setQR(String qr) {
        this.QR = qr;
    }
    
}
