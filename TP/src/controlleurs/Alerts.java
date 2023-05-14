package controlleurs;

import javafx.scene.control.Alert;

public class Alerts {
    public static void emptyPseudoField() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Pseudo vide !");
            alert.setContentText("Veuillez introduire votre pseudo.");
            alert.showAndWait();
    }
    public static void usedPseudo() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Pseudo déjà utilisé !");
        alert.setContentText("Veuillez introduire un autre pseudo.");
        alert.showAndWait();
    }
    public static void unauthentifiedPseudo() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Pseudo introuvable !");
        alert.setContentText("Le pseudo que vous avez introduit est introuvable.");
        alert.showAndWait();
    }
    public static void emptyFields() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Choix non introduit.");
        alert.setContentText("Veuillez choisir vos préférences !");
        alert.showAndWait();
    }
}
