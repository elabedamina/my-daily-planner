package controlleurs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modals.Utilisateur;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.Node;

public class ControlleurAuthentification implements Initializable {

    @FXML
    private Button connexionBtn;

    @FXML
    private BorderPane firstpage;

    @FXML
    private TextField getPseudo;

    @FXML
    private Button goTo;

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
    private Utilisateur myCurrenUtilisateur= new Utilisateur("test", null, -1);
    private String fileName = "users.dat";

    @FXML
    void handleConnexion(ActionEvent event) {
        String pseudo = pseudoField.getText();
        if (!pseudo.isEmpty()){
            ArrayList<Utilisateur> userList;
            try {
                FileInputStream fileIn = new FileInputStream(fileName);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                userList = (ArrayList<Utilisateur>) in.readObject();
                in.close();
                fileIn.close();
            } catch (IOException | ClassNotFoundException e) {
                userList = new ArrayList<>();
            }

            if( SignedUp(userList,pseudo) == null ){
                Alerts.unauthentifiedPseudo();
            }else{
                myCurrenUtilisateur = SignedUp(userList, pseudo);
                pseudoField.setText("");
                inscriptionBtn.setVisible(false);
                connexionBtn.setVisible(false);
                goTo.setVisible(true);
                System.out.println("sucess");
            }
        }else{//le pseudo est vide
            Alerts.emptyPseudoField();
        }
    }

    @FXML
    void handleInscription(ActionEvent event) {
        firstpage.setVisible(false);
        preference.setVisible(true);
        populateChoiceBoxes();
    }

    @FXML
    void handleSavePreferences(ActionEvent event) {
        String pseudo = getPseudo.getText();
        if (!pseudo.isEmpty()) { 
            myCurrenUtilisateur.setPseudo(pseudo);
            String selectedMinTache = minTache.getSelectionModel().getSelectedItem();
            String selectedDureeMin = minDuree.getSelectionModel().getSelectedItem();
            if (selectedMinTache != null && selectedDureeMin != null) {
                myCurrenUtilisateur.setTacheMin(Integer.parseInt(selectedMinTache));
                myCurrenUtilisateur.setDureeMin(Long.parseLong(selectedDureeMin));
                addUserToFile(myCurrenUtilisateur, fileName);
                getPseudo.setText("");
                Alerts.successfulAuth();
                firstpage.setVisible(true);
                preference.setVisible(false);
                
            } else {
                Alerts.emptyFields();
            }
        }else{//le pseudo est vide
            Alerts.emptyPseudoField();
        }
        
    }
    private Stage stage;
    private Scene scene;
    
    public void switchToScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Planning.fxml"));
        Parent root = loader.load();
        ControlleurPlanning controlleurPlanning = loader.getController();
        controlleurPlanning.setUser(myCurrenUtilisateur);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        if(SignedUp(userList,newUser.getPseudo()) == null) {
            userList.add(newUser);
            // Write the entire ArrayList of User objects to the file
            try {
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

    private static Utilisateur SignedUp(ArrayList<Utilisateur> userList, String pseudo){   
        for (Utilisateur user : userList) {
            if (user.getPseudo().equals(pseudo)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstpage.setVisible(true);
        preference.setVisible(false);
        goTo.setVisible(false);
        
    }

}