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
public class Participation {
    private int iduser;
    private int idevent;

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    public Participation() {
    }

    public Participation(int iduser, int idevent) {
        this.iduser = iduser;
        this.idevent = idevent;
    }

    @Override
    public String toString() {
        return "Participation{" + "iduser=" + iduser + ", idevent=" + idevent + '}';
    }
    
    
}
