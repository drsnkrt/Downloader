package net.egemsoft.updater.util;

import net.egemsoft.updater.metodlar.TvDestekLogger;
import net.egemsoft.updater.ui.DefaultSettings;
import sun.misc.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Created by drsnkrt on 19.07.2017.
 */
public class InternetTestConnection {

    public boolean internetTest() {

        boolean isOk = false;

        try {

            System.out.println("Internet bağlantısı kontrol ediliyor.");

            URL url = new URL(DefaultSettings.INTERNET_CONN_TEST_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.connect();
            if (con.getResponseCode() == 200) {
                System.out.println(url.toString() + " adresine bağlanıldı Internet bağlantısı var");
                isOk = true;
            } else {
                System.out.println(url.toString() + " adresine BAĞLANILAMADI Internet bağlantısı YOK");
                System.out.println("Updater Kapatılacak");
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (UnknownHostException ue) {
            ue.getMessage();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return isOk;
        }

    }


}
