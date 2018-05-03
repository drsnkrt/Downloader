package net.egemsoft.updater.ui;

import com.sun.javafx.application.PlatformImpl;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.egemsoft.updater.util.DownloadFiles;
import net.egemsoft.updater.util.InternetTestConnection;
import net.egemsoft.updater.util.MoveOperations;
import net.egemsoft.updater.util.StartStopOperations;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by drsnkrt on 18.07.2017.
 */
public class Controller implements Initializable {

    public Label lblStopTvDestek;
    public Label lblInternetTestConnection;
    public Label lblMoveOldFiles2backup;
    public Label lblDownloadFiles;
    public Label lblMoveNewFiles2Path;

    public ProgressBar pBarStopTvDestek;
    public ProgressBar pBarInternetTestConnection;
    public ProgressBar pBarMoveOldFiles2backup;
    public ProgressBar pBarDownloadFiles;
    public ProgressBar pBarMoveNewFiles2Path;

    public ImageView imgInternetTestConnection;
    public ImageView imgDownloadFiles;
    public ImageView imgMoveOldFiles2backup;
    public ImageView imgMoveNewFiles2Path;
    public ImageView imgStopTvDestek;
    public ImageView imageBackground;


    public AnchorPane anchorPane;

    public Image imgPass = new Image("/images/success.png");
    private Image imgFail = new Image("/images/error.png");
    private Image imgLoad = new Image("/images/loader.gif");
    private Image imgBack = new Image("/images/mavi.png");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                init();
                return null;
            }
        };

        worker.execute();
    }

    private void init() {

        StartStopOperations startStop = new StartStopOperations();
        MoveOperations move = new MoveOperations();
        InternetTestConnection connection = new InternetTestConnection();
        DownloadFiles files = new DownloadFiles();


        imageBackground.setImage(imgBack);
        imageBackground.setVisible(true);

        updateVisibleTrue(pBarStopTvDestek, imgStopTvDestek);

        updateResultTrue(pBarStopTvDestek, imgStopTvDestek);
        updateLabelSituation(lblStopTvDestek, true, "TVDESTEK UYGULAMASI KAPATILIYOR...");

        if (startStop.stopTvDestek()) {
            updateLabelSituation(lblStopTvDestek, true, "TVDESTEK UYGULAMASI KAPATILDI");
        } else {
            updateResultFalse(pBarStopTvDestek, imgStopTvDestek);
            forceStartTvDestek();
            forceClose();
        }

        updateVisibleTrue(pBarInternetTestConnection, imgInternetTestConnection);
        updateLabelSituation(lblInternetTestConnection, true, "INTERNET BAGLANTI TESTI YAPILIYOR...");

        if (connection.internetTest()) {
            updateResultTrue(pBarInternetTestConnection, imgInternetTestConnection);
            updateLabelSituation(lblInternetTestConnection, true, "INTERNET BAGLANTI TESTI BAŞARILI");
        } else {
            updateResultFalse(pBarInternetTestConnection, imgInternetTestConnection);
            sendMessageBox("Internet bağlantı hatası, Updater kapatılacak", "ERROR");
            forceStartTvDestek();
            forceClose();
        }

        updateVisibleTrue(pBarDownloadFiles, imgDownloadFiles);
        updateLabelSituation(lblDownloadFiles, true, "GUNCELLEME DOSYASI INDIRILIYOR...");


        if (files.downloadFile()) {
            updateResultTrue(pBarDownloadFiles, imgDownloadFiles);
            updateLabelSituation(lblDownloadFiles, true, "GUNCELLEME DOSYASI BAŞARIYLA INDIRILDI");
        } else {
            updateResultFalse(pBarDownloadFiles, imgDownloadFiles);
            sendMessageBox("Güncelleme indirilirken hata oluştu, Updater kapatılacak", "ERROR");
            forceStartTvDestek();
            forceClose();
        }

        updateVisibleTrue(pBarMoveOldFiles2backup, imgMoveOldFiles2backup);
        updateLabelSituation(lblMoveOldFiles2backup, true, "UYGULAMA DOSYASININ YEDEGI ALINIYOR...");

        if (move.moveOldFileToBackup()) {
            updateLabelSituation(lblMoveOldFiles2backup, true, "UYGULAMA DOSYASININ YEDEGI ALINDI");
            updateResultTrue(pBarMoveOldFiles2backup, imgMoveOldFiles2backup);
        } else {
            updateResultFalse(pBarMoveOldFiles2backup, imgMoveOldFiles2backup);
            sendMessageBox("Dosya kopyalarken hata oluştu, Updater kapatılacak", "ERROR");
            forceStartTvDestek();
            forceClose();
        }

        updateVisibleTrue(pBarMoveNewFiles2Path, imgMoveNewFiles2Path);
        updateLabelSituation(lblMoveNewFiles2Path, true, "YENI DOSYA UYGULAMA DIZININE TAŞINIYOR...");

        if (move.moveNewFileToPath()) {
            updateResultTrue(pBarMoveNewFiles2Path, imgMoveNewFiles2Path);
            updateLabelSituation(lblMoveNewFiles2Path, true, "YENI DOSYA UYGULAMA DIZININE TAŞINDI");
        } else {
            updateResultFalse(pBarMoveNewFiles2Path, imgMoveNewFiles2Path);
            sendMessageBox("Dosya kopyalarken hata oluştu, Updater kapatılacak", "ERROR");
        }
        sendMessageBox("Güncelleme işlemi başarılı TVDestek başlatılıyor", "INFORMATION");
        startStop.startTvDestek();
        forceClose();
    }


    private void updateResultTrue(ProgressBar bar, ImageView img) {
        PlatformImpl.runAndWait(new Runnable() {
            @Override
            public void run() {
                bar.setProgress(1);
                bar.setStyle("-fx-accent: #0a8031;");
                img.setImage(imgPass);
            }
        });
    }

    private void sendMessageBox(String content, String type) {
        PlatformImpl.runAndWait(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.valueOf(type));
                alert.setTitle("TVDestek Updater");
                alert.setHeaderText(null);
                alert.setContentText(content);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("images/icon.ico"));
                alert.showAndWait();
            }
        });
    }

    private void updateVisibleTrue(ProgressBar bar, ImageView img) {
        PlatformImpl.runAndWait(new Runnable() {
            @Override
            public void run() {
                bar.setVisible(true);
                img.setVisible(true);
                img.setImage(imgLoad);
            }
        });
    }

    private void updateResultFalse(ProgressBar bar, ImageView img) {
        PlatformImpl.runAndWait(new Runnable() {
            @Override
            public void run() {
                bar.setProgress(1);
                bar.setStyle("-fx-accent: #ff312f;");
                img.setImage(imgFail);
            }
        });

    }

    private void forceClose() {
        PlatformImpl.runAndWait(new Runnable() {
            @Override
            public void run() {

                MoveOperations move = new MoveOperations();
                move.deleteTempDirectory();
                System.exit(-3);
            }
        });

    }

    private void forceStartTvDestek() {
        PlatformImpl.runAndWait(new Runnable() {
            @Override
            public void run() {
                StartStopOperations operations = new StartStopOperations();
                operations.startTvDestek();
            }
        });

    }

    private void updateLabelSituation(Label name, boolean visible, String text) {
        PlatformImpl.runAndWait(new Runnable() {
            @Override
            public void run() {
                name.setVisible(visible);
                name.setText(text);
            }
        });

    }

}


