/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author achref kh
 */
public class Notification {
    private int idnotif ;
    private int iduser;
    private String contenu;
    private int etat;

    public int getIdnotif() {
        return idnotif;
    }

    public void setIdnotif(int idnotif) {
        this.idnotif = idnotif;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Notification() {
    }

    public Notification(int idnotif, int iduser, String contenu, int etat) {
        this.idnotif = idnotif;
        this.iduser = iduser;
        this.contenu = contenu;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Notification{" + "idnotif=" + idnotif + ", iduser=" + iduser + ", contenu=" + contenu + ", etat=" + etat + '}';
    }
    
}
