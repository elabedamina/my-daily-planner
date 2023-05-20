
package modals;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;

public class Creneau implements Serializable{
    
    @Override
    public String toString() {
        return "Creneau [heureDebut=" + heureDebut + ", heureFin=" + heureFin + ", duree=" + duree + ", dureeMin="
                + dureeMin + ", bloque=" + bloque + "]";
    }

    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Long duree;
    private Long dureeMin;
    private boolean bloque; // pour savoir si ce créneau est bloqué pour certaines tâches

    public Creneau(LocalTime heureDebut, LocalTime heureFin, Long dureeMin, boolean bloque){
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.duree = Duration.between(heureDebut, heureFin).toMinutes();
        this.dureeMin=dureeMin;
        this.bloque = bloque;
    }

    public Creneau(){}
 
    public boolean isDivisible(Long dureeTache){
        //tester si un créneau est divisible
        boolean isDiv = false;
        if(duree-dureeTache>=dureeMin){
            isDiv=true;
        }
        else{
            isDiv=false;
        }
        return isDiv;
    }
    
    public void updateCreneau(Long dureeTache){
        //si le creneau est divisible on change les intervalles de temps 
        heureDebut = heureDebut.plusMinutes(dureeTache);
    }
    
    public boolean overlaps(Creneau creneau1, Creneau creneau2) {
    return creneau1.getHeureDebut().isBefore(creneau2.getHeureFin())
        && creneau1.getHeureFin().isAfter(creneau2.getHeureDebut()) ||
        creneau1.getHeureDebut().equals(creneau2.getHeureFin())
        || creneau1.getHeureFin().equals(creneau2.getHeureDebut());
    }

    

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    public Long getDuree() {
        return duree;
    }

    public void setDuree(Long duree) {
        this.duree = duree;
    }

    public boolean isBloque() {
        return bloque;
    }

    public void setBloque(boolean bloque) {
        this.bloque = bloque;
    }

    public Long getDureeMin() {
        return dureeMin;
    }

    public void setDureeMin(Long dureeMin) {
        this.dureeMin = dureeMin;
    }
    
}
