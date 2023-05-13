import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class App extends Application {
    @Override
    
    public void start(Stage stage) throws IOException {
            // Set the icon to your custom icon
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("interface/Preference.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 450);
        stage.setTitle("Planify");
        Image icon = new Image(getClass().getResourceAsStream("/interface/Calendrier logo 3.png"));
        stage.getIcons().add(icon); 
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
