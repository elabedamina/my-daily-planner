package modals;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ListIterator;

import controlleurs.Alerts;

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
    
    public void addUserToFile( String fileName) {
        // Create an ArrayList to store all the User objects
        ArrayList<Utilisateur> userList;
        try {
            // Load the existing User objects from the file, if it exists
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            userList = (ArrayList<Utilisateur>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            // If the file doesn't exist or cannot be loaded, create a new ArrayList
            userList = new ArrayList<>();
        }
        // Add the new User object to the ArrayList
        if (SignedUp(userList, this.getPseudo()) == null) {
            userList.add(this);
            // Write the entire ArrayList of User objects to the file
            try {
                FileOutputStream fileOut = new FileOutputStream(fileName);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(userList);
                out.close();
                fileOut.close();
                System.out.println("User objects saved to " + fileName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alerts.usedPseudo();
        }
    }

    public Utilisateur SignedUp(ArrayList<Utilisateur> userList, String pseudo) {
        for (Utilisateur user : userList) {
            if (user.getPseudo().equals(pseudo)) {
                return user;
            }
        }
        return null;
    }

    public void updateUserFile(String fileName, Planning planning) {
        ArrayList<Utilisateur> userList;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            userList = (ArrayList<Utilisateur>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            userList = new ArrayList<>();
        }
        int index = -1;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getPseudo().equals(this.getPseudo())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            userList.get(index).setPlanning(planning);
            System.out.println("this is it " + userList.get(index));
            try {
                FileOutputStream fileOut = new FileOutputStream(fileName);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(userList);
                objectOut.close();
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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


}
