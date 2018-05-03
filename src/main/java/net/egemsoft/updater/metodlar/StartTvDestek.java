package net.egemsoft.updater.metodlar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by drsnkrt on 18.07.2017.
 */
public class StartTvDestek {

    public void basicStart() {

        try {

            //Programı başlatır
            Process p = Runtime.getRuntime().exec("powershell.exe Start-Process \'C:\\Program Files (x86)\\Superonline\\Superonline Kiosk\\Superonline Kiosk.exe\' -verb RunAs");
            //Programı kapatır
//            Process p1 = Runtime.getRuntime().exec("powershell.exe Stop-Process -processname Superonline*");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startTvDestek() {
        try {
            String command = "powershell.exe Start-Process \'C:\\Program Files (x86)\\Superonline\\Superonline Kiosk\\Superonline Kiosk.exe\' -verb RunAs";
            Process powerShellProcess = null;

            powerShellProcess = Runtime.getRuntime().exec(command);

            powerShellProcess.getOutputStream().close();
            String line;
            System.out.println("Standard Output:");
            BufferedReader stdout = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getInputStream()));
            while ((line = stdout.readLine()) != null) {
                System.out.println(line);
            }
            stdout.close();
            System.out.println("Standard Error:");
            BufferedReader stderr = new BufferedReader(new InputStreamReader(
                    powerShellProcess.getErrorStream()));
            while ((line = stderr.readLine()) != null) {
                System.out.println(line);
            }
            stderr.close();
            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
