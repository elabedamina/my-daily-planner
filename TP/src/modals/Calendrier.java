package modals;
import java.util.ArrayList;

public class Calendrier {
    private Journee[][] mat_journees = new Journee[31][12]; // une matrice qui comporte les journées de chaque mois
    private ArrayList<Projet> projets; // la liste qui contient tous les projets à planifier    
    private ArrayList<Tache> non_planifie; // liste des tâches qui n'ont pas encore été planifiées

    public Calendrier(Journee[][] mat_journees, ArrayList<Projet> projets,ArrayList<Tache> non_planifie) {
        int i, j;
        for (i = 0; i < mat_journees.length; i++) {
            for (j = 0; j < mat_journees[0].length; j++) {
                this.mat_journees[i][j] = mat_journees[i][j];
            }
        }
        this.projets = new ArrayList<Projet>(projets);
        this.non_planifie = new ArrayList<Tache>(non_planifie);
    }
}
