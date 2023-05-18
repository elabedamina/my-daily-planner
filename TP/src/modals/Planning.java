package modals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Planning implements Serializable  /* implements Planification */ {
    
    @Override
    public String toString() {
        return "Planning : \nLe nombre de tâches complétées : " + nb_taches_comp + "\nLe nombre de projets complétés : " + nb_projets_comp + "\nLes badges gagnés : " + badge;
    }
    
    private int nb_taches_comp;         // nombre total de tâches complétées
    private int nb_projets_comp;        // nombre total de projets complétés
    private PeriodMe period;
    private Map<String, Integer> badge = new HashMap<>();
    private ArrayList<Projet> projets = new ArrayList<>(); // la liste qui contient tous les projets à planifier    
    private ArrayList<Tache> tachesPlanned = new ArrayList<>(); // to change into map fiha tache + creneau

    public Planning( PeriodMe period) {
        this.period = period;
        this.nb_taches_comp = 0;
        this.nb_projets_comp = 0;
        badge.put("GOOD", 0);
        badge.put("VeryGOOD", 0);
        badge.put("EXCELLENT", 0);
    }

    public int getNb_taches_comp() {
        return nb_taches_comp;
    }

    public void setNb_taches_comp(int nb_taches_comp) {
        this.nb_taches_comp = nb_taches_comp;
    }

    public int getNb_projets_comp() {
        return nb_projets_comp;
    }

    public void setNb_projets_comp(int nb_projets_comp) {
        this.nb_projets_comp = nb_projets_comp;
    }

    public Map<String, Integer> getBadge() {
        return badge;
    }

    public void setBadge(Map<String, Integer> badge) {
        this.badge = badge;
    }

    public int getGood(){
        return badge.get("GOOD");
    }
   
    public int getVeryGOOD(){
        return badge.get("VeryGOOD");
    }

    public int getEXCELLENT(){
        return badge.get("EXCELLENT");
    }

    public PeriodMe getPeriod() {
        return period;
    }

    public void setPeriod(PeriodMe period) {
        this.period = period;
    }

    public ArrayList<Tache> getTachesPlanned() {
        return tachesPlanned;
    }

    public void setTachesPlanned(ArrayList<Tache> tachesPlanned) {
        this.tachesPlanned = tachesPlanned;
    }

}
