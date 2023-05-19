package controlleurs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modals.Utilisateur;
import javafx.scene.Node;


public class ControlleurTask implements Initializable{

    @FXML
    private BorderPane AddPage;

    @FXML
    private MenuButton PriorityBox;

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
    private MenuButton categoryBox;

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
    private TextField creneauFinField;

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
    private ChoiceBox<?> deleteCategoryBox;

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
    private MenuButton taskPlannedList;

    @FXML
    private MenuButton tasktList;

    @FXML
    private MenuButton tasktList1;

    @FXML
    private ChoiceBox<?> typeBox;

    @FXML
    private Text typeText;

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

    @FXML
    void handleAddCategory(ActionEvent event) {

    }

    @FXML
    void handleAddProjet(ActionEvent event) {

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
    }

    @FXML
    void handleColorPicker(ActionEvent event) {

    }

    @FXML
    void handleConfirmAuto(ActionEvent event) {

    }

    @FXML
    void handleConfirmUpdate(ActionEvent event) {

    }

    @FXML
    void handleDeadline(ActionEvent event) {

    }

    @FXML
    void handleDeleteCategory(ActionEvent event) {

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
    void handleEnterDatePicker(ActionEvent event) {

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
    }

    @FXML
    void handleManual(ActionEvent event) {
        TaskMainPage.setVisible(false);
        AddPage.setVisible(true);
        manualPage.setVisible(false);
        autoPage.setVisible(false);
        projectPage.setVisible(false);
        updatePage.setVisible(false);
        displayPage.setVisible(false);
        categoryPage.setVisible(false);
        typeBox.setVisible(false);
        typeText.setVisible(false);
        sauvegarderBtn.setVisible(false);
    }

    @FXML
    void handleNomProjet(ActionEvent event) {

    }

    @FXML
    void handleNon(ActionEvent event) {
        typeBox.setVisible(true);
        typeText.setVisible(true);
        sauvegarderBtn.setVisible(true);
    }

    @FXML
    void handleOui(ActionEvent event) {
        AddPage.setVisible(false);
        manualPage.setVisible(true);
    }

    @FXML
    void handlePeriodDebutPicker(ActionEvent event) {

    }

    @FXML
    void handlePeriodFinPicker(ActionEvent event) {

    }

    @FXML
    void handlePlanAuto(ActionEvent event) {
        confirmAutoBtn.setVisible(true);
        refuseAutoBtn.setVisible(true);
        planAutoBtn.setVisible(false);
    }

    @FXML
    void handlePlanManual(ActionEvent event) {

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
    void handleRefuseAuto(ActionEvent event) {

    }

    @FXML
    void handleSauvegarder(ActionEvent event) {

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
    void handletaskList(ActionEvent event) {

    }

    @FXML
    void handletaskPlannedList(ActionEvent event) {

    }

    public void setUser(Utilisateur user) {
        //to get the infos from the previous controller
        myCurrenUtilisateur = user;
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

}
