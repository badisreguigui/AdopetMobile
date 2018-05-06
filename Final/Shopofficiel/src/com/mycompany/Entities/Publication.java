/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.Entities;


/**
 *
 * @author LENOVO
 */
public class Publication {
    private String idpub;
    private String type;
    private String description;
    private String image;
    private String username;
    private int iduser;
    private String date;
    private String time;
    private String photoName;
    private String signall;

    public Publication(String type, String description, String image, String username, String date, String time, String photoName, String signall) {
        this.type = type;
        this.description = description;
        this.image = image;
        this.username = username;
        this.date = date;
        this.time = time;
        this.photoName = photoName;
        this.signall = signall;
    }
    

    public Publication(String text, String text0) {
    }

    public String getSignall() {
        return signall;
    }

    public void setSignall(String signall) {
        this.signall = signall;
    }
    
      
    public Publication() {
    }

    public Publication(String idpub, String type, String description, String image, String username, String date, String time) {
        this.idpub = idpub;
        this.type = type;
        this.description = description;
        this.image = image;
        this.username = username;
        this.date = date;
        this.time = time;
    }
    
    public Publication(String idpub, String type, String description, String image, String username, String date, String time, String photoName, String signall) {
        this.idpub = idpub;
        this.type = type;
        this.description = description;
        this.image = image;
        this.username = username;
        this.date = date;
        this.time = time;
        this.photoName = photoName;
        this.signall = signall;
    }


    public Publication(String type, String description, String image, int iduser) {
        this.type = type;
        this.description = description;
        this.image = image;
        this.iduser=iduser;
    }    

    public Publication(String type, String description, String image) {
        this.type = type;
        this.description = description;
        this.image = image;
    }

    public Publication(String idpub, String type, String description, String image, String username) {
        this.idpub = idpub;
        this.type = type;
        this.description = description;
        this.image = image;
        this.username = username;
    }

    public Publication(String type, String description, String image, String username, String date, String time) {
        this.type = type;
        this.description = description;
        this.image = image;
        this.username = username;
        this.date = date;
        this.time = time;
    }

    public Publication(String idpub) {
        this.idpub = idpub;
    }
    

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
    
    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIduser() {
        return iduser;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIdpub() {
        return idpub;
    }

    public void setIdpub(String idpub) {
        this.idpub = idpub;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }  


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Publication other = (Publication) obj;
        if (this.idpub != other.idpub) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Publication{" + "idpub=" + idpub + ", type=" + type + ", description=" + description + ", image=" + image + ", username=" + username + ", date=" + date + ", time=" + time + ", photoName=" + photoName + ", signall=" + signall + '}';
    }

    
    
    
    

    
    
    
    
}
