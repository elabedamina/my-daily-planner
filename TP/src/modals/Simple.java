package modals;
import java.util.*;

public class Simple extends Tache {
    @Override
    public String toString() {
        return "Simple [periodicite=" + periodicite + "]";
    }

    private int periodicite;

    public Simple(String nom, float duree, Date date_limite, Priorite priorite, Categorie categorie, Etat etat,
            int periodicite) {
        super(nom, duree, date_limite, priorite, categorie, etat);
        this.periodicite = periodicite;
    }

    
    

    public int getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(int periodicite) {
        this.periodicite = periodicite;
    }

    @Override
    public void evaluerEtat() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'evaluerEtat'");
    }
}
