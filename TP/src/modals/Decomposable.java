package modals;

import java.time.LocalDate;
import java.util.ArrayList;

public class Decomposable extends Tache{

    private ArrayList<Tache> subTaches = new ArrayList<>(); // tableau contenant la décomposition de la tâche en sous-tâches

    public Decomposable(String nom, Long duree, LocalDate date_limite,  Priorite priorite, Categorie categorie) {
        super(nom, duree, date_limite, priorite, categorie);
    }

    public Decomposable() {
        super();
    }

    public void addTosSubTaches(Tache t){
        subTaches.add(t);
    }

    public boolean isRealised() {
        // we see if the etat of all the subTaches is done
        for (Tache tache : subTaches) {
            if (!tache.getEtat().equals(Etat.COMPLETED)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString()+ "\n----> Decomposable [subTaches=" + subTaches + "]";
    }

    public ArrayList<Tache> getSubTaches() {
        return subTaches;
    }

    public void setSubTaches(ArrayList<Tache> subTaches) {
        this.subTaches = subTaches;
    }

}
