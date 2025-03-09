package controlleurs;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.plaf.multi.MultiColorChooserUI;

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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modals.Categorie;
import modals.Creneau;
import modals.Decomposable;
import modals.Etat;
import modals.PeriodMe;
import modals.Planify;
import modals.Planning;
import modals.Priorite;
import modals.Projet;
import modals.Simple;
import modals.Tache;
import modals.Utilisateur;
import javafx.scene.Node;

public class ControlleurTask implements Initializable {

    @FXML
    private BorderPane AddPage;

    @FXML
    private ChoiceBox<Priorite> PriorityBox;

    @FXML
    private ChoiceBox<Priorite> modifPriorityBox;

    @FXML
    private BorderPane TaskMainPage;

    @FXML
    private Button addCategoBtn;

    @FXML
    private Button addProjetBtn;

    @FXML
    private Text addTaskText;

    @FXML
    private Text affichageText;

    @FXML
    private Button autoBtn;

    @FXML
    private BorderPane autoPage;

    @FXML
    private Button categoryBtn;

    @FXML
    private TextField categoryField;

    @FXML
    private BorderPane categoryPage;

    @FXML
    private Text categoryText;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Text colorText;

    @FXML
    private Button confirmAutoBtn;

    @FXML
    private Button confirmUpdateBtn;

    @FXML
    private TextField creneauDebutField;

    @FXML
    private Text creneauText;

    @FXML
    private DatePicker dateManualPicker;

    @FXML
    private Text dateManualText;

    @FXML
    private DatePicker deadlineDatePicker;

    @FXML
    private DatePicker modifDeadline;

    @FXML
    private TextField modifDuree;

    @FXML
    private Text modifDureeText;

    @FXML
    private TextField modifNomTache;

    @FXML
    private Text deadlineText;

    @FXML
    private ChoiceBox<Categorie> deleteCategoryBox;

    @FXML
    private ChoiceBox<Categorie> deleteCategoryBox2;

    @FXML
    private ChoiceBox<Categorie> modifCategoryBox;

    @FXML
    private ChoiceBox<String> extendBox;

    @FXML
    private Button deleteCategoryBtn;

    @FXML
    private Text deleteCategoryText;

    @FXML
    private TextArea descriptionField;

    @FXML
    private Text descriptionText;

    @FXML
    private Button displayBtn;

    @FXML
    private ListView<Tache> displayList;

    @FXML
    private BorderPane displayPage;

    @FXML
    private Text dureeText;

    @FXML
    private TextField dureeTextField;

    @FXML
    private DatePicker enterDatePicker;

    @FXML
    private Text enterDateText;

    @FXML
    private ChoiceBox<Etat> etatBox;

    @FXML
    private Text etatText;

    @FXML
    private Button goBack1;

    @FXML
    private Button goBack2;

    @FXML
    private Button goBack21;

    @FXML
    private Button goBack22;

    @FXML
    private Button goBack23;

    @FXML
    private Button goBack24;

    @FXML
    private Button manualBtn;

    @FXML
    private BorderPane manualPage;

    @FXML
    private TextField nomProjetField;

    @FXML
    private Text nomProjetText;

    @FXML
    private Button nonBtn;

    @FXML
    private Button ouiBtn;

    @FXML
    private DatePicker periodDebutPicker;

    @FXML
    private DatePicker periodFinPicker;

    @FXML
    private Text periodeText;

    @FXML
    private Button planAutoBtn;

    @FXML
    private Button planManualBtn;

    @FXML
    private Text priorityText;

    @FXML
    private Button projectBtn;

    @FXML
    private BorderPane projectPage;

    @FXML
    private Text qstText;

    @FXML
    private Button refuseAutoBtn;

    @FXML
    private Button sauvegarderBtn;

    @FXML
    private Text tacheText;

    @FXML
    private Text titreCategory;

    @FXML
    private Text titreAffichage;

    @FXML
    private Text titreUpdate;

    @FXML
    private Text titreProjet;

    @FXML
    private Text titreAuto;

    @FXML
    private Text titreManual;

    @FXML
    private TextField tacheTextField;

    @FXML
    private Text periodiciteText;

    @FXML
    private TextField periodiciteTextField;

    @FXML
    private ChoiceBox<Tache> taskPlannedList;

    @FXML
    private ChoiceBox<Tache> tasktList;

    @FXML
    private ChoiceBox<Tache> tasktList1;

    @FXML
    private ChoiceBox<String> typeBox;

    @FXML
    private Text typeText;

    @FXML
    private ChoiceBox<String> bloqueBox;

    @FXML
    private Text bloqueText;

    @FXML
    private Text modifNomText;

    @FXML
    private Text modifDeadlineText;

    @FXML
    private Text modifPriorityText;

    @FXML
    private Text modifCategoryText;

    @FXML
    private Text explicationText;

    @FXML
    private Button modifConfirm;

    @FXML
    private Text autoText;

    @FXML
    private Text extendText;

    @FXML
    private Button updateBtn;

    @FXML
    private ImageView etatIcon;

    @FXML
    private ImageView modifIcon;

    @FXML
    private Button goToPlanningBtn;

    @FXML
    private Button modificationBtn;

    @FXML
    private Button evaluationBtn;

    @FXML
    private BorderPane updatePage;

    @FXML
    private ListView<Tache> viewListTask;

    @FXML
    private ListView<Tache> viewListTask1;

    @FXML
    private Button afficherBtn;

    private Utilisateur myCurrenUtilisateur;
    private Planify planify = Planify.getInstance();
    private LocalDate deadLine;
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private ArrayList<Tache> listToPlanAuto = new ArrayList<>();
    private ArrayList<Tache> listProject = new ArrayList<>();
    private Planning p = new Planning();
    private Planning p_ = new Planning();
    private LocalDate dateDisplay;
    LocalDate currentDate = LocalDate.now();

    @FXML
    void handleAfficher(ActionEvent event) {
        displayList.getItems().clear();
        ArrayList<Tache> unscList = myCurrenUtilisateur.getAllPlannedDay(dateDisplay);
        ObservableList<Tache> myUnscist = FXCollections.observableArrayList(unscList);
        displayList.setItems(myUnscist);
    }

    @FXML
    void handleConfirmAuto(ActionEvent event) {
        myCurrenUtilisateur.deletePlanning(p_);
        myCurrenUtilisateur.addPlanning(p);
        tasktList.getSelectionModel().clearSelection();
        viewListTask.getItems().clear();
        listToPlanAuto.clear();
        extendBox.getSelectionModel().clearSelection();
        planify.updateUser(myCurrenUtilisateur);
    }

    @FXML
    void handleRefuseAuto(ActionEvent event) {
        handleGoingBack(event);
        tasktList.getSelectionModel().clearSelection();
        viewListTask.getItems().clear();
        listToPlanAuto.clear();
        extendBox.getSelectionModel().clearSelection();

    }

    private void populateUnscheduled() {
        ArrayList<Tache> unscList = myCurrenUtilisateur.getTachesNotPlanned();
        ObservableList<Tache> myUnscist = FXCollections.observableArrayList(unscList);
        tasktList.setItems(myUnscist);
        tasktList1.setItems(myUnscist);
    }

    private void handleSelectedUnscheduled() {
        tasktList.setOnAction(e -> {
            Tache tache = tasktList.getSelectionModel().getSelectedItem();
            if (tache != null) {
                listToPlanAuto.add(tache);
                viewListTask.getItems().clear();
                viewListTask.getItems().addAll(listToPlanAuto);
            }
        });
    }

    private void handleSelectedUnscheduled2() {
        tasktList1.setOnAction(e -> {
            Tache tache = tasktList1.getSelectionModel().getSelectedItem();
            if (tache != null) {
                listProject.add(tache);
                viewListTask1.getItems().clear();
                viewListTask1.getItems().addAll(listProject);
            }
        });
    }

    @FXML
    void handlePeriodDebutPicker(ActionEvent event) {

    }

    @FXML
    void handlePeriodFinPicker(ActionEvent event) {

    }

    @FXML
    void handlePlanAuto(ActionEvent event) {
        LocalDate dateDebut = periodDebutPicker.getValue();
        LocalDate dateFin = periodFinPicker.getValue();
        if (dateDebut.isAfter(dateFin)) {
            Alerts.errorDate();
        } else {
            if (dateDebut.isBefore(currentDate)) {
                Alerts.errorDateCurrent();
            } else {
                PeriodMe period = new PeriodMe(dateDebut, dateFin);
                if (myCurrenUtilisateur.isPeriodAvailable2(period) == null) {
                    Alerts.errorPlanning();
                    handleGoingBack(event);
                    tasktList.getSelectionModel().clearSelection();
                    viewListTask.getItems().clear();
                    listToPlanAuto.clear();
                    extendBox.getSelectionModel().clearSelection();
                } else {// ici je planifie
                    boolean extend = false;
                    if (extendBox.getSelectionModel().getSelectedItem() == "Oui") {
                        extend = true;
                    } else {
                        extend = false;
                    }
                    p_ = myCurrenUtilisateur.isPeriodAvailable2(period);
                    p = myCurrenUtilisateur.planAuto(listToPlanAuto, myCurrenUtilisateur.isPeriodAvailable2(period),
                            extend);
                    Alerts.displayPlanning(p);
                    confirmAutoBtn.setVisible(true);
                    refuseAutoBtn.setVisible(true);
                    planAutoBtn.setVisible(false);
                }
            }
        }
    }

    @FXML
    void handlePlanManual(ActionEvent event) {
        Simple tacheSimple = new Simple();
        tacheSimple.setNom(tacheTextField.getText());
        tacheSimple.setDuree(convert(dureeTextField.getText()));
        tacheSimple.setDate_limite(deadlineDatePicker.getValue());
        tacheSimple.setCategorie(deleteCategoryBox2.getSelectionModel().getSelectedItem());
        tacheSimple.setPriorite(PriorityBox.getSelectionModel().getSelectedItem());
        if (dateManualPicker.getValue().isAfter(tacheSimple.getDate_limite())) {
            Alerts.errorDeadlineandDate();
        } else {
            if (creneauDebutField.getText().isEmpty()) {
                Alerts.emptyFields();
            } else {
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime h = LocalTime.parse(creneauDebutField.getText(), timeFormatter);
                LocalTime h1 = h.plusMinutes(tacheSimple.getDuree());
                boolean bloque = false;
                if (bloqueBox.getSelectionModel().getSelectedItem() == "Oui") {
                    bloque = true;
                }
                Creneau c = new Creneau(h, h1, myCurrenUtilisateur.getDureeMin(), bloque);
                if (myCurrenUtilisateur.planNewTaskSimple(tacheSimple, dateManualPicker.getValue(), c)) {
                    Alerts.successfulPlan();
                    System.out.println(myCurrenUtilisateur);
                } else {
                    Alerts.errorPlan();
                }
                planify.updateUser(myCurrenUtilisateur);
                handleGoingBack(event);
                clearFields();
            }
        }
        dateManualPicker.setValue(null);
        creneauDebutField.clear();
        bloqueBox.getSelectionModel().clearSelection();
    }

    private boolean getTache() {
        if (tacheTextField.getText().isEmpty() || dureeTextField.getText().isEmpty()
                || deadLine == null || PriorityBox.getSelectionModel().getSelectedItem() == null
                || deleteCategoryBox2.getSelectionModel().getSelectedItem() == null) {
            Alerts.emptyFields();
            return false;
        }
        return true;
    }

    @FXML
    void handleNon(ActionEvent event) {
        if (getTache()) {
            if (deadlineDatePicker.getValue().isBefore(LocalDate.now())) {
                Alerts.errorDeadline();
            } else {
                typeBox.setVisible(true);
                typeText.setVisible(true);
                sauvegarderBtn.setVisible(true);

                periodiciteText.setVisible(true);
                periodiciteTextField.setVisible(true);
            }
        }
    }

    @FXML
    void handleOui(ActionEvent event) {
        if (getTache()) {
            if (deadlineDatePicker.getValue().isBefore(LocalDate.now())) {
                Alerts.errorDeadline();
            } else {
                AddPage.setVisible(false);
                manualPage.setVisible(true);
                deleteCategoryBox.getSelectionModel().clearSelection();
                nonBtn.setDisable(true);
            }
        }
    }

    @FXML
    void handleSauvegarder(ActionEvent event) {
        String myType = typeBox.getSelectionModel().getSelectedItem();
        if (myType == "Tâche simple") {
            if (deadlineDatePicker.getValue().isBefore(LocalDate.now())) {
                Alerts.errorDeadline();
            } else {
                Simple tacheSimple = new Simple();
                tacheSimple.setNom(tacheTextField.getText());
                tacheSimple.setDuree(convert(dureeTextField.getText()));
                tacheSimple.setDate_limite(deadlineDatePicker.getValue());
                tacheSimple.setCategorie(deleteCategoryBox2.getSelectionModel().getSelectedItem());
                tacheSimple.setPriorite(PriorityBox.getSelectionModel().getSelectedItem());
                tacheSimple.setPeriodicite(Integer.parseInt(periodiciteTextField.getText()));
                // update the user
                myCurrenUtilisateur.addTaskToTachesNotPlanned(tacheSimple);
                planify.updateUser(myCurrenUtilisateur);
                clearFields();
                Alerts.sauvegarder();
                handleGoingBack(event);
            }
        } else {
            if (myType == "Tâche décomposable") {
                if (deadlineDatePicker.getValue().isBefore(LocalDate.now())) {
                    Alerts.errorDeadline();
                } else {
                    Decomposable tacheDecomposable = new Decomposable();
                    tacheDecomposable.setNom(tacheTextField.getText());
                    tacheDecomposable.setDuree(convert(dureeTextField.getText()));
                    tacheDecomposable.setDate_limite(deadlineDatePicker.getValue());
                    tacheDecomposable.setCategorie(deleteCategoryBox2.getSelectionModel().getSelectedItem());
                    tacheDecomposable.setPriorite(PriorityBox.getSelectionModel().getSelectedItem());
                    myCurrenUtilisateur.addTaskToTachesNotPlanned(tacheDecomposable);
                    planify.updateUser(myCurrenUtilisateur);
                    clearFields();
                    Alerts.sauvegarder();
                    handleGoingBack(event);
                }
            } else {
                Alerts.emptyFields();
            }
        }
    }

    @FXML
    void handleConfirmUpdate(ActionEvent event) {
        /* Pour évaluer l'état de réalisation d'une tâche */
        Etat etat = etatBox.getSelectionModel().getSelectedItem();
        Tache tache = taskPlannedList.getSelectionModel().getSelectedItem();
        Tache tacheSauv = tache; // pour garder l'ancienne version de la tâche (pour la recherche dans le map)
        if (tache == null) {
            Alerts.tacheEmpty();
        } else {
            if (etat == null)
                Alerts.etatEmpty();
            else {
                myCurrenUtilisateur.getTachePlanning(tacheSauv).modifEtat(tache, etat);

                if (etat == Etat.COMPLETED) { // on distribue les badges et les messages de félicitation
                    Map<LocalDate, Integer> tacheCompletedMap = myCurrenUtilisateur.getTachesCompleted();
                    if (tacheCompletedMap.get(currentDate) == null)
                        tacheCompletedMap.put(currentDate, 0);
                    tacheCompletedMap.put(currentDate, tacheCompletedMap.get(currentDate) + 1);
                    myCurrenUtilisateur.setTachesCompleted(tacheCompletedMap);
                    myCurrenUtilisateur.attribuerBadge(tacheCompletedMap.get(currentDate));
                }
                Alerts.setEtat();
                planify.updateUser(myCurrenUtilisateur);
                etatBox.getSelectionModel().clearSelection();
                handleGoingBack(event);
            }
        }

    }

    @FXML
    void handlemodifConfirm(ActionEvent event) {
        /* pour modifier une ou plusieurs informations liées à une tâche */
        String nom = modifNomTache.getText();
        LocalDate deadline = modifDeadline.getValue();
        String duree = modifDuree.getText();
        Priorite priorite = modifPriorityBox.getSelectionModel().getSelectedItem();
        Categorie categorie = modifCategoryBox.getSelectionModel().getSelectedItem();
        Tache tache = taskPlannedList.getSelectionModel().getSelectedItem();
        Tache tacheSauv = tache; // pour garder l'ancienne version de la tâche (pour la recherche dans le map)
        if (tache == null) {
            Alerts.tacheEmpty();
        } else {
            if (!nom.isEmpty()) {
                tache.setNom(nom);
            }
            if (deadline != null) {
                if (deadline.isBefore(currentDate)) {
                    Alerts.errorDateCurrent();
                } else {
                    tache.setDate_limite(deadline);
                }
            }
            if (!duree.isEmpty()) {
                tache.setDuree(convert(duree));
            }
            if (priorite != null) {
                tache.setPriorite(priorite);
            }
            if (categorie != null) {
                tache.setCategorie(categorie);
            }
            if (nom.isEmpty() && deadline == null && duree == null && priorite == null && categorie == null)
                Alerts.modifEmpty();
            else {
                Alerts.modifier();
                myCurrenUtilisateur.getTachePlanning(tacheSauv).modifTache(tacheSauv, tache);
            }
            planify.updateUser(myCurrenUtilisateur);
            handleGoingBack(event);
            clearFields2();
        }
    }

    @FXML
    void handleDeadline(ActionEvent event) {
        deadLine = deadlineDatePicker.getValue();
    }

    private Long convert(String durationString) {// convertir la durée en minutes
        String[] parts = durationString.split(":");
        Long hours = Long.parseLong(parts[0]);
        Long minutes = Long.parseLong(parts[1]);
        return hours * 60 + minutes;
    }

    private void clearFields() {
        tacheTextField.setText("");
        dureeTextField.setText("");
        periodiciteTextField.setText("");
        deadlineDatePicker.setValue(null);
        deleteCategoryBox2.getSelectionModel().clearSelection();
        PriorityBox.getSelectionModel().clearSelection();
        typeBox.getSelectionModel().clearSelection();
    }

    private void clearFields2() {
        modifNomTache.setText("");
        modifDuree.setText("");
        modifDeadline.setValue(null);
        modifPriorityBox.getSelectionModel().clearSelection();
        modifCategoryBox.getSelectionModel().clearSelection();
    }

    private void populatePriority() {
        ArrayList<Priorite> myPriorities = new ArrayList<>();
        myPriorities.add(Priorite.HIGH);
        myPriorities.add(Priorite.MEDIUM);
        myPriorities.add(Priorite.LOW);
        ObservableList<Priorite> myPrioritiesList = FXCollections.observableArrayList(myPriorities);
        PriorityBox.setItems(myPrioritiesList);
        modifPriorityBox.setItems(myPrioritiesList);
    }

    private void populateType() {
        ArrayList<String> myTypes = new ArrayList<>();
        myTypes.add("Tâche simple");
        myTypes.add("Tâche décomposable");
        ObservableList<String> myTypesList = FXCollections.observableArrayList(myTypes);
        typeBox.setItems(myTypesList);
    }

    private void populateEtat() {
        ArrayList<Etat> etatTache = new ArrayList<>();
        etatTache.add(Etat.COMPLETED);
        etatTache.add(Etat.CANCELLED);
        etatTache.add(Etat.INPROGRESS);
        etatTache.add(Etat.DELAYED);
        etatTache.add(Etat.NOTREALIZED);
        ObservableList<Etat> etatTacheList = FXCollections.observableArrayList(etatTache);
        etatBox.setItems(etatTacheList);
        ;
    }

    private void populatePlannedTache() {
        ArrayList<Tache> tachesList = myCurrenUtilisateur.plannedTaches();
        ObservableList<Tache> plannedTaches = FXCollections.observableArrayList(tachesList);
        taskPlannedList.setItems(plannedTaches);
    }

    @FXML
    void handleAddCategory(ActionEvent event) {
        String _name = categoryField.getText();
        Color _color = colorPicker.getValue();
        int red = (int) (_color.getRed() * 255);
        int green = (int) (_color.getGreen() * 255);
        int blue = (int) (_color.getBlue() * 255);
        int alpha = (int) (_color.getOpacity() * 255);
        java.awt.Color color_ = new java.awt.Color(red, green, blue, alpha);
        if (myCurrenUtilisateur.isColor(color_)) {
            Alerts.errorColor();
        } else {
            if (myCurrenUtilisateur.isNameCategory(_name) || _name.isEmpty()) {
                Alerts.errorCategory();
            } else {
                Categorie myCategorie = new Categorie(_name, color_);
                myCurrenUtilisateur.addNewCategory(myCategorie);
                planify.updateUser(myCurrenUtilisateur);
                Alerts.successfulCategory();
                categoryField.clear();
                colorPicker.setValue(Color.WHITE);
                deleteCategoryBox.getItems().add(myCategorie);
            }
        }
    }

    private void populateCategory() {
        ArrayList<Categorie> myCategories = myCurrenUtilisateur.getMyCategories();
        ObservableList<Categorie> myCategoriesList = FXCollections.observableArrayList(myCategories);
        deleteCategoryBox.setItems(myCategoriesList);
        deleteCategoryBox2.setItems(myCategoriesList);
        modifCategoryBox.setItems(myCategoriesList);
    }

    @FXML
    void handleDeleteCategory(ActionEvent event) {
        Categorie catTodelete = deleteCategoryBox.getSelectionModel().getSelectedItem();
        if (catTodelete != null) {
            myCurrenUtilisateur.deleteCategory(catTodelete);
            planify.updateUser(myCurrenUtilisateur);
            deleteCategoryBox.getSelectionModel().clearSelection();
            populateCategory();
            Alerts.successfulDeleteCategory();
        } else {
            Alerts.emptyFields();
        }

    }

    @FXML
    void handleAddProjet(ActionEvent event) {
        if (nomProjetField.getText().isEmpty() || descriptionField.getText().isEmpty() || listProject.isEmpty()) {
            Alerts.emptyFields();
        } else {
            Projet projet = new Projet(nomProjetField.getText(), descriptionField.getText(), listProject);
            myCurrenUtilisateur.addNewProject(projet);
            planify.updateUser(myCurrenUtilisateur);
            Alerts.top();
            listProject.clear();
            viewListTask1.getItems().clear();
            nomProjetField.setText("");
            descriptionField.setText("");
            handleGoingBack(event);
        }
    }

    @FXML
    void handleEnterDatePicker(ActionEvent event) {
        dateDisplay = enterDatePicker.getValue();
    }

    @FXML
    void handleNomProjet(ActionEvent event) {

    }

    @FXML
    void handletaskList(ActionEvent event) {

    }

    @FXML
    void handletaskPlannedList(ActionEvent event) {

    }

    public void setUser(Utilisateur user) {
        // to get the infos from the previous controller
        myCurrenUtilisateur = user;
    }

    private void populateBloque() {
        ArrayList<String> a = new ArrayList<>();
        a.add("Oui");
        a.add("Non");
        ObservableList<String> aList = FXCollections.observableArrayList(a);
        bloqueBox.setItems(aList);
        extendBox.setItems(aList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TaskMainPage.setVisible(true);
        AddPage.setVisible(false);
        manualPage.setVisible(false);
        autoPage.setVisible(false);
        projectPage.setVisible(false);
        updatePage.setVisible(false);
        displayPage.setVisible(false);
        categoryPage.setVisible(false);
        populatePriority();
        populateType();
        populateEtat();
        populateBloque();
    }

    private Stage stage;
    private Scene scene;

    public void changeSceneToPlannnig(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Planning.fxml"));
        Parent root = loader.load();
        ControlleurPlanning controlleurPlanning = loader.getController();
        controlleurPlanning.setUser(myCurrenUtilisateur);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleUpdate(ActionEvent event) {
        TaskMainPage.setVisible(false);
        AddPage.setVisible(false);
        manualPage.setVisible(false);
        autoPage.setVisible(false);
        projectPage.setVisible(false);
        updatePage.setVisible(true);
        displayPage.setVisible(false);
        categoryPage.setVisible(false);
        modificationBtn.setVisible(true);
        evaluationBtn.setVisible(true);
        modifIcon.setVisible(true);
        etatIcon.setVisible(true);
        explicationText.setVisible(false);
        modifNomText.setVisible(false);
        modifDeadlineText.setVisible(false);
        modifDureeText.setVisible(false);
        modifPriorityText.setVisible(false);
        modifCategoryText.setVisible(false);
        modifNomTache.setVisible(false);
        modifDeadline.setVisible(false);
        modifDuree.setVisible(false);
        modifPriorityBox.setVisible(false);
        modifCategoryBox.setVisible(false);
        modifConfirm.setVisible(false);
        taskPlannedList.setVisible(false);
        etatBox.setVisible(false);
        tacheText.setVisible(false);
        etatText.setVisible(false);
        taskPlannedList.setVisible(false);
        etatBox.setVisible(false);
        confirmUpdateBtn.setVisible(false);
    }

    @FXML
    void handleProject(ActionEvent event) {
        TaskMainPage.setVisible(false);
        AddPage.setVisible(false);
        manualPage.setVisible(false);
        autoPage.setVisible(false);
        projectPage.setVisible(true);
        updatePage.setVisible(false);
        displayPage.setVisible(false);
        categoryPage.setVisible(false);
        populateUnscheduled();
        handleSelectedUnscheduled2();
    }

    @FXML
    void handleGoingBack(ActionEvent event) {
        nonBtn.setDisable(false);
        ouiBtn.setDisable(false);

        TaskMainPage.setVisible(true);
        AddPage.setVisible(false);
        manualPage.setVisible(false);
        autoPage.setVisible(false);
        projectPage.setVisible(false);
        updatePage.setVisible(false);
        displayPage.setVisible(false);
        categoryPage.setVisible(false);
        deleteCategoryBox.getSelectionModel().clearSelection();
        tasktList.getSelectionModel().clearSelection();
        displayList.getSelectionModel().clearSelection();
        viewListTask.getItems().clear();
        viewListTask1.getItems().clear();
        listToPlanAuto.clear();
        extendBox.getSelectionModel().clearSelection();
    }

    @FXML
    void handleManual(ActionEvent event) {
        TaskMainPage.setVisible(false);
        AddPage.setVisible(true);
        populateCategory();

        manualPage.setVisible(false);
        autoPage.setVisible(false);
        projectPage.setVisible(false);
        updatePage.setVisible(false);
        displayPage.setVisible(false);
        categoryPage.setVisible(false);
        typeBox.setVisible(false);
        typeText.setVisible(false);
        sauvegarderBtn.setVisible(false);
        periodiciteText.setVisible(false);
        periodiciteTextField.setVisible(false);
    }

    @FXML
    void handleAuto(ActionEvent event) {
        TaskMainPage.setVisible(false);
        AddPage.setVisible(false);
        manualPage.setVisible(false);
        autoPage.setVisible(true);
        projectPage.setVisible(false);
        updatePage.setVisible(false);
        displayPage.setVisible(false);
        categoryPage.setVisible(false);
        confirmAutoBtn.setVisible(false);
        refuseAutoBtn.setVisible(false);
        populateUnscheduled();
        handleSelectedUnscheduled();
        planAutoBtn.setVisible(true);

    }

    @FXML
    void handleCategory(ActionEvent event) {
        TaskMainPage.setVisible(false);
        AddPage.setVisible(false);
        manualPage.setVisible(false);
        autoPage.setVisible(false);
        projectPage.setVisible(false);
        updatePage.setVisible(false);
        displayPage.setVisible(false);
        categoryPage.setVisible(true);
        populateCategory();
    }

    @FXML
    void handleDisplay(ActionEvent event) {
        TaskMainPage.setVisible(false);
        AddPage.setVisible(false);
        manualPage.setVisible(false);
        autoPage.setVisible(false);
        projectPage.setVisible(false);
        updatePage.setVisible(false);
        displayPage.setVisible(true);
        categoryPage.setVisible(false);
    }

    @FXML
    void handleEvaluationBtn(ActionEvent event) {
        populatePlannedTache();
        etatIcon.setVisible(false);
        modifIcon.setVisible(false);
        evaluationBtn.setVisible(false);
        modificationBtn.setVisible(false);
        taskPlannedList.setVisible(true);
        etatBox.setVisible(true);
        tacheText.setVisible(true);
        etatText.setVisible(true);
        etatBox.setVisible(true);
        confirmUpdateBtn.setVisible(true);
    }

    @FXML
    void handleModificationBtn(ActionEvent event) {
        populateCategory();
        populatePlannedTache();
        etatIcon.setVisible(false);
        taskPlannedList.setVisible(true);
        tacheText.setVisible(true);
        modifIcon.setVisible(false);
        evaluationBtn.setVisible(false);
        modificationBtn.setVisible(false);
        explicationText.setVisible(true);
        modifNomText.setVisible(true);
        modifDeadlineText.setVisible(true);
        modifDureeText.setVisible(true);
        modifPriorityText.setVisible(true);
        modifCategoryText.setVisible(true);
        modifNomTache.setVisible(true);
        modifDeadline.setVisible(true);
        modifDuree.setVisible(true);
        modifPriorityBox.setVisible(true);
        modifCategoryBox.setVisible(true);
        modifConfirm.setVisible(true);
    }
}
