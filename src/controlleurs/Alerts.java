package controlleurs;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import modals.Planning;

public class Alerts {

    static final String ERROR = "Erreur";

    public static void emptyPseudoField() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Pseudo vide !");
        alert.setContentText("Veuillez introduire votre pseudo.");
        alert.showAndWait();
    }

    public static void usedPseudo() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Pseudo déjà utilisé !");
        alert.setContentText("Veuillez introduire un autre pseudo.");
        alert.showAndWait();
    }

    public static void unauthentifiedPseudo() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Pseudo introuvable !");
        alert.setContentText("Le pseudo que vous avez introduit est introuvable.");
        alert.showAndWait();
    }

    public static void emptyFields() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Choix non introduit.");
        alert.setContentText("Veuillez remplir les champs concernés !");
        alert.showAndWait();
    }

    public static void successfulAuth() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Inscription réussie!");
        alert.setHeaderText(null);
        alert.setContentText("Votre inscription a été faite avec succès.\nConnectez-vous pour accéder à votre compte.");
        alert.showAndWait();
    }

    public static void successfulPlan() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Planification réussie!");
        alert.setHeaderText(null);
        alert.setContentText("La planification a été faite avec succès.");
        alert.showAndWait();
    }

    public static void planningAdded() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Planning ajouté!");
        alert.setHeaderText(null);
        alert.setContentText("Votre nouveau planning a été crée avec succès.\n");
        alert.showAndWait();
    }

    public static void successfulCategory() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Catégorie ajoutée!");
        alert.setHeaderText(null);
        alert.setContentText("Votre nouvelle catégorie de tâches a été ajoutée avec succés.\n");
        alert.showAndWait();
    }

    public static void successfulDeleteCategory() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Catégorie suprimée!");
        alert.setHeaderText(null);
        alert.setContentText("Votre catégorie de tâches a été supprimée avec succés.\n");
        alert.showAndWait();
    }

    public static void displayPlanning(Planning p) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Votre planning suggèré!");
        alert.setHeaderText("Voici le planning que je vous suggère.");
        alert.setContentText(p.toString());
        alert.showAndWait();
    }

    public static void top() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Projet ajouté!");
        alert.setHeaderText(null);
        alert.setContentText(null);
        alert.showAndWait();
    }

    public static void sauvegarder() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Tâche sauvegardée!");
        alert.setHeaderText(null);
        alert.setContentText(
                "La tâche que vous venez d'introduire a été sauvegardée\navec succès dans la liste de tâches non planifiées.\n");
        alert.showAndWait();
    }

    public static void Feliciter() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Bravo !");
        alert.setHeaderText(null);
        alert.setContentText("Félicitations! Vous étiez efficace aujourd'hui\n");
        alert.showAndWait();
    }

    public static void FeliciterGood() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Badge GOOD");
        alert.setHeaderText(null);
        alert.setContentText("Félicitations! Vous avez obtenu le badge GOOD\n");
        alert.showAndWait();
    }

    public static void FeliciterVeryGood() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Badge VeryGOOD");
        alert.setHeaderText(null);
        alert.setContentText("Félicitations! Vous avez obtenu le badge VeryGOOD\n");
        alert.showAndWait();
    }

    public static void FeliciterEXCELLENT() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Badge EXCELLENT");
        alert.setHeaderText(null);
        alert.setContentText("Félicitations! Vous avez obtenu le badge Excellent\n");
        alert.showAndWait();
    }

    public static void modifier() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Modification réussie!");
        alert.setHeaderText(null);
        alert.setContentText("Vos modifications ont été effectuées avec succès\n");
        alert.showAndWait();
    }

    public static void setEtat() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Modification état réussie!");
        alert.setHeaderText(null);
        alert.setContentText("L'état de votre tâche a été mis à jour\n");
        alert.showAndWait();
    }

    public static void errorDate() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Date erronée! ");
        alert.setContentText("La date de fin de période doit être strictement\nsupérieur à la date de fin de période");
        alert.showAndWait();
    }

    public static void errorPlanning() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Planning non trouvé! ");
        alert.setContentText(
                "La période que vous avez spécifiée n'est dans aucun planning.\nVeuillez introduire un nouveau planning avec ces dates\ndans la section <<Ajouter Planning>> du Menu");
        alert.showAndWait();
    }

    public static void errorPlan() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Planification impossible ! ");
        alert.setContentText("Impossible de planifier cette tâche dans la date\nque vous avez introduit");
        alert.showAndWait();
    }

    public static void errorDeadline() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Deadline erronée !");
        alert.setContentText("La date limite de la tâche doit être strictement\nsupérieur à la date d'aujourd'hui");
        alert.showAndWait();
    }

    public static void errorDeadlineandDate() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Date erronée !");
        alert.setContentText("La date de planification doit être\nsupérieur à la date limite");
        alert.showAndWait();
    }

    public static void errorColor() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Erreur Couleur!");
        alert.setContentText("Cette couleur est déjà affectée à une autre catégorie");
        alert.showAndWait();
    }

    public static void errorCategory() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Erreur Nom De Catégorie!");
        alert.setContentText(
                "La nom que vous venez d'introduire\nest soit déjà été affecté à une autre catégorie\nou vous avez laisser le champs vide");
        alert.showAndWait();
    }

    public static void errorDateCurrent() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Date erronée !");
        alert.setContentText("La date début de période doit être \nsupérieur à la date d'aujourd'hui");
        alert.showAndWait();
    }

    public static void errorDuration() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Durée erronée !");
        alert.setContentText("La durée du créneau doit être\nsupérieur à la durée minimale d'un créneau");
        alert.showAndWait();
    }

    public static void errorPeriod() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Période déjà prise !");
        alert.setContentText("Changez les dates. Un autre planning est déjà\nplannifié dans cette période! ");
        alert.showAndWait();
    }

    public static void errorHour() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Heure incorrecte!");
        alert.setContentText("Veuillez introduire l'heure sous la forme HH:MM ");
        alert.showAndWait();
    }

    public static void creaneauExist() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Créneau existant!");
        alert.setContentText("Ce créneau a déjà été créé. Veuillez introduire un autre");
        alert.showAndWait();
    }

    public static void creaneauContained() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Créneau incorrect!");
        alert.setContentText("Ce créneau est inclus dans un autre qui existe. Veuillez introduire un autre ");
        alert.showAndWait();
    }

    public static void tacheEmpty() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Tâche vide!");
        alert.setContentText("Veuillez choisir la tâche que vous souhaitez modifier ");
        alert.showAndWait();
    }

    public static void modifEmpty() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Champs vide!");
        alert.setContentText("Veuillez introduire des données dans les champs que vous souhaitez modifier ");
        alert.showAndWait();
    }

    public static void etatEmpty() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Etat vide!");
        alert.setContentText("Veuillez introduire le nouvel état de la tâche");
        alert.showAndWait();
    }
}
