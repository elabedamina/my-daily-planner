package controlleurs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

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
    private BorderPane historyPage;

    @FXML
    private ListView<?> listCurrentPlanning;

    @FXML
    private ListView<?> listPlanning1;

    @FXML
    private BorderPane newPlanningPage;

    @FXML
    private BorderPane planningPage;

    @FXML
    private Button viewHistoryButton;

    @FXML
    void handleCreation(ActionEvent event) {

    }

    @FXML
    void handleCurrentPlanning(ActionEvent event) {

    }

    @FXML
    void handleDatePicker(ActionEvent event) {

    }

    @FXML
    void handleGoingBack(ActionEvent event) {

    }

    @FXML
    void handleHistory(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        planningPage.setVisible(true);
        CurrentPlanningPage.setVisible(false);
        historyPage.setVisible(false);
        newPlanningPage.setVisible(false);
    }
}

