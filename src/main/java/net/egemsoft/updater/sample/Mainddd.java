package net.egemsoft.updater.sample;

import javafx.application.Application;
import javafx.stage.Stage;
import net.egemsoft.updater.metodlar.DownloadFilesOld;

public class Mainddd extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("net.egemsoft.updater.sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//        CheckInternetConnection connection = new CheckInternetConnection();
//        connection.execute();

        DownloadFilesOld files = new DownloadFilesOld();
        files.downloadFile();
        System.exit(-1);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
