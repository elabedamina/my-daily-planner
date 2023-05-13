import java.util.*;
import java.time.*;
import java.text.SimpleDateFormat;

public class Simple extends Tache {
    private int periodicite;

    public Simple(String nom, float duree, String date_limite, Priorite priorite, Categorie categorie, Etat etat,
            int periodicite) {
        super(nom, duree, date_limite, priorite, categorie, etat);
        this.periodicite = periodicite;
    }

    public void evaluerEtat() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String date_courrante = formatter.format(date);
        if ((etat != Etat.COMPLITED) && (etat != Etat.CANCELLED) && (etat != Etat.DELAYED)) {
            if (date_courrante.compareTo(date_limite) > 0) {
                etat = Etat.notREALIZED;
                /* demander l'utilisateur s'il souhaite la replanifier ou l'annuler */
            } else
                etat = Etat.inPROGRESS;

        }

    }
}
