import java.util.ArrayList;

public class Projet {

    public String nom;
    public String description;
    public ArrayList<Tache> listeTaches = new ArrayList<>();

    public Projet(String nom, String description, ArrayList<Tache> listeTaches) {
        this.nom = nom;
        this.description = description;
        this.listeTaches = listeTaches;
    }
}
