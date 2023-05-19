package modals;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ListIterator;

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

    public Utilisateur(){}

    private String pseudo;
    private ArrayList<Planning> planning= new ArrayList<>();
    private Long dureeMin; // durée minimale d'un créneau
    private int tacheMin; // nombre minimal de tâche/jour pour être récompensé
    private ArrayList<Tache> tachesNotPlanned = new ArrayList<>(); // contains all the tasks li dkhlhom user w mazel maplanifahomch
    private ArrayList<Categorie> myCategories = new ArrayList<>(); 
    private ArrayList<Projet> projets = new ArrayList<>(); // la liste qui contient tous les projets à planifier    

    public boolean isPeriodAvailable(PeriodMe period) {
        for (Planning _planning : planning) {
            if (_planning.getPeriod().overlaps(period)) {
                return false; // Period is not available
            }
        }
        return true; // Period is available
    }

    private Planning isDate(LocalDate d){
        //teste si la date "d" est déja dans un des plannings
        Planning indexP=null;
        ArrayList<Planning> myPlannings = planning;
        if (myPlannings != null) {
            ListIterator<Planning> iterator = myPlannings.listIterator();
            while (iterator.hasNext()) {
                Planning currentPlanning = iterator.next();
                if (currentPlanning.getPeriod().containsDate(d)) {
                    indexP=currentPlanning;
                    break;
                }
            }
        }
        return  indexP;
    }

    private Planning isDateAndCreneau(LocalDate d,Creneau c){
        //teste si la date "d" et le créneau sont déjà dans un des plannings
        Planning indexP=null;
        ArrayList<Planning> myPlannings = planning;
        if (myPlannings != null) {
            ListIterator<Planning> iterator = myPlannings.listIterator();
            while (iterator.hasNext()) {
                Planning currentPlanning = iterator.next();
                if (currentPlanning.getPeriod().containsDateAndCreneau(d,c) != -1) {
                    indexP=currentPlanning;
                    break;
                }
            }
        }
        return indexP;
    }

    public void planNewTaskSimple(Tache t,LocalDate d, Creneau c){
        //planifier manuellement une tâche simple
        if(t instanceof Simple){
            if(isDate(d) == null){
                //créer un plannig d'un jour 
                PeriodMe periodMe= new PeriodMe(d, d);
                Planning _planning = new Planning(periodMe);
                t.setDate(d);
                _planning.addSingleTask(t,c);
                this.planning.add(_planning);
            }
            else{
                Planning indexP = isDateAndCreneau(d, c);
                if(indexP != null){
                    //update the existing slot
                    Creneau _creneau = indexP.updateSlot(d, c, t.getDuree()); /////// idk if its right to test on la classe tache
                    t.setDate(d);
                    indexP.addSingleTask(t, _creneau);
                }
                else{
                    indexP=isDate(d);
                    t.setDate(d);
                    indexP.addSingleTask(t, c);
                }
            }
        }
    }

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
    
    public void setPlanning(ArrayList<Planning> planning) {
        this.planning = planning;
    }

    public ArrayList<Tache> getTachesNotPlanned() {
        return tachesNotPlanned;
    }

    public void addTaskToTachesNotPlanned(Tache task) {
        tachesNotPlanned.add(task);
    }

    public void removeTaskFromTachesNotPlanned(Tache task) {
        tachesNotPlanned.remove(task);
    }

    public void addNewCategory(Categorie c){
        myCategories.add(c);
    }

    public void deleteCategory(Categorie c){
        myCategories.remove(c);
    }

    public ArrayList<Categorie> getMyCategories() {
        return myCategories;
    }

    public ArrayList<Projet> getProjets() {
        return projets;
    }

    public void setProjets(ArrayList<Projet> projets) {
        this.projets = projets;
    }

    public void addNewProject(Projet p){
        projets.add(p);
    }

    public void deleteProject(Projet p){
        projets.remove(p);
    }

    public boolean isColor(java.awt.Color c){
        for (Categorie category : myCategories) {
            if (category.getCouleur().equals(c)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNameCategory(String n){
        for (Categorie category : myCategories) {
            if (category.getNom().equals(n)) {
                return true;
            }
        }
        return false;
    }
    
    

}
