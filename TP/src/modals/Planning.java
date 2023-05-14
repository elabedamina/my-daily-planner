package modals;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Planning implements Serializable  /* implements Planification */ {
    private Calendrier calendrier;
    private int nb_taches_comp;         // nombre total de tâches complétées
    private int nb_projets_comp;        // nombre total de projets complétés
    private Date debut_periode;       // la date du début de la période ou l'utilisateur souhaite planifier ses tâches
    private Date fin_periode;         // la date de la fin de la période ou l'utilisateur souhaite planifier ses tâches
    private Map<String, Integer> badge = new HashMap<>();

    public Planning(Calendrier calendrier, Date debut_periode, Date fin_periode) {
        this.calendrier = calendrier;
        this.nb_taches_comp = 0;
        this.nb_projets_comp = 0;
        this.debut_periode = debut_periode;
        this.fin_periode = fin_periode;
        badge.put("GOOD", 0);
        badge.put("VeryGOOD", 0);
        badge.put("EXCELLENT", 0);
    }

    public Calendrier getCalendrier() {
        return calendrier;
    }

    public void setCalendrier(Calendrier calendrier) {
        this.calendrier = calendrier;
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

    public Date getDebut_periode() {
        return debut_periode;
    }

    public void setDebut_periode(Date debut_periode) {
        this.debut_periode = debut_periode;
    }

    public Date getFin_periode() {
        return fin_periode;
    }

    public void setFin_periode(Date fin_periode) {
        this.fin_periode = fin_periode;
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
    /*
     * public void planifierManuel (Tache tache){}
     * public void planifierAuto (Tache tache){}
     * public void supprimerTache (Tache tache){}
     * public Calendrier majCalendrier (){} //retourne le calendrier après la
     * replanification (mise à jour)
     * public boolean valider (){} //retourne vrai si l'utilisateur souhaite garder
     * la modification
     * public boolean modifier (){} //retourne vrai si l'utilisateur souhaite
     * modifier le planing proposé
     * public void ajouterTache (Tache tache){} //ajouter dans la liste des tâches
     * non planifiées (la même métthode pour reporterTache)
     * public void replanifierTache (Tache tache){}
     */

}
