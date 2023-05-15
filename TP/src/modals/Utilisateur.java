package modals;

import java.io.Serializable;
import java.util.ArrayList;

public class Utilisateur implements Serializable {

    @Override
    public String toString() {
        return "Utilisateur [pseudo=" + pseudo + ", planning=" + planning + ", dureeMin=" + dureeMin
                + ", tacheMin=" + tacheMin + "]";
    }

    public Utilisateur(String pseudo, Long dureeMin, int tacheMin) {
        this.pseudo = pseudo;
        this.dureeMin = dureeMin;
        this.tacheMin = tacheMin;
    }

    private String pseudo;
    private ArrayList<Planning> planning= new ArrayList<>();
    private Long dureeMin; // durée minimale d'un créneau
    private int tacheMin; // nombre minimal de tâche/jour pour être récompensé

    public boolean isPeriodAvailable(PeriodMe period) {
        for (Planning _planning : planning) {
            if (_planning.getPeriod().overlaps(period)) {
                return false; // Period is not available
            }
        }
        return true; // Period is available
    }

    /*public int indexPlanning(Planning planning){

    }*/
    
    
    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public ArrayList<Planning> getPlanning() {
        return planning;
    }

    public void setPlanning(Planning planning) {
        this.planning.add(planning);
    }

    public Long getDureeMin() {
        return dureeMin;
    }

    public void setDureeMin(Long dureeMin) {
        this.dureeMin = dureeMin;
    }

    public int getTacheMin() {
        return tacheMin;
    }

    public void setTacheMin(int tacheMin) {
        this.tacheMin = tacheMin;
    }
    

}
