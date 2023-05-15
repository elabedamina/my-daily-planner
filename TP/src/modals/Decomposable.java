package modals;

import java.util.Arrays;
import java.util.Date;

public class Decomposable extends Tache{
    private Tache[] tab_taches = new Tache[30]; // tableau contenant la décomposition de la tâche en sous-tâches

    public Decomposable(String nom, float duree, Date date_limite, Priorite priorite, Categorie categorie, Etat etat,
            Tache[] tab_taches) {
        super(nom, duree, date_limite, priorite, categorie, etat);
        this.tab_taches = tab_taches;
    }

    public void evaluerEtat() {
    } // redéfinition de la méthode abstraite

    @Override
    public String toString() {
        return "Decomposable [tab_taches=" + Arrays.toString(tab_taches) + "]";
    }
}
