package net.egemsoft.updater.metodlar;

import java.io.*;

/**
 * Created by drsnkrt on 18.07.2017.
 */
public class MoveFiles {

    public static final String fileName = "TvDestek.rar";

    public static final File sourceFilePath = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\updater\\newFwFile");
    public static final File targetFilePath = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\updater\\oldFwFile");

    public void createFwPaths() {


        if (!sourceFilePath.exists()) {
            sourceFilePath.mkdir();
            System.out.println("Kaynak klasör oluşturuldu");
        } else {
            System.out.println("Kaynak klasör var");
        }
        if (!targetFilePath.exists()) {
            targetFilePath.mkdir();
            System.out.println("Hedef klasör oluşturuldu");
        } else {
            System.out.println("Hedef klasör var");
        }
    }

    public void deleteCurrentFw() {

        final File dosya = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\updater\\newFwFile\\TvDestek.rar");

        if (dosya.exists()) {
            dosya.delete();
            System.out.println(dosya.getName() + " adlı dosya başarılı bir şekilde silinmiştir.");
        } else {
            System.out.println("Dosya bulunamadığından silinemedi");
        }

    }

    public void copyFile() {

        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(sourceFilePath + "\\" + fileName);
            out = new FileOutputStream(targetFilePath + "\\" + fileName);

            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            System.out.println("Dosya Kopyalandı");
            System.out.println("Eski FW Dosyası Silinecek");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void moveOldFiles2backup() {


        if (!sourceFilePath.exists() || !targetFilePath.exists()) {
            createFwPaths();
        }
        copyFile();
        deleteCurrentFw();


    }
}
