
package modals;
import exceptions.HeureFinAvantHeureDebutException;
public class Creneau {
    private float heure_debut;
    private float heure_fin;
    private float duree;
    private float duree_min; // durée minimale d'un créneau
    private boolean bloque; // pour savoir si ce créneau est bloqué pour certaines tâches
    private boolean occupe; // pour savoir si ce créneau contient déjà des tâches planifiées

    public Creneau(float heure_debut, float heure_fin, float duree_min, boolean bloque)
            throws HeureFinAvantHeureDebutException {
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        if (heure_fin < heure_debut)
            throw new HeureFinAvantHeureDebutException();
        else {
            this.duree = heure_fin - heure_debut;
            System.out.println("SUCCES!");
        }
        this.bloque = bloque;
        this.duree_min = duree_min;
        this.occupe = false; // initialement le créneau qui vient d'être créé est libre
    }

    public float getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(float heure_debut) {
        this.heure_debut = heure_debut;
    }

    public float getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(float heure_fin) {
        this.heure_fin = heure_fin;
    }

    public float getDuree() {
        return duree;
    }

    public void setDuree(float duree) {
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

    public boolean divisible(Tache tache) {
        if (tache.getDuree() > this.duree)
            return false;
        else {
            if ((this.duree - tache.getDuree()) < this.duree_min)
                return false;
            else
                return true;
        }
    }
}
