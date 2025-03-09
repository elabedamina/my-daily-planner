package modals;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class PeriodMe implements Serializable {

    private LocalDate startDate;
    private LocalDate endDate;
    private Map<LocalDate,ArrayList<Creneau>> mapAvailableSlot= new TreeMap<>();// contains for every day of the period, the available slots
    
    private LocalDate current;
    
    public PeriodMe(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.current = startDate;
    }

    public ArrayList<Creneau> returnCreneauOfTheDay(LocalDate d){
        return mapAvailableSlot.get(d);
    }

    public void extendPerdiod(LocalDate date, ArrayList<Creneau> mySlot){
        endDate=date;
        while (!endDate.isAfter(date)) {
            mapAvailableSlot.put(date, mySlot);
            date = date.plusDays(1);
        }
    }

    public void sortCreneau(){
        for (ArrayList<Creneau> creneaux : mapAvailableSlot.values()) {
            // Sort the creneaux list based on the start time using a custom comparator
            Collections.sort(creneaux, Comparator.comparing(Creneau::getHeureDebut));
        }
    }

    public boolean containsDate(LocalDate date) {
        //retourne vrai si la période contient date
        return mapAvailableSlot.containsKey(date);
    }

    public Creneau findCreneauDispo(LocalDate d, Long duree) {
        //trouver créneau disponible de la journée that fits the task
        ArrayList<Creneau> creneauxList = mapAvailableSlot.get(d);
        Iterator<Creneau> iterator = creneauxList.iterator();
        while (iterator.hasNext()) {
            Creneau currentCreneau = iterator.next();
            if (currentCreneau.getDuree()>= duree) {
                return currentCreneau; 
            }
        } 
        return null;
    }

    public int containsDateAndCreneau(LocalDate date, Creneau creneau) {
        //retourne l'index si la période contient date+créneau
        ArrayList<Creneau> creneauxList = mapAvailableSlot.get(date);
        if (creneauxList != null) {
            return creneauxList.indexOf(creneau);
        }
        return -1;
    }

    public void removeSlot(LocalDate d, Creneau c) {
        ArrayList<Creneau> creneauxList = mapAvailableSlot.get(d);
        if (creneauxList != null) {
            creneauxList.remove(c);
        }
    }

    public void addSlot(LocalDate d, Creneau c) {
        ArrayList<Creneau> creneauxList = mapAvailableSlot.get(d);
        if (creneauxList == null) {
            creneauxList = new ArrayList<>();
        }
        creneauxList.add(c);
        mapAvailableSlot.put(d, creneauxList);
    }
   
    public boolean isTacheSimple(){
        return (this.startDate.equals(this.endDate));
    }

    public void setAllAvailableSlots(ArrayList<Creneau> mySlot) {
        // Tous les jours ont le même creneau libre
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            ArrayList<Creneau> mySlot_ = new ArrayList<>();
            for (Creneau creneau : mySlot) {
                Creneau newCreneau = new Creneau(
                    creneau.getHeureDebut(),
                    creneau.getHeureFin(),
                    creneau.getDureeMin(),false
                );
                mySlot_.add(newCreneau);
            }
            mapAvailableSlot.put(currentDate, mySlot_);
            currentDate = currentDate.plusDays(1);
        }
    }
    
    
    public void setSpecificAvailableSlot(ArrayList<Creneau> mySlot){ //chaque jour a des créneaux différents
        ArrayList<Creneau> input=new ArrayList<>();
        // créer une nouvelle instance pour ne pas modifier les autres valeurs des "keys",
        //CUZ i used the same reference of the arrayList in the controller
        input.addAll(mySlot);
        if(current.isAfter(endDate)){
            System.out.println("Cette date n'est pas dans la période du planning");
        }
        else{
            
            mapAvailableSlot.put(current,input );
            current = current.plusDays(1);
        } 
    }
    
    public boolean overlaps(PeriodMe other) {
        return !(other.getEndDate().isBefore(startDate) || other.getStartDate().isAfter(endDate));
    }

    public boolean overlaps2(PeriodMe other) {
        return (other.getEndDate().isEqual(endDate) && other.getStartDate().isEqual(startDate));
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


    @Override
    public String toString() {
        return "\nPeriodMe [La date de début : " + startDate + "\nLa date de fin : " + endDate + "\nLes créneaux dispo de la période : " + printMap()+ "]";
    }

    public String printMap(){
        String myText = "";
        for (Map.Entry<LocalDate, ArrayList<Creneau> > entry : mapAvailableSlot.entrySet()) {
           myText= myText+"\nLa date : "+ entry.getKey()+"Le créneau : "+ entry.getValue();
        }
        return myText;
    }


    public Map<LocalDate, ArrayList<Creneau>> getMapAvailableSlot() {
        return mapAvailableSlot;
    }


    public void setMapAvailableSlot(Map<LocalDate, ArrayList<Creneau>> mapAvailableSlot) {
        this.mapAvailableSlot = mapAvailableSlot;
    }

}