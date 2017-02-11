package GQ.FX.start;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartFrameController {
    private Stage stage;

    @FXML
    private Pane startFrame;

    @FXML
    private Button login;

    @FXML
    private Label response;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField username;


    @FXML
    public void loginAction(ActionEvent e){
        response.setText(passwordField.getText());
        startFrame.setDisable(true);
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
}
