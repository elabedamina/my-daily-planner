package modals;

import java.io.Serializable;
import java.time.LocalDate;

public class Tache implements Serializable {

    private String nom;
    private Long duree;
    private LocalDate date_limite;
    private Priorite priorite;
    private Categorie categorie;
    private Etat etat;
    private LocalDate date;// when planified i add the date

    public Tache() {
        this.etat = Etat.UNSCHEDULED;
    }
    
    public Tache(String nom, Long duree, LocalDate date_limite, Priorite priorite, Categorie categorie) {
        this.nom = nom;
        this.duree = duree;
        this.date_limite = date_limite;
        this.priorite = priorite;
        this.categorie = categorie;
        this.etat = Etat.UNSCHEDULED;
    }

    /* public void evaluerEtat(); */

    @Override
    public String toString() {
        return "\n---------\nTache [nom=" + nom + ", duree=" + duree + ", date_limite=" + date_limite + ", priorite="
                + priorite
                + ", categorie=" + categorie + ", etat=" + etat + ", date : " + date + "]";
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

    public LocalDate getDate_limite() {
        return date_limite;
    }

    public void setDate_limite(LocalDate date_limite) {
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
