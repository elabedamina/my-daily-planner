package modals;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public abstract class Tache implements Serializable{
    protected String nom;
    protected Long duree;
    protected Date date_limite;
    protected Priorite priorite;
    protected Categorie categorie;
    protected Etat etat;
    protected LocalDate date;//when planified i add the date

    protected Tache(String nom, Long duree, Date date_limite, Priorite priorite, Categorie categorie) {
        this.nom = nom;
        this.duree = duree;
        this.date_limite = date_limite;
        this.priorite = priorite;
        this.categorie = categorie;
        this.etat = Etat.UNSCHEDULED;
    }

    public abstract void evaluerEtat();

    @Override
    public String toString() {
        return "Tache [nom=" + nom + ", duree=" + duree + ", date_limite=" + date_limite + ", priorite=" + priorite
                + ", categorie=" + categorie + ", etat=" + etat + "]";
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getDuree() {
        return duree;
    }

    public void setDuree(Long duree) {
        this.duree = duree;
    }

    public Date getDate_limite() {
        return date_limite;
    }

    public void setDate_limite(Date date_limite) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
