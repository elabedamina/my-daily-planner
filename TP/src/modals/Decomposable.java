package modals;

import java.util.Arrays;
import java.util.Date;

public class Decomposable extends Tache{
    private Tache[] tab_taches = new Tache[30]; // tableau contenant la décomposition de la tâche en sous-tâches

    public Decomposable(String nom, float duree, Date date_limite, Priorite priorite, Categorie categorie,
            Tache[] tab_taches) {
        super(nom, duree, date_limite, priorite, categorie);
        this.tab_taches = tab_taches;
    }


    @Override
    public String toString() {
        return "Decomposable [tab_taches=" + Arrays.toString(tab_taches) + "]";
    }


    @Override
    public void evaluerEtat() {
        throw new UnsupportedOperationException("Unimplemented method 'evaluerEtat'");
    }


    public Tache[] getTab_taches() {
        return tab_taches;
    }


    public void setTab_taches(Tache[] tab_taches) {
        this.tab_taches = tab_taches;
    }
}
