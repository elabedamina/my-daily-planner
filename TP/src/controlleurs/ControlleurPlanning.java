package controlleurs;

import java.beans.EventHandler;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import modals.PeriodMe;
import modals.Planning;
import modals.Utilisateur;

public class ControlleurPlanning  implements Initializable {

    @FXML
    private BorderPane CurrentPlanningPage;

    @FXML
    private Button confirmDate;

    @FXML
    private Button createBtn;

    @FXML
    private Button currentPlanningBtn;

    @FXML
    private DatePicker dateCreneau;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Button confirmCreneau;

    @FXML
    private Button goBack1;

    @FXML
    private Button goBack2;

    @FXML
    private TextField heureDebut;

    @FXML
    private TextField heureFin;

    @FXML
    private BorderPane historyPage;

    @FXML
    private Label labelHeureDebut;

    @FXML
    private Label labelHeureFin;

    @FXML
    private ListView<Planning> listCurrentPlanning;

    @FXML
    private ListView<Planning> listPlanning1;

    @FXML
    private BorderPane newPlanningPage;

    @FXML
    private Button nonBtn;

    @FXML
    private Button ouiBtn;

    @FXML
    private Button nextBtn;

    @FXML
    private BorderPane planningPage;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private Text text;

    @FXML
    private Text textCreneau;

    @FXML
    private Text textExp;

    @FXML
    private Button viewHistoryButton;


    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate myDate;
    private LocalTime startHeure;
    private LocalTime EndHeure;
    private Utilisateur myCurrenUtilisateur;
    private String fileName = "users.dat";
    private PeriodMe period;
    private String myHour1;
    private String myHour;
    private boolean isBloque;

    @FXML
    void handleCreation(ActionEvent event) {
        planningPage.setVisible(false);
        CurrentPlanningPage.setVisible(false);
        historyPage.setVisible(false);
        newPlanningPage.setVisible(true);
    }

    @FXML
    void handleCurrentPlanning(ActionEvent event) {
        planningPage.setVisible(false);
        CurrentPlanningPage.setVisible(true);
        historyPage.setVisible(false);
        newPlanningPage.setVisible(false);
        populateCurrentListPlanning();
    }

    @FXML
    void handleGoingBack(ActionEvent event) {
        planningPage.setVisible(true);
        CurrentPlanningPage.setVisible(false);
        historyPage.setVisible(false);
        newPlanningPage.setVisible(false);
        listCurrentPlanning.getItems().clear();
    }

    @FXML
    void handleHistory(ActionEvent event) {
        planningPage.setVisible(false);
        CurrentPlanningPage.setVisible(false);
        historyPage.setVisible(true);
        newPlanningPage.setVisible(false);
        populateListPlanning();
        handleItemClicks();
    }

    private void populateListPlanning(){//l'historique
        ArrayList<Planning> myPlannings = myCurrenUtilisateur.getPlanning();
        LocalDate currentDate = LocalDate.now();
        for(Planning planning : myPlannings){
            if(planning.getPeriod().getEndDate().isBefore(currentDate) && planning.getPeriod().getStartDate().isBefore(currentDate)){
                listPlanning1.getItems().add(planning);
            }
        }
    }
    
    private void populateCurrentListPlanning(){//les plannings qui sont en cours
        ArrayList<Planning> myPlannings = myCurrenUtilisateur.getPlanning();
        LocalDate currentDate = LocalDate.now();
        int nbPlanning = 0;
        for(Planning planning : myPlannings){
            if(planning.getPeriod().getEndDate().isBefore(currentDate) && planning.getPeriod().getStartDate().isBefore(currentDate)){
                nbPlanning = nbPlanning+ 1;
            }
            else{
                listCurrentPlanning.getItems().add(planning);
            }
        }
    }   

    private void handleItemClicks(){//afficher les infos d'un planning expiré
            listPlanning1.setOnMouseClicked( event->{
            Planning clickedPlanning = listCurrentPlanning.getSelectionModel().getSelectedItem();
            Dialog d= new Alert(AlertType.INFORMATION,clickedPlanning.toString());
            d.setTitle("Votre Planning");
            d.setHeaderText("Les informations de votre planning");
            d.show();
        });
    }
    
    @FXML
    void hideFields(){
        textExp.setVisible(false);
        nonBtn.setVisible(false);
        ouiBtn.setVisible(false);
        nextBtn.setVisible(false);
        dateCreneau.setVisible(false);
        textCreneau.setVisible(false);
        text.setVisible(false);
        labelHeureDebut.setVisible(false);
        labelHeureFin.setVisible(false);
        heureDebut.setVisible(false);
        heureFin.setVisible(false);
        confirmCreneau.setVisible(false);
    }

    @FXML
    void handleEndDate(ActionEvent event) {
        endDate = endDatePicker.getValue();
    }

    @FXML
    void handlePeriod(ActionEvent event) {
        if(startDate == null || endDate == null ){
            Alerts.emptyFields();
        }
        else{
            if (endDate.isBefore(startDate) || endDate.isEqual(startDate)) {                
                Alerts.errorDate();
            }
            else{
                LocalDate currentDate = LocalDate.now();
                if(startDate.isBefore(currentDate)){
                    Alerts.errorDateCurrent();
                }
                else{
                    period = new PeriodMe(startDate, endDate);
                    if(myCurrenUtilisateur.isPeriodAvailable(period)){
                        textExp.setVisible(true);
                        nonBtn.setVisible(true);
                        ouiBtn.setVisible(true);
                        confirmDate.setVisible(false);
                        /*enter the available slot*/
                    }
                    else{
                        Alerts.errorPeriod();
                    }
                }
                
            }
        }      
    }
    
    @FXML
    void handleNon(ActionEvent event) {
        nextBtn.setVisible(true);
        ouiBtn.setDisable(true);
        dateCreneau.setVisible(true);
        textCreneau.setVisible(true);
        text.setVisible(true);
        labelHeureDebut.setVisible(true);
        labelHeureFin.setVisible(true);
        heureDebut.setVisible(true);
        heureFin.setVisible(true);
        confirmCreneau.setVisible(false);
        period.setAllAvailableDays();
    }

    @FXML
    void handleOui(ActionEvent event) {
        nonBtn.setDisable(true);
        text.setVisible(true);
        labelHeureDebut.setVisible(true);
        labelHeureFin.setVisible(true);
        heureDebut.setVisible(true);
        heureFin.setVisible(true);
        confirmCreneau.setVisible(true);
    }
 

    @FXML
    void handleCreneau(ActionEvent event){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm");      
        myHour1 = heureFin.getText();
        myHour = heureDebut.getText();
        EndHeure = LocalTime.parse(myHour1,dateFormatter);
        startHeure = LocalTime.parse(myHour,dateFormatter);
        Long duration = Duration.between(startHeure, EndHeure).toMinutes();
        if(myCurrenUtilisateur.getDureeMin()>duration){
            Alerts.errorDuration();
        }
        else{
            period.setAllAvailableSlots(startHeure, EndHeure, myCurrenUtilisateur.getDureeMin());
            Planning myCurrentPlanning = new Planning(period);
            myCurrenUtilisateur.setPlanning(myCurrentPlanning);
            updateUserFile(myCurrenUtilisateur, fileName, myCurrentPlanning);
            /*nlew7o l paga jaya */
        }
    }


    @FXML
    void handleStartDate(ActionEvent event) {
        startDate = startDatePicker.getValue();
    }

    @FXML
    void handleDateCreneau(ActionEvent event) {
        myDate = dateCreneau.getValue();
    }

    @FXML
    void handleNext(ActionEvent event){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm");      
        String myHour2 = heureFin.getText();
        String myHour3 = heureDebut.getText();
        EndHeure = LocalTime.parse(myHour2,dateFormatter);
        startHeure = LocalTime.parse(myHour3,dateFormatter);
        Long duration = Duration.between(startHeure, EndHeure).toMinutes();
        if(myCurrenUtilisateur.getDureeMin()>duration){
            Alerts.errorDuration();
        }
        else{
            period.setSpecificAvailableSlot(myDate, startHeure, EndHeure, myCurrenUtilisateur.getDureeMin(), isBloque);
            //period.printMap();
            
        }
        dateCreneau.setValue(null);
        Planning myCurrentPlanning = new Planning(period);
        myCurrenUtilisateur.setPlanning(myCurrentPlanning);
        updateUserFile(myCurrenUtilisateur, fileName, myCurrentPlanning);
        /*nlew7o l paga jaya */
    }

    public void setUser( Utilisateur user ){
        myCurrenUtilisateur=user;
    }

    public static void updateUserFile(Utilisateur userToUpdate, String fileName, Planning planning) {
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
        int index = -1;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getPseudo().equals(userToUpdate.getPseudo())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            userList.get(index).setPlanning(planning);
            System.out.println("this is it "+ userList.get(index));
            try {
                FileOutputStream fileOut = new FileOutputStream(fileName);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(userList);
                objectOut.close();
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        planningPage.setVisible(true);
        CurrentPlanningPage.setVisible(false);
        historyPage.setVisible(false);
        newPlanningPage.setVisible(false);
        hideFields();
    }

}

