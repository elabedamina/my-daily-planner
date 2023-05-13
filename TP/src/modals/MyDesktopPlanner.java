package modals;
import java.util.TreeMap;

public class MyDesktopPlanner {

    private TreeMap<String, Utilisateur> utilisateurMap = new TreeMap<>();

    public MyDesktopPlanner(TreeMap<String, Utilisateur> utilisateurMap) {
        this.utilisateurMap = utilisateurMap;
    }

    public void ajouterUtilisateur(String pseudo) {
        if (utilisateurMap.containsKey(pseudo))
            System.out.println("ERREUR! Ce pseudo existe déjà");
        else {
            Utilisateur utilisateur = new Utilisateur(pseudo, null, null); // à revoir
            utilisateurMap.put(pseudo, utilisateur);
            System.out.println("BIENVENUE!");
        }
    }

    public Utilisateur authentifier(String pseudo) {
        if (!utilisateurMap.containsKey(pseudo)) {
            System.out.println("ERREUR! Votre pseudo est incorrect");
            return null;
        } else {
            System.out.println("Authentification faite avec succès!");
            return utilisateurMap.get(pseudo);
        }
    }

    public void supprimerUtilisateur(Utilisateur utilisateur) {

        if (!utilisateurMap.containsKey(utilisateur.getPseudo()))
            System.out.println("ERREUR! Utilisateur inexistant");
        else {
            utilisateurMap.remove(utilisateur.getPseudo());
            System.out.println("Utilisateur supprimé avec succès!");
        }

    }

}
