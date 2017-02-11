package GQ.FX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("start/StartFrame.fxml"));
        if (null == fxmlLoader.getLocation()){
            throw new Exception("StartFrame.fxml not found");
        }
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("GQ");
        primaryStage.setScene(new Scene(root, 427, 295));
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
