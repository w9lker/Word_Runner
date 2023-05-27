package Word_Runner;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.lang.Math;
import java.io.IOException;

import static Word_Runner.logic.elapsedSeconds;


public class MainController {
    @FXML
    private Text text;
    @FXML
    private Button button;
    @FXML
    private Button pause;
    @FXML
    private Button resume;
    @FXML
    private Button backW;
    @FXML
    private Button NextW;
    @FXML
    private TextArea textInput;
    @FXML
    private ProgressBar progressbar;
    private Stage stage;
    private Scene mainscene;
    private static String textF = "Nothing";
    private static int currentIndex = 0;
    public static void main(String[] args){
    }

    public void setImagetoButton(){
        String url1 = "";
        ImageView imageView1 = new ImageView(new Image(url1));
    }

    public void changeText(ActionEvent e){
        button.setVisible(false);
        button.setDisable(true);
        pause.setDisable(false);
        pause.setVisible(true);
        WordRunner(currentIndex, textF, .01,0.3,1.5);
    }

    public void PausePressed(ActionEvent event){
        stopExecution = true;
        pause.setDisable(true);
        pause.setVisible(false);
        resume.setDisable(false);
        resume.setVisible(true);
    }

    public void ResumePressed(ActionEvent event){
        stopExecution = false;
        WordRunner(currentIndex,textF,.01,.3,1.5);
        resume.setVisible(false);
        resume.setDisable(true);
        pause.setVisible(true);
        pause.setDisable(false);
    }

    public void GoBack(ActionEvent event){
        stopExecution = true;
        currentIndex = Math.max(currentIndex - 10, 0);
        stopExecution = false;
        WordRunner(currentIndex, textF, .01, .3, 1.5);
    }

    public void GoNext(ActionEvent event){
        stopExecution = true;
        currentIndex = Math.min(currentIndex +10, textF.length());
        stopExecution = false;
        WordRunner(currentIndex, textF, .01, .3, 1.5);
    }

    //method here gets needed stage, scene and node and loads the main scene
    public void TextInputToMain(ActionEvent e) throws IOException {
        textF = textInput.getText();
        System.out.println(textF);
        FXMLLoader fxmlloader = new FXMLLoader(MainController.class.getResource("main.fxml"));
        Parent root = fxmlloader.load();
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        mainscene = new Scene(root);
        stage.setScene(mainscene);
        stage.show();

    }



    //implement the function Word_Runner from Logic Class here...
    //Here I resolved the issue with threading. The original function couldn't update UI properly cause of threading issue
    //The flag is volatile to ensure that changes made to this variable are visible across all threads
    private volatile boolean stopExecution = false;

    public void WordRunner(int startingIndex, String textF, double Acceleration, double OvrSpeed, double PunctuationMark) {
        String[] arrayText = TextProcessing.Splitter(textF);
        double[] speedmap = TextProcessing.SpeedMap(arrayText, Acceleration, PunctuationMark, OvrSpeed);
        double length = arrayText.length;

        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                double beginTime = System.currentTimeMillis();
                for (int i = startingIndex; i < length; i++) {
                    if (stopExecution) {
                        break;
                    }

                    double currentTime = System.currentTimeMillis();
                    if (elapsedSeconds(beginTime, currentTime) >= speedmap[i]) {
                        updateMessage(arrayText[i]);
                        progressbar.setProgress((i+1)/arrayText.length);
                        beginTime = System.currentTimeMillis();
                        currentIndex = i;
                    } else {
                        i -= 1;
                    }
                }
                return null;
            }
        };
        task.messageProperty().addListener((obs, oldMessage, newMessage) -> text.setText(newMessage));
        new Thread(task).start();
        task.messageProperty().addListener((obs, oldMessage, newMessage) -> progressbar.setProgress((currentIndex+1)/length));
        new Thread(task).start();
    }

}
