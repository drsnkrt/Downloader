package net.egemsoft.updater.metodlar;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by EgemSoft on 22.06.2017.
 */
public class TvDestekLogger {

    SimpleDateFormat yedekDosyaAdiBicim = new SimpleDateFormat("ddMyyyy-hhmmss");
    Date yedekDosyatarihSaat = new Date();


    public String sabitKontrol;
    public final int logSabiti = 4;
    public final String yedekDosyaAdi = yedekDosyaAdiBicim.format(yedekDosyatarihSaat) + ".txt";
    public static final File yedekDosya = new File("C:\\Users\\" + System.getProperty("user.name") + "\\.superonline\\kiosk\\yedekLog");
    public static final File dosya = new File("C:\\Users\\" + System.getProperty("user.name") + "\\.superonline\\kiosk\\kiosk.log");
    /* Yazılar için renkler tanımlandı */
    public static final String kirmizi = "\u001B[31m";
    public static final String beyaz = "\u001B[37m";
    /* Yazı Çeşitleri*/
    public static final String kalin = "\033[0;1m";
    double dosyaBoyutu = dosya.length();
    double dosyaBoyutuKB = dosyaBoyutu / 1024;
    double dosyaBoyutuMB = dosyaBoyutuKB / 1024;

    public void logDosyasiOlustur() {

        try {

            if (dosya.exists()) {
                System.out.println("Dosya Zaten var");
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                System.out.println("Dosya " + decimalFormat.format(dosyaBoyutuMB) + " Mbdir ");

            } else {
                dosya.createNewFile(); // Dosya oluşturuluyor
            }

            yedekDosyaOlustur();
        } catch (IOException ex) {
            System.out.println("Dosya Yazdırılırken hata oluştu");
        }
    }

    public void yedekDosyaOlustur() {

        if (!yedekDosya.exists()) {
            yedekDosya.mkdir();
        }
    }

    public void logDosyasiKopyala() {

        FileInputStream in = null;
        FileOutputStream out = null;


        try {
            in = new FileInputStream("C:\\Users\\" + System.getProperty("user.name") + "\\.superonline\\kiosk\\kiosk.log");
            out = new FileOutputStream("C:\\Users\\" + System.getProperty("user.name") + "\\.superonline\\kiosk\\yedekLog\\" + yedekDosyaAdi);

            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            System.out.println("Dosya Kopyalandı");

            dosyaSil();

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

    public void dosyaSil() {

        if (dosya.exists()) {
            dosya.delete();
            System.out.println(dosya.getName() + " adlı dosya başarılı bir şekilde silinmiştir.");
        } else {
            System.out.println("Dosya bulunamadığından silinemedi");
        }

    }


    public TvDestekLogger() {
        logDosyasiOlustur();
        yedekDosyaOlustur();
        if (dosyaBoyutuMB > 100) {
            logDosyasiKopyala();
        }

        sabitKontrol = "1info";
    }


    private void debugYazdir(String msg, int sabit, String metodAdi) {

        try {

            PrintWriter txtYazici = new PrintWriter(new BufferedWriter(new FileWriter(dosya, true)));
            txtYazici.println(logBicim(sabit, metodAdi) + " - " + msg);

            if (!isNotSuppressed("2debug")) return;
            System.out.println(beyaz + logBicim(sabit, metodAdi) + " - " + msg);
            txtYazici.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void errorYazdir(String msg, int sabit, String metodAdi) {
        try {
            PrintWriter txtYazici = new PrintWriter(new BufferedWriter(new FileWriter(dosya, true)));
            txtYazici.println(logBicim(sabit, metodAdi) + " - " + msg);

            if (!isNotSuppressed("4error")) return;
            System.out.println(kirmizi + kalin + logBicim(sabit, metodAdi) + " - " + msg);

            txtYazici.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void warningYazdir(String msg, int sabit, String metodAdi) {

        try {
            PrintWriter txtYazici = new PrintWriter(new BufferedWriter(new FileWriter(dosya, true)));

            if (!isNotSuppressed("3warning")) return;
            System.out.println(beyaz + logBicim(sabit, metodAdi) + " - " + msg);
            txtYazici.println(logBicim(sabit, metodAdi) + " - " + msg);

            txtYazici.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void infoYazdir(String msg, int sabit, String metodAdi) {

        try {
            PrintWriter txtYazici = new PrintWriter(new BufferedWriter(new FileWriter(dosya, true)));

            if (!isNotSuppressed("1info")) return;
            System.out.println(beyaz + logBicim(sabit, metodAdi) + " - " + msg);
            txtYazici.println(logBicim(sabit, metodAdi) + " - " + msg);

            txtYazici.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void debug(String msg) {
        String metodAdi = Thread.currentThread().getStackTrace()[1].getMethodName();
        String up = metodAdi.toUpperCase();
        debugYazdir(msg, logSabiti, up);
    }

    public void error(String msg) {
        String metodAdi = Thread.currentThread().getStackTrace()[1].getMethodName();
        String up = metodAdi.toUpperCase();
        errorYazdir(msg, logSabiti, up);
    }

    public void warn(String msg) {
        String metodAdi = Thread.currentThread().getStackTrace()[1].getMethodName();
        String up = metodAdi.toUpperCase();
        warningYazdir(msg, logSabiti, up);
    }

    public void info(String msg) {
        String metodAdi = Thread.currentThread().getStackTrace()[1].getMethodName();
        String up = metodAdi.toUpperCase();
        infoYazdir(msg, logSabiti, up);
    }


    public String logBicim(int sabit, String metodAdi) {

        SimpleDateFormat bicim = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date tarihSaat = new Date();

        String tamClassAdi = Thread.currentThread().getStackTrace()[sabit].getClassName();
        String classAdi = tamClassAdi.substring(tamClassAdi.lastIndexOf(".") + 1);
//        String metodAdi = Thread.currentThread().getStackTrace()[sabit].getMethodName();
        String tarihveSaat = bicim.format(tarihSaat);
        int satirNo = Thread.currentThread().getStackTrace()[sabit].getLineNumber();

        String logbicim = tarihveSaat + " " + metodAdi + " " + classAdi + ":" + satirNo;
        return logbicim;
    }

    public boolean isNotSuppressed(String sabit) {
        if (sabit.compareTo(sabitKontrol) >= 0) {
            return true;
        } else {
            return false;
        }
    }
}


