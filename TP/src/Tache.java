import java.time.*;
import java.util.*;

public abstract class Tache {
    protected String nom;
    protected float duree;
    protected String date_limite;
    protected Priorite priorite;
    protected Categorie categorie;
    protected Etat etat;

    public Tache(String nom, float duree, String date_limite, Priorite priorite, Categorie categorie, Etat etat) {
        this.nom = nom;
        this.duree = duree;
        this.date_limite = date_limite;
        this.priorite = priorite;
        this.categorie = categorie;
        this.etat = etat;
    }

    public abstract void evaluerEtat();

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getDuree() {
        return duree;
    }

    public void setDuree(float duree) {
        this.duree = duree;
    }

    public String getDate_limite() {
        return date_limite;
    }

    public void setDate_limite(String date_limite) {
        this.date_limite = date_limite;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }
}
