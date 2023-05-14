package controlleurs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import modals.Calendrier;
import modals.Planning;
import modals.Utilisateur;
import controlleurs.Alerts;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControlleurAuthentification implements Initializable {

    @FXML
    private Button connexionBtn;

    @FXML
    private BorderPane createaccount;

    @FXML
    private BorderPane firstpage;

    @FXML
    private TextField getPseudo;

    @FXML
    private Button inscriptionBtn;

    @FXML
    private ChoiceBox<String> minDuree;

    @FXML
    private Button Commencer;

    @FXML
    private ChoiceBox<String> minTache;

    @FXML
    private Button nextButton;

    @FXML
    private BorderPane preference;

    @FXML
    private TextField pseudoField;
    private String myLabel;

    private Utilisateur myCurrenUtilisateur= new Utilisateur("test", new Planning(null, null, null), -1, -1);
    private String fileName = "users.dat";

    @FXML
    void handleConnexion(ActionEvent event) {
        String pseudo = pseudoField.getText();
      if (!pseudo.isEmpty()){
        File file = new File("pseudos.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // tester si le pseudo figure dans le fichier
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(pseudo)) {
                    // implement the home page and call it
                    System.out.println("oui");
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    //si le psuedo est introuvable
        Alerts.unauthentifiedPseudo();
    }else{//le pseudo est vide
        Alerts.emptyPseudoField();
    }}

    @FXML
    void handleInscription(ActionEvent event) {
        createaccount.setVisible(true);
        firstpage.setVisible(false);
        preference.setVisible(false);
    }

    @FXML
    void handleNewInscription(ActionEvent event) {
        String pseudo = getPseudo.getText();
        if (!pseudo.isEmpty()) { 
            myCurrenUtilisateur.setPseudo(pseudo);
            createaccount.setVisible(false);
            firstpage.setVisible(false);
            preference.setVisible(true);
            populateChoiceBoxes();
        }else{//le pseudo est vide
            Alerts.emptyPseudoField();
        }
    }
    @FXML
void handleSavePreferences(ActionEvent event) {
    String selectedMinTache = minTache.getSelectionModel().getSelectedItem();
    String selectedDureeMin = minDuree.getSelectionModel().getSelectedItem();

    if (selectedMinTache != null && selectedDureeMin != null) {
        myCurrenUtilisateur.setTacheMin(Integer.parseInt(selectedMinTache));
        myCurrenUtilisateur.setDureeMin(Integer.parseInt(selectedDureeMin));
        addUserToFile(myCurrenUtilisateur, fileName);
    } else {
        Alerts.emptyFields();
    }
}

   
    public void populateChoiceBoxes() {
        String[] minTaches = {"2", "3", "4", "5"};
        ObservableList<String> minTachesList = FXCollections.observableArrayList(minTaches);
        minTache.setItems(minTachesList);
        String[] minDurees = {"30", "45", "60"};
        ObservableList<String> minDureeList = FXCollections.observableArrayList(minDurees);
        minDuree.setItems(minDureeList);
    }


    public static void addUserToFile(Utilisateur newUser, String fileName) {
        // Create an ArrayList to store all the User objects
        ArrayList<Utilisateur> userList;
    
        try {
            // Load the existing User objects from the file, if it exists
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            userList = (ArrayList<Utilisateur>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            // If the file doesn't exist or cannot be loaded, create a new ArrayList
            userList = new ArrayList<>();
        }
    
        // Add the new User object to the ArrayList
        if(!alreadySignedUp(userList,newUser)) {
            userList.add(newUser);
            try {
                // Write the entire ArrayList of User objects to the file
                FileOutputStream fileOut = new FileOutputStream(fileName);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(userList);
                out.close();
                fileOut.close();
        
                System.out.println("User objects saved to " + fileName);
        
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Alerts.usedPseudo();
        }
    }

    private static boolean alreadySignedUp(ArrayList<Utilisateur> userList, Utilisateur newUser){
        boolean userExists = false;
        for (Utilisateur user : userList) {
            if (user.getPseudo().equals(newUser.getPseudo())) {
                userExists = true;
                break;
            }
        }
        return userExists;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createaccount.setVisible(false);
        firstpage.setVisible(true);
        preference.setVisible(false);
    }

}