package modals;

import java.time.LocalDate;
import java.util.ArrayList;

public class Decomposable extends Tache{

    private ArrayList<Simple> tab_taches = new ArrayList<>(); // tableau contenant la décomposition de la tâche en sous-tâches

    public Decomposable(String nom, Long duree, LocalDate date_limite, Priorite priorite, Categorie categorie) {
        super(nom, duree, date_limite, priorite, categorie);
    }

    public Decomposable() {
        super();
    }

    public ArrayList<Simple> getTab_taches() {
        return tab_taches;
    }

    public void setTab_taches(ArrayList<Simple> tab_taches) {
        this.tab_taches = tab_taches;
    }

    public void addNewTask(Simple t){
        tab_taches.add(t);
    }

    @Override
    public String toString() {
        return "Decomposable [tab_taches=" + tab_taches + "]";
    }

}
