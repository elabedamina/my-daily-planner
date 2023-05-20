package modals;
import java.time.LocalDate;

public class Simple extends Tache {
    
    @Override
    public String toString() {
        return super.toString() + "\n---->Simple [periodicite=" + periodicite + "]";
    }

    private int periodicite;

    public Simple(String nom, Long duree, LocalDate date_limite, Priorite priorite, Categorie categorie,
            int periodicite) {
        super(nom, duree, date_limite, priorite, categorie);
        this.periodicite = periodicite;
    } 
    
    public Simple() {
        super();
    }  

    public int getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(int periodicite) {
        this.periodicite = periodicite;
    }

   
}
