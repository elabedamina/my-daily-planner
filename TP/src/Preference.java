public class Preference {
    private float dure_min_creneau; // durée minimale d'un créneau
    private int nb_min_tache; // nombre minimal de tâche/jour pour être récompensé

    public Preference(float dure_min_creneau, int nb_min_tache) {
        this.dure_min_creneau = dure_min_creneau;
        this.nb_min_tache = nb_min_tache;
    }

    public float getDure_min_creneau() {
        return dure_min_creneau;
    }

    public void setDure_min_creneau(float dure_min_creneau) {
        this.dure_min_creneau = dure_min_creneau;
    }

    public int getNb_min_tache() {
        return nb_min_tache;
    }

    public void setNb_min_tache(int nb_min_tache) {
        this.nb_min_tache = nb_min_tache;
    }
}
