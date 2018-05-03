package net.egemsoft.updater.util;

import net.egemsoft.updater.ui.DefaultSettings;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by drsnkrt on 19.07.2017.
 */
public class MoveOperations {

    public static String fileName = "";
    public static String sourcePath = DefaultSettings.TVDESTEK_MAIN_SOURCE_PATH;

    public boolean moveOldFileToBackup() {

        boolean isOk = false;

        getJarFileName();
        createPath(DefaultSettings.OLDVERSION_CREATED_PATH);
        String destPath = DefaultSettings.OLDVER_DEST_PATH;
        try {

            Path source = Paths.get(sourcePath);
            Path destination = Paths.get(destPath);
            createPath(DefaultSettings.OLDVER_CREATED_PATH);
            if (Files.exists(source) || Files.exists(destination)) {

                Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                System.out.println(source.getFileName() + " dosyası " + destination.toString().split("oldversions")[0] + fileName + " klasörüne KOPYALANDI");
                isOk = true;
            } else {
                System.out.println("Kaynak ya da hedef dosya bulunamadı ");
                isOk = false;
            }
        } catch (IOException e) {
            isOk = false;
            e.printStackTrace();
        }
        return isOk;
    }

    public String getJarFileName() {


        try {
            URL uri = null;

            uri = new URL(DefaultSettings.UPDATER_TXT_URI);

            BufferedReader in = new BufferedReader(new InputStreamReader(uri.openStream()));

            String line;
            while ((line = in.readLine()) != null)
                if (line.contains("preVersion")) {
                    fileName = line.split("preVersion=")[1].replace(".", "");
                    fileName.replace(".", "");
                    System.out.println("Yedeklenecek eski versiyon " + fileName);
                }
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return fileName;
        }

    }

    public boolean deleteFwfile(String path) {

        boolean isOk = false;

        File file = new File(path);

        if (file.delete()) {
            isOk = true;
            System.out.println(file.getName() + " dosyası silindi!");
        } else {
            System.out.println("Dosya silme işlemi başarısız");
            isOk = false;
        }
        return isOk;
    }

    public void deleteTempDirectory() {

        File directory = new File(System.getProperty("user.home") + "\\.superonline\\tvdestek\\temp\\");
        try {
            FileUtils.deleteDirectory(directory);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean moveNewFileToPath() {

        boolean isOk = false;
        try {

            Path source = Paths.get(DefaultSettings.NEW_VERSION_SOURCE_PATH);
            Path destination = Paths.get(DefaultSettings.NEW_VERSION_DEST_PATH);
            if (Files.exists(source) || Files.exists(destination)) {
                Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                System.out.println(source.getFileName() + " dosyası " + destination.toString().split("kiosk-client")[0] + " klasörüne KOPYALANDI");
                isOk = true;
            } else {
                System.out.println("Kaynak ya da hedef dosya bulunamadı");
                isOk = false;
            }
        } catch (IOException e) {
            isOk = false;
            e.printStackTrace();
        }
        return isOk;
    }

    public void createPath(String path) {

        File directory = new File("C:\\users\\" + System.getProperty("user.name") + path);
        if (!directory.exists()) {
            directory.mkdir();
        }

    }
}
