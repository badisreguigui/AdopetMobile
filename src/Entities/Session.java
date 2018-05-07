/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kapio
 */
public class Session {

    public static Session instance;

    public List<User> p = new ArrayList();

    public Session() {
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;

    }

    public void addSession(User c) {

        if (p.isEmpty()) {
            System.out.println("moch mawwjoud");
            p.add(c);

        } else {
            System.out.println("mawjoud");

        }

        //System.out.println(c);
    }

    public void removeSession(User c) {
        p.remove(c);

    }
    
    public String LoginSession(){
        return p.get(0).getUsername();
}
    

    public int IdSession() {

        return p.get(0).getId();
}

}
