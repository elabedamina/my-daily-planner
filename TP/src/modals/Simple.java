package modals;
import java.util.*;
import java.text.SimpleDateFormat;

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

    public void evaluerEtat() {
       // SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        //Date date = new Date();
        //String date_courrante = formatter.format(date);
        if ((etat != Etat.COMPLETED) && (etat != Etat.CANCELLED) && (etat != Etat.DELAYED)) {
           // if (date_courrante.compareTo(date_limite) > 0) {
        //     etat = Etat.notREALIZED;
           // } else
         //    etat = Etat.inPROGRESS;

        }

    }

    public int getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(int periodicite) {
        this.periodicite = periodicite;
    }
}
