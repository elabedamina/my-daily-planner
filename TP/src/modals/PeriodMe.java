package modals;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

public class PeriodMe implements Serializable {

    private LocalDate startDate;
    private LocalDate endDate;
    private Map<LocalDate,ArrayList<Creneau>> mapAvailableSlot= new HashMap<>();// contains for every day of the period, the available slots
    
    private LocalDate current;
    
    public PeriodMe(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.current = startDate;
    }

    public boolean containsDate(LocalDate date) {
        //retourne vrai si la période contient date
        return mapAvailableSlot.containsKey(date);
    }

    public int containsDateAndCreneau(LocalDate date, Creneau creneau) {
        //retourne l'index si la période contient date+créneau
        ArrayList<Creneau> creneauxList = mapAvailableSlot.get(date);
        if (creneauxList != null) {
            return creneauxList.indexOf(creneau);
        }
        return -1;
    }

    public void removeSlot(LocalDate d,Creneau c){
        //removes c from the map
        ArrayList<Creneau> creneauxList = mapAvailableSlot.get(d);
        Iterator<Creneau> iterator = creneauxList.iterator();
        while (iterator.hasNext()) {
            Creneau currentCreneau = iterator.next();
            if (currentCreneau.equals(c)) {
                iterator.remove();
                break; 
            }
        }    
    }

    public void updateSlotPeriod(LocalDate d,Creneau oldCreneau, Creneau newCreneau){
        //mettre à jour un créneau dans le cas ou il est divisible
        ArrayList<Creneau> creneauxList = mapAvailableSlot.get(d);
        if (creneauxList != null) {
            ListIterator<Creneau> iterator = creneauxList.listIterator();
            while (iterator.hasNext()) {
                Creneau currentCreneau = iterator.next();
                if (currentCreneau.equals(oldCreneau)) {
                    iterator.set(newCreneau);
                    break;
                }
            }
        }
    }

    public void setAllAvailableSlots(ArrayList<Creneau> mySlot){
        //tous les jours ont le même creneau libre
        LocalDate currentDate=startDate;
        while (!currentDate.isAfter(endDate)) {
            mapAvailableSlot.put(currentDate, mySlot);
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
        return "PeriodMe [La date de début : " + startDate + "\nLa date de fin : " + endDate + "Les créneaux de la période : " + printMap()+ "]";
    }

    public String printMap(){
        String myText = "";
        for (Map.Entry<LocalDate, ArrayList<Creneau> > entry : mapAvailableSlot.entrySet()) {
           myText= myText+"La date : "+ entry.getKey()+"Le créneau : "+ entry.getValue();
        }
        return myText;
    }


    public Map<LocalDate, ArrayList<Creneau>> getMapAvailableSlot() {
        return mapAvailableSlot;
    }


    public void setMapAvailableSlot(Map<LocalDate, ArrayList<Creneau>> mapAvailableSlot) {
        this.mapAvailableSlot = mapAvailableSlot;
    }

    public boolean isTacheSimple(){
        return (this.startDate.equals(this.endDate));
    }

}