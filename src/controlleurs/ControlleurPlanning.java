package controlleurs;

import java.io.IOException;
import java.net.URL;
import javafx.scene.Node;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modals.Badge;
import modals.Creneau;
import modals.PeriodMe;
import modals.Planify;
import modals.Planning;
import modals.Utilisateur;

public class ControlleurPlanning implements Initializable {

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
    private Label datedebut;

    @FXML
    private Label datefin;

    @FXML
    private Label datecreneau1;

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
    private Button nextCreneauBtn;

    @FXML
    private Text text;

    @FXML
    private Text textCreneau;

    @FXML
    private Text textExp;

    @FXML
    private ImageView horloge;

    @FXML
    private ImageView planning;

    @FXML
    private Button viewHistoryButton;

    @FXML
    private Button confirmCreneau2;

    @FXML
    private Button changeSceneBtn;

    @FXML
    private BorderPane profilePage;

    @FXML
    private ChoiceBox<String> modifDureeMin;

    @FXML
    private ChoiceBox<String> modifNbMin;

    @FXML
    private Label rendement;

    @FXML
    private Label felicitation;

    @FXML
    private Label good;

    @FXML
    private Label veryGood;

    @FXML
    private Label excellent;

    @FXML
    private Button profileBtn;

    @FXML
    private Button modifPrefBtn;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startHeure;
    private LocalTime EndHeure;
    private Utilisateur myCurrenUtilisateur;
    private Planify planify = Planify.getInstance();
    private PeriodMe period;
    private ArrayList<Creneau> mySlots = new ArrayList<>();
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private Stage stage;
    private Scene scene;
    LocalDate currentDate = LocalDate.now();

    @FXML
    private void handleCreation(ActionEvent event) {
        planningPage.setVisible(false);
        CurrentPlanningPage.setVisible(false);
        historyPage.setVisible(false);
        profilePage.setVisible(false);
        newPlanningPage.setVisible(true);
        confirmDate.setVisible(true);
        planning.setVisible(true);
        textCreneau.setVisible(true);
        startDatePicker.setVisible(true);
        endDatePicker.setVisible(true);
        datedebut.setVisible(true);
        datefin.setVisible(true);
        confirmCreneau2.setVisible(false);
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
        datecreneau1.setVisible(false);
        ouiBtn.setDisable(false);
        nonBtn.setDisable(false);
    }

    @FXML
    private void handleCurrentPlanning(ActionEvent event) {
        planningPage.setVisible(false);
        CurrentPlanningPage.setVisible(true);
        historyPage.setVisible(false);
        newPlanningPage.setVisible(false);
        profilePage.setVisible(false);
        populateCurrentListPlanning();
        handleItemClicks();
    }

    @FXML
    private void handleGoingBack(ActionEvent event) {
        planningPage.setVisible(true);
        CurrentPlanningPage.setVisible(false);
        historyPage.setVisible(false);
        newPlanningPage.setVisible(false);
        profilePage.setVisible(false);
        listCurrentPlanning.getItems().clear();
        hideFields();
    }

    @FXML
    private void handleHistory(ActionEvent event) {
        planningPage.setVisible(false);
        CurrentPlanningPage.setVisible(false);
        historyPage.setVisible(true);
        newPlanningPage.setVisible(false);
        profilePage.setVisible(false);
        populateListPlanning();
        handleItemClicks2();
    }

    private void populateListPlanning() {// l'historique
        ArrayList<Planning> myPlannings = myCurrenUtilisateur.getPlanning();
        for (Planning _planning : myPlannings) {
            if (_planning.getPeriod().getEndDate().isBefore(currentDate)
                    && _planning.getPeriod().getStartDate().isBefore(currentDate)) {
                listPlanning1.getItems().add(_planning);
            }
        }
    }

    private void handleItemClicks2() {// pour mieux visualiser les informations du planning archivés
        listPlanning1.setOnMouseClicked(event -> {
            Planning clickedPlanning = listPlanning1.getSelectionModel().getSelectedItem();
            Alert d = new Alert(AlertType.INFORMATION, clickedPlanning.toString());
            d.setTitle("Votre Planning");
            d.setHeaderText("Les informations de votre planning");
            d.show();
        });
    }

    private void populateCurrentListPlanning() {// les plannings qui sont en cours
        ArrayList<Planning> myPlannings = myCurrenUtilisateur.getPlanning();
        int nbPlanning = 0;
        for (Planning _planning : myPlannings) {
            if (_planning.getPeriod().getEndDate().isAfter(currentDate)
                    && _planning.getPeriod().getStartDate().isBefore(currentDate)) {
                nbPlanning = nbPlanning + 1;
            } else {
                listCurrentPlanning.getItems().add(_planning);
            }
        }
    }

    private void handleItemClicks() {// pour mieux visualiser les informations du planning courant
        listCurrentPlanning.setOnMouseClicked(event -> {
            Planning clickedPlanning = listCurrentPlanning.getSelectionModel().getSelectedItem();
            Alert d = new Alert(AlertType.INFORMATION, clickedPlanning.toString());
            d.setTitle("Votre Planning");
            d.setHeaderText("Les informations de votre planning");
            d.show();
        });
    }

    @FXML
    private void hideFields() {
        textExp.setVisible(false);
        nonBtn.setVisible(false);
        ouiBtn.setVisible(false);
        nextBtn.setVisible(false);
        dateCreneau.setVisible(false);
        text.setVisible(false);
        labelHeureDebut.setVisible(false);
        labelHeureFin.setVisible(false);
        heureDebut.setVisible(false);
        heureFin.setVisible(false);
        confirmCreneau.setVisible(false);
        nextCreneauBtn.setVisible(false);
    }

    @FXML
    private void handleChangeScene2(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Tasks.fxml"));
        Parent root = loader.load();
        ControlleurTask controlleurTask = loader.getController();
        controlleurTask.setUser(myCurrenUtilisateur);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleEndDate(ActionEvent event) {
        endDate = endDatePicker.getValue();
    }

    @FXML
    private void handlePeriod(ActionEvent event) {
        // get the period of the new planning
        if (startDate == null || endDate == null) {
            Alerts.emptyFields();
        } else {
            if (endDate.isBefore(startDate) || endDate.isEqual(startDate)) {
                Alerts.errorDate();
            } else {
                if (startDate.isBefore(currentDate)) {
                    Alerts.errorDateCurrent();
                } else {
                    period = new PeriodMe(startDate, endDate);
                    if (myCurrenUtilisateur.isPeriodAvailable(period)) {
                        textExp.setVisible(true);
                        nonBtn.setVisible(true);
                        ouiBtn.setVisible(true);
                        horloge.setVisible(true);
                        confirmDate.setVisible(false);
                        planning.setVisible(false);
                        textCreneau.setVisible(false);
                        startDatePicker.setVisible(false);
                        endDatePicker.setVisible(false);
                        datedebut.setVisible(false);
                        datefin.setVisible(false);
                    } else {
                        Alerts.errorPeriod();
                    }
                }

            }
        }
    }

    @FXML
    private void handleNon(ActionEvent event) {
        nextBtn.setVisible(true);
        ouiBtn.setDisable(true);
        dateCreneau.setVisible(true);
        textCreneau.setVisible(false); // à vérifier
        text.setVisible(true);
        labelHeureDebut.setVisible(true);
        labelHeureFin.setVisible(true);
        datecreneau1.setVisible(true);
        heureDebut.setVisible(true);
        heureFin.setVisible(true);
        confirmCreneau.setVisible(false);
        confirmCreneau2.setVisible(false);
        nonBtn.setVisible(false);
        ouiBtn.setVisible(false);
        textExp.setVisible(false);
        nextCreneauBtn.setVisible(true);
        dateCreneau.setValue(startDate);
    }

    @FXML
    private void handleOui(ActionEvent event) {
        confirmCreneau2.setVisible(false);
        nextCreneauBtn.setVisible(true);
        text.setVisible(true);
        labelHeureDebut.setVisible(true);
        labelHeureFin.setVisible(true);
        heureDebut.setVisible(true);
        heureFin.setVisible(true);
        confirmCreneau.setVisible(true);
        textExp.setVisible(false);
        nonBtn.setVisible(false);
        ouiBtn.setVisible(false);
    }

    @FXML
    private void handleCreneau2(ActionEvent event) {
        // gère la sauvegarde du nouveau planning avec des créneaux différents pour
        // chaque jour
        period.setSpecificAvailableSlot(mySlots);
        Planning myCurrentPlanning = new Planning(period);
        myCurrenUtilisateur.setPlanning(myCurrentPlanning);
        planify.updateUser(myCurrenUtilisateur);
        Alerts.planningAdded();
        handleGoingBack(event);
    }

    @FXML
    private void handleNextCreneau(ActionEvent event) {
        // si l'utilisateur souhaite rajouter un autre créneau pour la même journée
        String myHour1;
        String myHour;
        myHour1 = heureFin.getText();
        myHour = heureDebut.getText();
        try {
            EndHeure = LocalTime.parse(myHour1, dateFormatter);
            startHeure = LocalTime.parse(myHour, dateFormatter);
        } catch (Exception e) {
            Alerts.errorHour();
        }
        Long duration = Duration.between(startHeure, EndHeure).toMinutes();
        if (myCurrenUtilisateur.getDureeMin() > duration) {
            Alerts.errorDuration();
        } else {
            Creneau creneau = new Creneau(startHeure, EndHeure, myCurrenUtilisateur.getDureeMin(), false);
            if (mySlots.contains(creneau)) {
                Alerts.creaneauExist();
            } else {
                for (Creneau element : mySlots) {
                    if (element.contains(creneau)) { // le créneau introduit est contenu dans un autre qui existe
                        Alerts.creaneauContained();
                        creneau = null;
                    } else {
                        if (element.contains2(creneau)) {
                            /*
                             * Le créneau introduit provoque un chevauchement avec un autre qui existe
                             * => on prend l'union des 2 créneaux (le créneau le plus large posssible)
                             */
                            creneau.modifCreneau(element);
                            creneau = null;
                        }
                    }
                }
            }
            if (creneau != null)
                mySlots.add(creneau);
            System.out.println("n\n Voici les créneaux : " + mySlots + "\n");
        }
        heureDebut.clear();
        heureFin.clear();
    }

    @FXML
    private void handleCreneau(ActionEvent event) {
        // rajouter le nouveau planning à l'utilisateur s'il a choisi le même créneau
        // pour tous les jours
        String myHour1;
        String myHour;
        myHour1 = heureFin.getText();
        myHour = heureDebut.getText();
        try {
            EndHeure = LocalTime.parse(myHour1, dateFormatter);
            startHeure = LocalTime.parse(myHour, dateFormatter);
        } catch (Exception e) {
            Alerts.errorHour();
        }

        Long duration = Duration.between(startHeure, EndHeure).toMinutes();
        if (myCurrenUtilisateur.getDureeMin() > duration) {
            Alerts.errorDuration();
        } else {
            mySlots.add(new Creneau(startHeure, EndHeure, myCurrenUtilisateur.getDureeMin(), false));
            long daysBetween = ChronoUnit.DAYS.between(period.getStartDate(), period.getEndDate());
            int numberOfDays = Math.toIntExact(daysBetween);
            for (int i = 0; i < numberOfDays + 1; i++) {
                period.setSpecificAvailableSlot(mySlots);
            }
            Planning myCurrentPlanning = new Planning(period);
            myCurrenUtilisateur.setPlanning(myCurrentPlanning);
            planify.updateUser(myCurrenUtilisateur);
            Alerts.planningAdded();
            handleGoingBack(event);
        }
        heureDebut.clear();
        heureFin.clear();
    }

    @FXML
    private void handleNext(ActionEvent event) {
        // si l'utilisateur souhaite introduire un créneau différent pour chaque journée
        String myHour2 = heureFin.getText();
        String myHour3 = heureDebut.getText();
        if (!(myHour2.isEmpty()) && !(myHour3.isEmpty())) {
            EndHeure = LocalTime.parse(myHour2, dateFormatter);
            startHeure = LocalTime.parse(myHour3, dateFormatter);
            Long duration = Duration.between(startHeure, EndHeure).toMinutes();
            if (myCurrenUtilisateur.getDureeMin() > duration) {
                Alerts.errorDuration();
            } else {
                mySlots.add(new Creneau(startHeure, EndHeure, myCurrenUtilisateur.getDureeMin(), false));
                period.setSpecificAvailableSlot(mySlots);
            }
        } else {// l'heure est vide
            Alerts.emptyFields();
        }
        startDate = startDate.plusDays(1);
        if (startDate.isBefore(endDate) || startDate.isEqual(endDate)) { // on est pas arrivé à la fin de période
            dateCreneau.setValue(startDate); // passer à la journée suivante
            heureDebut.clear();
            heureFin.clear();
            mySlots.clear();
        } else {
            confirmCreneau2.setVisible(true);
            nextBtn.setVisible(false);
        }
    }

    @FXML
    private void handleStartDate(ActionEvent event) {
        startDate = startDatePicker.getValue();
    }

    public void setUser(Utilisateur user) {
        // to get the infos from the previous controller
        myCurrenUtilisateur = user;
    }

    @FXML
    public void populateChoiceBoxes() {
        String[] minTaches = { "2", "3", "4", "5" };
        ObservableList<String> minTachesList = FXCollections.observableArrayList(minTaches);
        modifNbMin.setItems(minTachesList);
        String[] minDurees = { "30", "45", "60" };
        ObservableList<String> minDureeList = FXCollections.observableArrayList(minDurees);
        modifDureeMin.setItems(minDureeList);
    }

    @FXML
    public void handleModifPref() {
        /* pour modifier les préférences de l'utilisateur */
        String selectedMinTache = modifNbMin.getSelectionModel().getSelectedItem();
        String selectedDureeMin = modifDureeMin.getSelectionModel().getSelectedItem();
        if (selectedDureeMin != null) {
            myCurrenUtilisateur.setDureeMin(Long.parseLong(selectedDureeMin));
            Alerts.modifier();
        }
        if (selectedMinTache != null) {
            myCurrenUtilisateur.setTacheMin(Integer.parseInt(selectedMinTache));
        }
        planify.updateUser(myCurrenUtilisateur);
    }

    @FXML
    public void handleProfile(ActionEvent event) {
        populateChoiceBoxes();
        planningPage.setVisible(false);
        CurrentPlanningPage.setVisible(false);
        historyPage.setVisible(false);
        profilePage.setVisible(true);
        newPlanningPage.setVisible(false);
        felicitation.setText(String.valueOf(myCurrenUtilisateur.getNb_felicitation()));
        good.setText(String.valueOf(myCurrenUtilisateur.getGood()));
        veryGood.setText(String.valueOf(myCurrenUtilisateur.getVeryGOOD()));
        excellent.setText(String.valueOf(myCurrenUtilisateur.getEXCELLENT()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        planningPage.setVisible(true);
        CurrentPlanningPage.setVisible(false);
        historyPage.setVisible(false);
        newPlanningPage.setVisible(false);
        profilePage.setVisible(false);
        hideFields();
    }

}
