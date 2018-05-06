/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;


/**
 *
 * @author Badis
 */
public class Produit {
    private int id;
    private String nom;
    private String Description;
    private String Image;
    private String race;
    private float Prix;
    private int Quantite;
    private int Disponibilite;
    private int Promotion;
    private int Rating;
    private int TauxPromotion;
    

    public Produit() {
    }

    public Produit(int id, String nom, String Description, String Image, String race, float Prix, int Quantite, int Disponibilite, int Promotion, int Rating, int TauxPromotion) {
        this.id = id;
        this.nom = nom;
        this.Description = Description;
        this.Image = Image;
        this.race = race;
        this.Prix = Prix;
        this.Quantite = Quantite;
        this.Disponibilite = Disponibilite;
        this.Promotion = Promotion;
        this.Rating = Rating;
        this.TauxPromotion = TauxPromotion;
    }
    
    

    public Produit(int id, String nom, float Prix, int Quantite, String Image,int Promotion,int TauxPromotion) {
        this.id = id;
        this.nom = nom;

        this.Prix = Prix;
        this.Quantite = Quantite;
        this.Image=Image;
        this.Promotion=Promotion;
        this.TauxPromotion=TauxPromotion;
    }

      public Produit(int id, String nom, float Prix, int Quantite) {
        this.id = id;
        this.nom = nom;

        this.Prix = Prix;
        this.Quantite = Quantite;
       
    }
  
      
      
        public Produit(int id, String nom, float Prix, int Quantite, String Image) {
        this.id = id;
        this.nom = nom;
        this.Prix = Prix;
        this.Quantite = Quantite;
        this.Image=Image;

    }

  

    public Produit(String nom, String Description, String Image, float Prix, int Quantite, int Disponibilite, int Promotion, int Rating, int TauxPromotion) {
        this.nom = nom;
        this.Description = Description;
        this.Image = Image;

        this.Prix = Prix;
        this.Quantite = Quantite;
        this.Disponibilite = Disponibilite;
        this.Promotion = Promotion;
        this.Rating = Rating;
        this.TauxPromotion = TauxPromotion;

    }

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
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

 

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }


    public float getPrix() {
        return Prix;
    }

    public void setPrix(float Prix) {
        this.Prix = Prix;
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int Quantite) {
        this.Quantite = Quantite;
    }

    public int getDisponibilite() {
        return Disponibilite;
    }

    public void setDisponibilite(int Disponibilite) {
        this.Disponibilite = Disponibilite;
    }

    public int getPromotion() {
        return Promotion;
    }

    public void setPromotion(int Promotion) {
        this.Promotion = Promotion;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int Rating) {
        this.Rating = Rating;
    }

    public int getTauxPromotion() {
        return TauxPromotion;
    }

    public void setTauxPromotion(int TauxPromotion) {
        this.TauxPromotion = TauxPromotion;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", Description=" + Description + ", Image=" + Image + ", race=" + race + ", Prix=" + Prix + ", Quantite=" + Quantite + ", Disponibilite=" + Disponibilite + ", Promotion=" + Promotion + ", Rating=" + Rating + ", TauxPromotion=" + TauxPromotion + '}';
    }

   

    

  

    
    
    
}
