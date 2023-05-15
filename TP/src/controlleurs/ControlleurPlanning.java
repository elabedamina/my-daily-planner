package controlleurs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
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
    private Button goBack1;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private BorderPane historyPage;

    @FXML
    private ListView<?> listCurrentPlanning;

    @FXML
    private ListView<?> listPlanning1;

    @FXML
    private ListView<LocalDate> daysDisplayed;

    @FXML
    private BorderPane newPlanningPage;

    @FXML
    private BorderPane planningPage;

    @FXML
    private Button viewHistoryButton;

    private LocalDate startDate;
    private LocalDate endDate;
    private Utilisateur myCurrenUtilisateur;
    private String fileName = "users.dat";

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
    }

    @FXML
    void handleGoingBack(ActionEvent event) {
        planningPage.setVisible(true);
        CurrentPlanningPage.setVisible(false);
        historyPage.setVisible(false);
        newPlanningPage.setVisible(false);
    }

    @FXML
    void handleHistory(ActionEvent event) {
        planningPage.setVisible(false);
        CurrentPlanningPage.setVisible(false);
        historyPage.setVisible(true);
        newPlanningPage.setVisible(false);
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
                PeriodMe period = new PeriodMe(startDate, endDate);
                if(myCurrenUtilisateur.isPeriodAvailable(period)){
                    Planning myCurrentPlanning = new Planning(period);
                    updateUserFile(myCurrenUtilisateur, fileName, myCurrentPlanning);
                    //populateListDays(myCurrentPlanning);
                }
                else{
                    Alerts.errorPeriod();;
                }
            }
        }      
    }

    @FXML
    void handleStartDate(ActionEvent event) {
        startDate = startDatePicker.getValue();
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

    /*void populateListDays(Planning planning){
        LocalDate startDate = myCurrenUtilisateur.getPlanningNoI().get // create a method that returns the index of a desired planning
        List<LocalDate> dateList = period.getDates();
        
        // Add the dates to the ObservableList
        ObservableList<LocalDate> observableDateList = FXCollections.observableArrayList(dateList);
        this.daysDisplayed.setItems(observableDateList);
    } */


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        planningPage.setVisible(true);
        CurrentPlanningPage.setVisible(false);
        historyPage.setVisible(false);
        newPlanningPage.setVisible(false);
    }

}

