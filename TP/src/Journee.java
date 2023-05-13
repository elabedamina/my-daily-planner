import java.util.ArrayList;

public class Journee {
    private String date;
    private ArrayList<Tache> taches; // liste qui comporte les tâches de la journée
    private ArrayList<Creneau> creneau_libre; // liste qui comporte tous les créneaux libres de la journée
    private int nb_taches; // le nombre de tâches complétées dans la journée (pour la félicitation)

    public Journee(String date, ArrayList<Tache> taches, ArrayList<Creneau> creneau_libre, int nb_taches) {

    }
}
