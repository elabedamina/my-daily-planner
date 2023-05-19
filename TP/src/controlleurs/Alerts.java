package controlleurs;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
        alert.setContentText("Veuillez choisir vos préférences !");
        alert.showAndWait();
    }
    public static void successfulAuth() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Inscription réussie!");
        alert.setHeaderText(null);
        alert.setContentText("Votre inscription a été faite avec succès.\nConnectez-vous pour accéder à votre compte.");
        alert.showAndWait();
    }
    public static void planningAdded() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Planning ajouté!");
        alert.setHeaderText(null);
        alert.setContentText("Votre nouveau planning a été crée avec succès.\n");
        alert.showAndWait();
    }
    public static void errorDate() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR);
        alert.setHeaderText("Date erronée !");
        alert.setContentText("La date de fin de période doit être strictement\nsupérieur à la date de fin de période");
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
        alert.setContentText("Changez les dates. Un autre planning est déjà plannifié dans cette période! ");
        alert.showAndWait();
    }
}
