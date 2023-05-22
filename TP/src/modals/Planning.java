package modals;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Planning implements Serializable /* implements Planification */ {

    // public String toString() {
    // return "Planning de "+ period.getStartDate() +" à "+period.getEndDate() +" :
    // " +"\nLe nombre de tâches complétées : " + nb_taches_comp + "\nLe nombre de
    // projets complétés : " + nb_projets_comp + "\nLes badges gagnés : " + badge;
    // }
    private int nb_taches_comp; // nombre total de tâches complétées
    private int nb_projets_comp; // nombre total de projets complétés
    private PeriodMe period;
    private Map<Tache, Creneau> tachesPlanned = new HashMap<>();// contains the tasks of the planning and their slot
                                                                // after planification


    public Planning() {
    }

    public Planning(PeriodMe period) {
        this.period = period;
        this.nb_taches_comp = 0;
        this.nb_projets_comp = 0;
    }

    public ArrayList<Tache> getTachesForDay(LocalDate date) {
        //returns the list of taches scheduled in a given date
        ArrayList<Tache> tachesForDay = new ArrayList<>();
        for (Tache tache : tachesPlanned.keySet()) {
            LocalDate tacheDate = tache.getDate(); 
            if (tacheDate.equals(date)) {
                tachesForDay.add(tache);
            }
        }
        return tachesForDay;
    }

    public void addTachesToPlanned(Map<Tache, Creneau> tachesToAdd) {
        tachesPlanned.putAll(tachesToAdd);
    }

    public void addSingleTask(Tache t, Creneau c){
        tachesPlanned.put(t,c);
    }

    public boolean containsCreneauValue(Creneau creneau) {
        boolean b=false;
        for (Map.Entry<Tache, Creneau> entry : tachesPlanned.entrySet()) {
            Creneau currentCreneau = entry.getValue();
                if(currentCreneau.overlaps(creneau,currentCreneau)){
                    b=true;
                } 
            
        }
        return b;
    }

    @Override
    public String toString() {
        return "\n-------->Planning [period=" + period + ", tachesPlanned = " + printtacheplanned()  + "]";
    }

    public String printtacheplanned(){
        String text="";
        for (Map.Entry<Tache, Creneau> entry : tachesPlanned.entrySet()) {
            Tache tache = entry.getKey();
            Creneau creneau = entry.getValue();
           text=text+ "------\nTache: " + tache + ", Creneau: " + creneau;
        }   
        return text;        
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

    public PeriodMe getPeriod() {
        return period;
    }

    public void setPeriod(PeriodMe period) {
        this.period = period;
    }

    public Map<Tache, Creneau> getTachesPlanned() {
        return tachesPlanned;
    }

    public void setTachesPlanned(Map<Tache, Creneau> tachesPlanned) {
        this.tachesPlanned = tachesPlanned;
    }
}
