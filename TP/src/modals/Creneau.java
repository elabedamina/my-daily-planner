
package modals;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;

public class Creneau implements Serializable{
    
    @Override
    public String toString() {
        return "Creneau [heureDebut=" + heureDebut + ", heureFin=" + heureFin + ", duree=" + duree + ", dureeMin="
                + dureeMin + ", bloque=" + bloque + ", occupe=" + occupe + "]";
    }

    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Long duree;
    private Long dureeMin;
    private boolean bloque; // pour savoir si ce créneau est bloqué pour certaines tâches
    private boolean occupe; // pour savoir si ce créneau contient déjà des tâches planifiées

    public Creneau(LocalTime heureDebut, LocalTime heureFin, Long dureeMin, boolean bloque){
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.duree = Duration.between(heureDebut, heureFin).toMinutes();
        this.dureeMin=dureeMin;
        this.bloque = bloque;
        this.occupe = false; // initialement le créneau qui vient d'être créé est libre
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

    public boolean isOccupe() {
        return occupe;
    }

    public void setOccupe(boolean occupe) {
        this.occupe = occupe;
    }

    public Long getDureeMin() {
        return dureeMin;
    }

    public void setDureeMin(Long dureeMin) {
        this.dureeMin = dureeMin;
    }
    
}
