package modals;

import java.io.Serializable;
import java.util.ArrayList;

public class Utilisateur implements Serializable {

    @Override
    public String toString() {
        return "Utilisateur [pseudo=" + pseudo + ", planning=" + planning + ", dureeMin=" + dureeMin
                + ", tacheMin=" + tacheMin + "]";
    }

    public Utilisateur(String pseudo, int dureeMin, int tacheMin) {
        this.pseudo = pseudo;
        this.planning = new ArrayList<Planning>();
        this.dureeMin = dureeMin;
        this.tacheMin = tacheMin;
    }

    private String pseudo;
    private ArrayList<Planning> planning;
    private int dureeMin; // durée minimale d'un créneau
    private int tacheMin; // nombre minimal de tâche/jour pour être récompensé


    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public ArrayList<Planning> getPlanning() {
        return planning;
    }

    public void setPlanning(ArrayList<Planning> planning) {
        this.planning = planning;
    }

    public float getDureeMin() {
        return dureeMin;
    }

    public void setDureeMin(int dureeMin) {
        this.dureeMin = dureeMin;
    }

    public int getTacheMin() {
        return tacheMin;
    }

    public void setTacheMin(int tacheMin) {
        this.tacheMin = tacheMin;
    }
 

}
