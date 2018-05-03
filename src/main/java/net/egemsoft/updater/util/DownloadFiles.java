package net.egemsoft.updater.util;

import net.egemsoft.updater.ui.DefaultSettings;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by drsnkrt on 19.07.2017.
 */
public class DownloadFiles {


    MoveOperations operations = new MoveOperations();

    public boolean downloadFile() {

        operations.createPath(DefaultSettings.TEMP_FILE_PATH);

        boolean isOk = false;
        try {

            URL website = new URL(DefaultSettings.JAR_FILE_LINK);
            ReadableByteChannel rbc;
            rbc = Channels.newChannel(website.openStream());

            FileOutputStream fos = new FileOutputStream(DefaultSettings.DOWNED_JAR_FILE_PATH);
            System.out.println("Dosya İndiriliyor...");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            System.out.println("Dosya İndirildi.");
            fos.close();
            rbc.close();
            isOk = true;
            System.out.println("\" " + website.toString() + " \" " + " \n adresinden güncelleme dosyası indirildi");
        } catch (IOException e) {
            System.out.println("GÜNCELLEME DOSYASI İNDİRİLEMEDİ");
            isOk = false;
            e.printStackTrace();
        } finally {
            return isOk;
        }
    }

}
