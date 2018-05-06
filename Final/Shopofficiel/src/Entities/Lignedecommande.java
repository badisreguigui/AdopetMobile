/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Comparator;


/**
 *
 * @author Badis
 */
public class Lignedecommande {

    private int id;
    private String nomProduit;
    private float prixTotal;
    private int quantite;
    private int etat;
    private int panier;
    private int idProduit;
    private int idUser;
    private Produit product = new Produit();

    public Lignedecommande(String nomProduit, float prixTotal, int quantite, Produit product) {
        this.nomProduit = nomProduit;
        this.prixTotal = prixTotal;
        this.quantite = quantite;
        this.product = product;
    }

    public Produit getProduct() {
        return product;
    }

    public void setProduct(Produit product) {
        this.product = product;
    }

    public Lignedecommande() {
    }

    public Lignedecommande(int id, String nomProduit, float prixTotal, int quantite, int etat, int panier, int idProduit, int idUser) {
        this.id = id;
        this.nomProduit = nomProduit;
        this.prixTotal = prixTotal;
        this.quantite = quantite;
        this.etat = etat;
        this.panier = panier;
        this.idProduit = idProduit;
        this.idUser = idUser;
    }

    public Lignedecommande(int id, String nomProduit, float prixTotal, int quantite, int etat) {
        this.id = id;
        this.nomProduit = nomProduit;
        this.prixTotal = prixTotal;
        this.quantite = quantite;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public float getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getPanier() {
        return panier;
    }

    public void setPanier(int panier) {
        this.panier = panier;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Lignedecommande{" + "id=" + id + ", nomProduit=" + nomProduit + ", prixTotal=" + prixTotal + ", quantite=" + quantite + ", etat=" + etat + ", panier=" + panier + ", idProduit=" + idProduit + ", idUser=" + idUser + '}';
    }

    public Lignedecommande(String nomProduit, int prixTotal, int quantite) {
        this.nomProduit = nomProduit;
        this.prixTotal = prixTotal;
        this.quantite = quantite;
    }
    
    
    
    
//     @Override
//    public int compareTo(Lignedecommande o) 
//    {
//        if(id>o.getId())
//            return 0;
//        if(id<o.getId())
//            return -1;
//        else
//        return 1;
//                    
//    }
//
//    @Override
//    public int compare(Lignedecommande o1, Lignedecommande o2) {
//       if(o1.getProduct().getNom().compareTo(o2.getProduct().getNom())>0)
//           return 0;
//      if(o1.getProduct().getNom().compareTo(o2.getProduct().getNom())<0)
//          return -1;
//    
//       return 1;
//    }

    public Lignedecommande(String nomProduit, float prixTotal, int quantite, int idProduit, int idUser,Produit product) {
        this.nomProduit = nomProduit;
        this.prixTotal = prixTotal;
        this.quantite = quantite;
        this.idProduit = idProduit;
        this.idUser = idUser;
        this.product=product;
    }


}
