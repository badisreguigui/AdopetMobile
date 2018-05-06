/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Entity;

/**
 *
 * @author TESNIME
 */
public class RDV {
    private int id;
    private int id_veto;
    private int id_user;
    private String date_rdv;
    private String heure;

    public RDV() {
    }

    public RDV(int id_veto, int id_user, String date_rdv, String heure) {
        this.id_veto = id_veto;
        this.id_user = id_user;
        this.date_rdv = date_rdv;
        this.heure = heure;
    }

    
    

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    

    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_veto() {
        return id_veto;
    }

    public void setId_veto(int id_veto) {
        this.id_veto = id_veto;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getDate_rdv() {
        return date_rdv;
    }

    public void setDate_rdv(String date_rdv) {
        this.date_rdv = date_rdv;
    }

    @Override
    public String toString() {
        return "Rdv{" + "id=" + id + ", id_veto=" + id_veto + ", id_user=" + id_user + ", date_rdv=" + date_rdv + '}';
    }

   
    
    
}
