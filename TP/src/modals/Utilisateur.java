package modals;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ListIterator;

public class Utilisateur implements Serializable {

    @Override
    public String toString() {
        return "----------------------------\nUtilisateur : pseudo=" + pseudo + "\n->planning :\n" + planning + "\n->dureeMin=\n" + dureeMin + "\n->tacheMin=\n"
                + tacheMin + "\n->tachesNotPlanned= \n" + tachesNotPlanned + "\n->myCategories=\n" + myCategories + "\n->projets=\n"
                + projets + "]\n----------------------------";
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

    private int isDate(LocalDate d){
        //teste si la date "d" est déja dans un des plannings
        int index = -1;
        ArrayList<Planning> myPlannings = planning;
        if (myPlannings != null) {
            ListIterator<Planning> iterator = myPlannings.listIterator();
            while (iterator.hasNext()) {
                Planning currentPlanning = iterator.next();
                if (currentPlanning.getPeriod().containsDate(d)||(currentPlanning.getPeriod().isTacheSimple() && currentPlanning.getPeriod().getStartDate().isEqual(d))) {
                    index = myPlannings.indexOf(currentPlanning);
                    break;
                }
            }
        }
        return  index;
    }

    private boolean creneauLibre(LocalDate d,Creneau c, int indexPlanning){
        //retourne vrai si pour une date de planning donnée le créneau est libre
        if (planning.get(indexPlanning).getPeriod().containsDateAndCreneau(d,c) != -1) {
            return true;
        }
        return false;
    }

    private boolean creneauOccupe(Creneau c, int indexPlanning){
        //retrourne vrai si le créneau est occupé
        System.out.println(" dkhalt l creneau ocuuppee ");

        return planning.get(indexPlanning).containsCreneauValue(c);
    }

    private boolean creneauNotExist(LocalDate d,Creneau c, int indexPlanning){
        //retourne vrai si le créneau n'existe
        return(!creneauLibre(d, c, indexPlanning) && !creneauOccupe(c, indexPlanning));      
    }

    public boolean planNewTaskSimple(Tache t,LocalDate d, Creneau c){
    //planifier manuellement une tâche simple
        if(t instanceof Simple){
            if(isDate(d) == -1){    //la date n'est dans aucun planning
                PeriodMe periodMe= new PeriodMe(d, d);
                Planning _planning = new Planning(periodMe);
                t.setEtat(Etat.INPROGRESS);
                t.setDate(d);
                _planning.addSingleTask(t,c);
                this.planning.add(_planning);
                System.out.println("date not prise");
                return true;//planification is done
            }
            else{//un planning contient cette date
                System.out.println("this is the index : "+ isDate(d));
                System.out.println("heda wsh raj3li creneau occuppee: "+ creneauOccupe(c, isDate(d)));
                    if(creneauOccupe(c, isDate(d))){
                    //2eme cas : le créneau est occupé, planification impossible
                        System.out.println("impoooo");
                        return false;
                    }
                    else{
                        System.out.println("dkhalt lel elseee : ");

                        if (creneauLibre(d, c, isDate(d))){
                            //3eme cas : le créneau est libre
                            /*il faut vérifier si le créneau est divisible ou non puis planifier*/
                            if(c.isDivisible(t.getDuree())){
                                c.updateCreneau(t.getDuree());
                                System.out.println("divisible");
                            }
                            else{
                                /*s'il n'est pas divisible je l'enlève de la liste des créneaux libres et je planifie */
                                planning.get(isDate(d)).getPeriod().removeSlot(d, c);
                                System.out.println("not divis");
                            }
                            t.setEtat(Etat.INPROGRESS);
                            t.setDate(d);
                            planning.get(isDate(d)).addSingleTask(t,c);
                            System.out.println("done");
                            return true;//planification is done
                        }
                        else{
                            if(creneauNotExist(d, c, isDate(d))){
                                // 1er cas : le creneau n'est ni occupé ni libre, ajouter la tache
                                t.setEtat(Etat.INPROGRESS);
                                t.setDate(d);
                                planning.get(isDate(d)).addSingleTask(t,c);
                                System.out.println("creneau libre");
                                return true;
                            }
                        }
                    }
            }
        }
        else{
            return false;
        }
        return false;
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

}
