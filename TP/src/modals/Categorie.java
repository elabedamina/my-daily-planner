package modals;
import javax.management.monitor.CounterMonitor;

import javafx.scene.paint.Color;

public class Categorie {
    private String nom;
    private Color couleur;

    public Categorie(String nom, Color couleur) {
        this.nom = nom;
        this.couleur = couleur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
}
