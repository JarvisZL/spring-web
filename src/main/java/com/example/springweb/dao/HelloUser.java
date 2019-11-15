package com.example.springweb.dao;

import java.io.Serializable;

public class HelloUser implements Serializable {
    private String id;
    private String name;
    private String password;
    private String contact;
    private String remark;

    public HelloUser(){
        id = null;
        name = null;
        password = null;
        contact = null;
        remark = null;
    }
    public HelloUser(String id,String name,String password,String contact, String remark){
        this.id=id;
        this.name=name;
        this.password=password;
        this.contact = contact;
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + password;
    }
}
