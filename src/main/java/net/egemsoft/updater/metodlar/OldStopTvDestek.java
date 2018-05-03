package net.egemsoft.updater.metodlar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by drsnkrt on 17.07.2017.
 */
public class OldStopTvDestek {


    public boolean execute() {

        boolean result = false;

        List<Integer> pIdList = new ArrayList<Integer>();

        //find process id
        try {

            Process p = Runtime.getRuntime().exec("cmd /C tasklist");
            BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));

            String line = "";
            while ((line = bf.readLine()) != null) {

                if (line.contains("Superonline Kiosk.exe")) {
                    System.out.println(line);
                    line = line.substring("Superonline Kiosk.exe".length());
                    int taskID = Integer.parseInt(line.trim().split(" ")[0]);
                    pIdList.add(taskID);
                    System.out.println("Kiosk PID: " + taskID);
                }
            }

        } catch (IOException e) {
            System.out.println("Kiosk durdurulurken bir hata oluştu!\n" + e.toString());
            result = false;
        }


        if (pIdList.size() > 0) {

            for (int i = 0; i < pIdList.size(); i++) {

                try {

                    Process p2 = Runtime.getRuntime().exec("cmd /C taskkill /f /pid " + pIdList.get(i));
                    System.out.println("Kiosk çalışması durduruldu.");
                    result = true;

                } catch (IOException e) {
                    System.out.println("Kiosk taskkill'de bir hata oluştu!\n" + e.toString());
                    result = false;
                }
            }
        } else {

            // kiosk haricinde program çalıştırılamasın
        }
//        result = true;
        return result;
    }


}
