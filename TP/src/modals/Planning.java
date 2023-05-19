package modals;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Planning implements Serializable  /* implements Planification */ {
    
    @Override
    public String toString() {
        return "Planning de "+ period.getStartDate() +" à "+period.getEndDate() +" : " +"\nLe nombre de tâches complétées : " + nb_taches_comp + "\nLe nombre de projets complétés : " + nb_projets_comp + "\nLes badges gagnés : " + badge;
    }
    
    private int nb_taches_comp;         // nombre total de tâches complétées
    private int nb_projets_comp;        // nombre total de projets complétés
    private PeriodMe period;
    private Map<String, Integer> badge = new HashMap<>();
    private Map<Tache,Creneau> tachesPlanned = new HashMap<>();// contains the tasks of the planning and their slot after planification


    /*WHEN displaying history of plannings i have to test wheter it is a planning of one day if no i return it with the getter, ndir 
     * methode traj3li boolean 
     * nroh l controller w fl parcours ta3 plannings nzid condition bl methode jdida
     */
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

    public Map<Tache, Creneau> getTachesPlanned() {
        return tachesPlanned;
    }

    public void setTachesPlanned(Map<Tache, Creneau> tachesPlanned) {
        this.tachesPlanned = tachesPlanned;
    }

    public Creneau updateSlot(LocalDate d,Creneau c,Long dureeTache){
        //mettre à jour un créneau après la planification d'une tâche
        //returns the new slot so i can add it to the map tache, créneau
        Creneau _creneau = c;
        boolean isDivisible = _creneau.isDivisible(dureeTache);
        if(isDivisible){//calculate the new slot and replace the old one, 
           _creneau.updateCreneau(dureeTache);
           period.updateSlotPeriod(d, c, _creneau);
        }
        else{//remove the slot from the arraylist of date,creneau
            period.removeSlot(d, c);
        }
        return _creneau;
    }
    
    public void addSingleTask(Tache t, Creneau c){
        tachesPlanned.put(t,c);
    }

    /* add a methode to test if in the arrayList of plannedtasks , the date and slot are already taken */

   
}
