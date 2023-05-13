public class Planing /* implements Planification */ {
    private Calendrier calendrier;
    private int nb_taches_comp; // nombre total de tâches complétées
    private int nb_projets_comp; // nombre total de projets complétés
    private OccurrenceBadge[] tab_badge = new OccurrenceBadge[3]; // tableau contenant les badges déjà obtenus avec
                                                                  // leurs occurrences

    public Planing(Calendrier calendrier, int nb_taches_comp, int nb_projets_comp, OccurrenceBadge[] tab_badge) {
        this.calendrier = calendrier;
        this.nb_taches_comp = nb_taches_comp;
        this.nb_projets_comp = nb_projets_comp;
        this.tab_badge = tab_badge;
    }

    /*
     * public void planifierManuel (Tache tache){}
     * public void planifierAuto (Tache tache){}
     * public void supprimerTache (Tache tache){}
     * public Calendrier majCalendrier (){} //retourne le calendrier après la
     * replanification (mise à jour)
     * public boolean valider (){} //retourne vrai si l'utilisateur souhaite garder
     * la modification
     * public boolean modifier (){} //retourne vrai si l'utilisateur souhaite
     * modifier le planing proposé
     * public void ajouterTache (Tache tache){} //ajouter dans la liste des tâches
     * non planifiées (la même métthode pour reporterTache)
     * public void replanifierTache (Tache tache){}
     */

}
