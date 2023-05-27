package Word_Runner;

import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class UI extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(UI.class.getResource("textInput.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(UI.class.getResourceAsStream("Icon.png")));
        stage.setTitle("Word Runner");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        //setting the buttons to images


    }

    public static void main(String[] args) {
        launch();
    }
}