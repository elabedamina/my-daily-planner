package modals;
import java.io.Serializable;
import java.util.*;

public abstract class Tache implements Serializable{
    protected String nom;
    protected float duree;
    protected Date date_limite;
    protected Priorite priorite;
    protected Categorie categorie;
    protected Etat etat;
    //add date l la tache

    protected Tache(String nom, float duree, Date date_limite, Priorite priorite, Categorie categorie, Etat etat) {
        this.nom = nom;
        this.duree = duree;
        this.date_limite = date_limite;
        this.priorite = priorite;
        this.categorie = categorie;
        this.etat = etat;
    }

    public abstract void evaluerEtat();

    @Override
    public String toString() {
        return "Tache [nom=" + nom + ", duree=" + duree + ", date_limite=" + date_limite + ", priorite=" + priorite
                + ", categorie=" + categorie + ", etat=" + etat + "]";
    }

}
