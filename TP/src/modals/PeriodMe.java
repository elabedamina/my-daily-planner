package modals;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class PeriodMe implements Serializable {

    private LocalDate startDate;
    private LocalDate endDate;
    private Map<LocalDate,Creneau> mapAvailableSlot= new HashMap<>();// contains for every day of the period, the available slot
    
    public PeriodMe(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    
    public void setAllAvailableSlots(LocalTime start, LocalTime end, Long dureeMin){//tous les jours ont le meme creneau libre
        LocalDate currentDate=startDate;
        while (!currentDate.isAfter(endDate)) {
            mapAvailableSlot.put(currentDate, new Creneau(start,end, dureeMin, false));
            currentDate = currentDate.plusDays(1);
        }
    }

    public void setAllAvailableDays(){ //initialiser les jours de la période
        LocalDate currentDate=startDate;
        while (!currentDate.isAfter(endDate)) {
            mapAvailableSlot.put(currentDate, new Creneau());
            currentDate = currentDate.plusDays(1);
        }
    }

    public void setSpecificAvailableSlot(LocalDate date,LocalTime start, LocalTime end, Long dureeMin, boolean bloque){        
        //chaque jour a un creneau differnet
        for (Map.Entry<LocalDate, Creneau> entry : mapAvailableSlot.entrySet()) {
            if(entry.getKey().equals(date)){
                entry.setValue( new Creneau(start,end, dureeMin, bloque));
            }
        }
    }
    
    public boolean overlaps(PeriodMe other) {
        return this.startDate.isEqual(other.startDate) && other.endDate.isEqual(this.endDate);
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

    public Map<LocalDate, Creneau> getMapAvailableSlot() {
        return mapAvailableSlot;
    }


    public void setMapAvailableSlot(Map<LocalDate, Creneau> mapAvailableSlot) {
        this.mapAvailableSlot = mapAvailableSlot;
    }

    @Override
    public String toString() {
        return "PeriodMe [La date de début : " + startDate + "\nLa date de fin : " + endDate + "Les créneaux de la période : " + printMap()+ "]";
    }

    public String printMap(){
        String myText = "";
        for (Map.Entry<LocalDate, Creneau> entry : mapAvailableSlot.entrySet()) {
           myText= myText+"La date : "+ entry.getKey()+"Le créneau : "+ entry.getValue();
        }
        return myText;
    }

}