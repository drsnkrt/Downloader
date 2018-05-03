package net.egemsoft.updater.metodlar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Created by drsnkrt on 17.07.2017.
 */
public class CheckInternetConnection {

    private static final String newLine = System.getProperty("line.separator");
    TvDestekLogger logger = new TvDestekLogger();


    public boolean execute() {

        boolean isOk = false;

        try {

            logger.info("Internet bağlantısı kontrol ediliyor.");

            URL url = new URL("http://www.net.egemsoft.net/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.connect();
            if (con.getResponseCode() == 200) {
                System.out.println("Internet var");
                isOk = true;
            } else {
                logger.error("Internet bağlantısında hata var!");
            }
        } catch (MalformedURLException er) {
            er.getMessage();
        } catch (UnknownHostException ue) {
            ue.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("gbxfghxfghfxgh");
            return isOk;
        }
    }

    private void sendGet() throws Exception {

        String url = "http://www.net.egemsoft.net/";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);


        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } else {
            System.out.println("sadasdsadsadsadsadsadasdsadasdsadsadsad");
        }

        //print result
//        System.out.println(response.toString());

    }

    private static String readStream(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine + newLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
