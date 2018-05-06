/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Entities.Lignedecommande;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Badis
 */
public class Panier {

    public static Panier instance;

    public List<Lignedecommande> p = new ArrayList();

    public Panier() {
    }

    public static Panier getInstance() {
        if (instance == null) {
            instance = new Panier();
        }
        return instance;

    }

    public void addLine(Lignedecommande c) {
        if (search(c) == false) {
            System.out.println("Quantite Premier Ajout==>" + c.getQuantite());
            p.add(c);
        } else {
            for (Lignedecommande c1 : p) 
            {

                if (c1.getProduct().getNom().compareTo(c.getProduct().getNom())==0)
                {                   
                    c1.setQuantite(c1.getQuantite() + 1);
                    System.out.println("Quantite Deuxieme Ajout==>" + c1.getQuantite());
                }
            }
        }

    }

    public void deleteCart() {
        p.removeAll(p);

    }

    public void removeLine(Lignedecommande c) {
        p.remove(c);

    }

    public void show() {

        for (Lignedecommande c : p) {
            System.out.println(c.toString());
        }
    }

    public boolean search(Lignedecommande c) {
        for (Lignedecommande c1 : p) {
            if (c1.getProduct().getNom().compareTo(c.getProduct().getNom()) == 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "Panier{" + "p=" + p + '}';
    }

}
