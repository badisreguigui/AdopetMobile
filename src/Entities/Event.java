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
public class Event {
    private int id;
    private String nom;
    private String description;
    private String type;
    private String lieu;
    private String datefin;
    private String datedebut;
    private String status;
    private int nbrpart;
    private int organisateur;
    private int acc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLieu() {
        return lieu;
    } 

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNbrpart() {
        return nbrpart;
    }

    public void setNbrpart(int nbrpart) {
        this.nbrpart = nbrpart;
    }

    public int getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(int organisateur) {
        this.organisateur = organisateur;
    }
    
    

    public int getAcc() {
        return acc;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }

    public Event() {
    }

    public Event(int id, String nom, String description, String type, String lieu, String status, int nbrpart,int organisateur, int acc) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.lieu = lieu;
        this.status = status;
        this.nbrpart = nbrpart;
        this.organisateur = organisateur;
        this.acc = acc;
    }

    public Event(String nom, String description, String type, String lieu, String datefin, String datedebut, int organisateur) {
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.lieu = lieu;
        this.datefin = datefin;
        this.datedebut = datedebut;
        this.organisateur = organisateur;
    }


    
    

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", type=" + type + ", lieu=" + lieu + ", status=" + status + ", nbrpart=" + nbrpart + ", organisateur=" + organisateur + ", acc=" + acc + '}';
    }

    
    
    
}
