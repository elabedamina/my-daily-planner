package controlleurs;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modals.Categorie;
import modals.Creneau;
import modals.Decomposable;
import modals.Planify;
import modals.Priorite;
import modals.Simple;
import modals.Utilisateur;
import javafx.scene.Node;


public class ControlleurTask implements Initializable{

    @FXML
    private BorderPane AddPage;

    @FXML
    private ChoiceBox<Priorite> PriorityBox;

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
    private Text deadlineText;

    @FXML
    private ChoiceBox<Categorie> deleteCategoryBox;

    @FXML
    private ChoiceBox<Categorie> deleteCategoryBox2;

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
    private ListView<?> displayList;

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
    private ChoiceBox<?> etatBox;

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
    private MenuButton taskPlannedList;

    @FXML
    private MenuButton tasktList;

    @FXML
    private MenuButton tasktList1;

    @FXML
    private ChoiceBox<String> typeBox;

    @FXML
    private Text typeText;

    @FXML
    private ChoiceBox<String> bloqueBox;

    @FXML
    private Text bloqueText;

    @FXML
    private Button updateBtn;

    @FXML
    private Button goToPlanningBtn;

    @FXML
    private BorderPane updatePage;

    @FXML
    private ListView<?> viewListTask;

    @FXML
    private ListView<?> viewListTask1;

    
    private Utilisateur myCurrenUtilisateur;
    private Planify planify = Planify.getInstance();
    private LocalDate deadLine;

    @FXML
    void handlePlanManual(ActionEvent event) {
        Simple tacheSimple = new Simple();
        tacheSimple.setNom(tacheTextField.getText());
        tacheSimple.setDuree(convert(dureeTextField.getText()));
        tacheSimple.setDate_limite(deadlineDatePicker.getValue());
        tacheSimple.setCategorie(deleteCategoryBox2.getSelectionModel().getSelectedItem());
        tacheSimple.setPriorite(PriorityBox.getSelectionModel().getSelectedItem());
        if (dateManualPicker.getValue().isAfter(tacheSimple.getDate_limite())){
            Alerts.errorDeadlineandDate();
        }
        else{
            if(creneauDebutField.getText().isEmpty()){
                Alerts.emptyFields();
            }
            else{
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime h = LocalTime.parse(creneauDebutField.getText(), timeFormatter);
                LocalTime h1 = h.plusMinutes(tacheSimple.getDuree());
                boolean bloque=false;
                if(bloqueBox.getSelectionModel().getSelectedItem() == "Oui"){
                    bloque=true;
                }
                Creneau c = new Creneau(h, h1, myCurrenUtilisateur.getDureeMin(),bloque);
                if(myCurrenUtilisateur.planNewTaskSimple(tacheSimple, dateManualPicker.getValue(), c)){
                    Alerts.successfulPlan();
                    System.out.println(myCurrenUtilisateur);
                }
                else{
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

    private boolean getTache(){
        if(tacheTextField.getText().isEmpty()|| dureeTextField.getText().isEmpty() 
        || deadLine == null || PriorityBox.getSelectionModel().getSelectedItem() == null
        || deleteCategoryBox2.getSelectionModel().getSelectedItem()== null){
            Alerts.emptyFields();
            return false;
        }
        return true;
    }

    @FXML
    void handleNon(ActionEvent event) {
        if (getTache()){
            if(deadlineDatePicker.getValue().isBefore(LocalDate.now())){
                Alerts.errorDeadline();
            }
            else{
                typeBox.setVisible(true);
                typeText.setVisible(true);
                sauvegarderBtn.setVisible(true);
                ouiBtn.setDisable(true);
                periodiciteText.setVisible(true);
                periodiciteTextField.setVisible(true);
            }
        }
    }

    @FXML
    void handleOui(ActionEvent event) {
        if(getTache()){
            if(deadlineDatePicker.getValue().isBefore(LocalDate.now())){
                Alerts.errorDeadline();
            }
            else{
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
       if( myType == "Tâche simple" ){
           if(deadlineDatePicker.getValue().isBefore(LocalDate.now())){
               Alerts.errorDeadline();
            }
            else{
                Simple tacheSimple = new Simple();
                tacheSimple.setNom(tacheTextField.getText());
                tacheSimple.setDuree(convert(dureeTextField.getText()));
                tacheSimple.setDate_limite(deadlineDatePicker.getValue());
                tacheSimple.setCategorie(deleteCategoryBox2.getSelectionModel().getSelectedItem());
                tacheSimple.setPriorite(PriorityBox.getSelectionModel().getSelectedItem());
                tacheSimple.setPeriodicite(Integer.parseInt(periodiciteTextField.getText()));
                //update the user
                myCurrenUtilisateur.addTaskToTachesNotPlanned(tacheSimple);
                planify.updateUser(myCurrenUtilisateur);
                clearFields();
                Alerts.sauvegarder();
                handleGoingBack(event);
            }
        }
        else{
            if( myType == "Tâche décomposable"){
                if(deadlineDatePicker.getValue().isBefore(LocalDate.now())){
                    Alerts.errorDeadline();
                }
                else{
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
            }
            else{
                Alerts.emptyFields();
            }
        }
    }

    @FXML
    void handleDeadline(ActionEvent event) {
        deadLine = deadlineDatePicker.getValue();
    }

    private Long convert (String durationString ){//convertir la durée en minutes
        String[] parts = durationString.split(":");
        Long hours = Long.parseLong(parts[0]);
        Long minutes = Long.parseLong(parts[1]);
        return hours * 60 + minutes;
    }

    private void clearFields(){
        tacheTextField.setText("");
        dureeTextField.setText("");
        periodiciteTextField.setText("");
        deadlineDatePicker.setValue(null);
        deleteCategoryBox2.getSelectionModel().clearSelection();
        PriorityBox.getSelectionModel().clearSelection();        
        typeBox.getSelectionModel().clearSelection();        
    }

    private void populatePriority(){
        ArrayList<Priorite> myPriorities = new ArrayList<>();
        myPriorities.add(Priorite.HIGH);
        myPriorities.add(Priorite.MEDIUM);
        myPriorities.add(Priorite.LOW);
        ObservableList<Priorite> myPrioritiesList = FXCollections.observableArrayList(myPriorities);
        PriorityBox.setItems(myPrioritiesList);
    }

    private void populateType(){
        ArrayList<String> myTypes = new ArrayList<>();
        myTypes.add("Tâche simple");
        myTypes.add("Tâche décomposable");
        ObservableList<String> myTypesList = FXCollections.observableArrayList(myTypes);
        typeBox.setItems(myTypesList);
    }

    @FXML
    void handleAddCategory(ActionEvent event) {
        String _name= categoryField.getText();
        Color _color = colorPicker.getValue();
        int red = (int) (_color.getRed() * 255);
        int green = (int) (_color.getGreen() * 255);
        int blue = (int) (_color.getBlue() * 255);
        int alpha = (int) (_color.getOpacity() * 255);
        java.awt.Color color_ = new java.awt.Color(red, green, blue, alpha);
        if (myCurrenUtilisateur.isColor(color_)){
            Alerts.errorColor();
        }
        else{
            if (myCurrenUtilisateur.isNameCategory(_name) || _name.isEmpty()){
                Alerts.errorCategory();
            }
            else{
                Categorie myCategorie= new Categorie(_name, color_);
                myCurrenUtilisateur.addNewCategory(myCategorie);
                planify.updateUser(myCurrenUtilisateur);
                Alerts.successfulCategory();
                categoryField.clear();
                colorPicker.setValue(Color.WHITE);
                deleteCategoryBox.getItems().add(myCategorie);                
            }
        }
    }

    private void populateCategory(){
        ArrayList<Categorie> myCategories = myCurrenUtilisateur.getMyCategories();
        ObservableList<Categorie> myCategoriesList = FXCollections.observableArrayList(myCategories);
        deleteCategoryBox.setItems(myCategoriesList);
        deleteCategoryBox2.setItems(myCategoriesList);
    }
    
    @FXML
    void handleDeleteCategory(ActionEvent event) {
        Categorie catTodelete = deleteCategoryBox.getSelectionModel().getSelectedItem();  
        if(catTodelete!=null){
            myCurrenUtilisateur.deleteCategory(catTodelete);
            planify.updateUser(myCurrenUtilisateur);
            deleteCategoryBox.getSelectionModel().clearSelection();
            populateCategory();
            Alerts.successfulDeleteCategory();
        }
        else{
            Alerts.emptyFields();
        }
        
    }

    @FXML
    void handleAddProjet(ActionEvent event) {

    }

    @FXML
    void handleConfirmAuto(ActionEvent event) {

    }

    @FXML
    void handleConfirmUpdate(ActionEvent event) {

    }

    @FXML
    void handleEnterDatePicker(ActionEvent event) {

    }


    @FXML
    void handleNomProjet(ActionEvent event) {

    }

    @FXML
    void handlePeriodDebutPicker(ActionEvent event) {

    }

    @FXML
    void handlePeriodFinPicker(ActionEvent event) {

    }
  
    @FXML
    void handleRefuseAuto(ActionEvent event) {

    }

    @FXML
    void handletaskList(ActionEvent event) {

    }
    
    @FXML
    void handletaskPlannedList(ActionEvent event) {

    }

    public void setUser(Utilisateur user) {
        //to get the infos from the previous controller
        myCurrenUtilisateur = user;
    }

    private void populateBloque(){
        ArrayList<String> a = new ArrayList<>();
        a.add("Oui");
        a.add("Non");
        ObservableList<String> aList = FXCollections.observableArrayList(a);
        bloqueBox.setItems(aList);
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
    }


    @FXML
    void handleGoingBack(ActionEvent event) {
        TaskMainPage.setVisible(true);
        AddPage.setVisible(false);
        manualPage.setVisible(false);
        autoPage.setVisible(false);
        projectPage.setVisible(false);
        updatePage.setVisible(false);
        displayPage.setVisible(false);
        categoryPage.setVisible(false);
        deleteCategoryBox.getSelectionModel().clearSelection();
    }

    @FXML
    void handleManual(ActionEvent event) {
        TaskMainPage.setVisible(false);
        AddPage.setVisible(true);
        populateCategory();
        nonBtn.setDisable(false);
        ouiBtn.setDisable(false);
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
    void handlePlanAuto(ActionEvent event) {
        confirmAutoBtn.setVisible(true);
        refuseAutoBtn.setVisible(true);
        planAutoBtn.setVisible(false);
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
}
