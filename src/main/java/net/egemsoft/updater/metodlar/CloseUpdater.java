package net.egemsoft.updater.metodlar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by drsnkrt on 18.07.2017.
 */
public class CloseUpdater {

    public boolean execute() {

        boolean result = false;

        List<Integer> pIdList = new ArrayList<Integer>();

        try {

            Process p = Runtime.getRuntime().exec("cmd /C tasklist");
            BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));

            String line = "";
            while ((line = bf.readLine()) != null) {

                if (line.contains("notepad.exe")) {
                    System.out.println(line);
                    line = line.substring("notepad.exe".length());
                    int taskID = Integer.parseInt(line.trim().split(" ")[0]);
                    pIdList.add(taskID);
                    System.out.println("Notepad PID: " + taskID);
                }
            }

        } catch (IOException e) {
            System.out.println("TVDestek durdurulurken bir hata oluştu!\n" + e.toString());
            result = false;
        }


        if (pIdList.size() > 0) {

            for (int i = 0; i < pIdList.size(); i++) {

                try {

                    Process p2 = Runtime.getRuntime().exec("cmd /C taskkill /f /pid " + pIdList.get(i));
                    System.out.println("TVDestek çalışması durduruldu.");
                    result = true;

                } catch (IOException e) {
                    System.out.println("TVDestek taskkill'de bir hata oluştu!\n" + e.toString());
                    result = false;
                }
            }
        } else {

        }
//        result = true;
        return result;
    }
}
