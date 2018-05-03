package net.egemsoft.updater;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.egemsoft.updater.ui.DefaultSettings;

/**
 * Created by drsnkrt on 18.07.2017.
 */

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(DefaultSettings.TVDESTEK_FXML_PAGE));
        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setOpacity(0.9);
        primaryStage.show();

    }
}
