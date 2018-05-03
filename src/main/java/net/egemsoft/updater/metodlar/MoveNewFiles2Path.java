package net.egemsoft.updater.metodlar;

import java.io.*;

/**
 * Created by drsnkrt on 18.07.2017.
 */
public class MoveNewFiles2Path {


    public static final String fileName = "TvDestek.rar";
    public static final File sourceFilePath = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\updater");
    public static final File targetFilePath = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\updater\\newFwFile");


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

}
