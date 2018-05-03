package net.egemsoft.updater.sample;

import com.sun.javafx.application.PlatformImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;


public class Controller implements Initializable {

    public AnchorPane mainPane;

    public Button btnPrint;
    public TextField txtInput;
    public Label lblGoster;
    public Label lblCopySonuc;
    public ProgressBar pBar;
    public ProgressIndicator killerIndi;

    public ImageView imgResult;

    public Image imgPass = new Image("images/success.png");
    private Image imgFail = new Image("images/error.png");
    private Image imgLoad = new Image("images/loading.gif");


    //Onload Metodu
    public void initialize(URL location, ResourceBundle resources) {


        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
//                init();
//                OldStopTvDestek destek = new OldStopTvDestek();
//                destek.execute();
//                CheckInternetConnection connection = new CheckInternetConnection();
//                connection.execute();
//                System.exit(-1);
                return null;
            }
        };

        worker.execute();


    }

    public void btnPrintOnClick(ActionEvent e) {

        lblGoster.setVisible(true);
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                init();
                return null;
            }
        };

        worker.execute();
//        btnPrint.setDisable(true);
    }

    private void init() {
        lblCopySonuc.setVisible(false);
        imgResult.setImage(imgLoad);
        imgResult.setVisible(true);
        for (double i = 0; i <= 100; i++) {
            pBar.setProgress(i / 100);
            PlatformImpl.runAndWait(new Runnable() {
                @Override
                public void run() {
                    lblGoster.textProperty().bind(new SimpleStringProperty("%" + (int) (pBar.getProgress() * 100)));

                }
            });
            try {
                sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lblGoster.setVisible(false);
        imgResult.setImage(imgPass);
        imgResult.setVisible(true);
        lblCopySonuc.setVisible(true);

    }

}