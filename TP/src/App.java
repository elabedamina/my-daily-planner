import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException  {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("interface/AuthentificationPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 450);
        primaryStage.setTitle("Planify");
        Image icon = new Image(getClass().getResourceAsStream("/interface/Calendrier logo 3.png"));
        primaryStage.getIcons().add(icon); 
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}