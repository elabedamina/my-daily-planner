package controlleurs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Testcontroller {

    @FXML
    private Label pseudoLabel;

    @FXML
    private TextField pseudoField;

    @FXML
    private Button connexionBtn;

    @FXML
    private Button inscriptionBtn;

    @FXML
    void handleConnexion(ActionEvent event) {
        String pseudo = pseudoField.getText();
        File file = new File("pseudos.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        storePseudo(pseudo);
    }

    @FXML
    void handleInscription(ActionEvent event) {
        // code to navigate to another page
    }

    private void storePseudo(String pseudo) {
        try (FileWriter fw = new FileWriter("pseudos.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(pseudo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}