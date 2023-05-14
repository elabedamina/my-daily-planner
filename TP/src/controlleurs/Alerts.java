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
}
