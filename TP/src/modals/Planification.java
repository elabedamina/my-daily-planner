package modals;
public interface Planification {
    public void planifierManuel(Tache tache);

    public void planifierAuto(Tache tache);

    public void supprimerTache(Tache tache);

    public Calendrier majCalendrier(); // retourne le calendrier après la replanification (mise à jour)

    public boolean valider(); // retourne vrai si l'utilisateur souhaite garder la modification

    public boolean modifier(); // retourne vrai si l'utilisateur souhaite modifier le planing proposé

    public void ajouterTache(Tache tache); // ajouter dans la liste des tâches non planifiées (la même métthode pour
                                           // reporterTache)

    public void replanifierTache(Tache tache);
}