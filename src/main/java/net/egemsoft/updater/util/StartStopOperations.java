package net.egemsoft.updater.util;

import java.io.IOException;

/**
 * Created by drsnkrt on 18.07.2017.
 */
public class StartStopOperations {

    public boolean stopTvDestek() {

        boolean isOk = false;
        try {
            //Programı kapatır path tanımlarken ' kullanmak gerekir
            Process p1 = Runtime.getRuntime().exec("powershell.exe Stop-Process -processname Superonline*");
            isOk= true;
        } catch (IOException e) {
            System.out.println(" stopTvDestek() metodunda try catch hatası " + e.getMessage());
            isOk= false;
        }
        return isOk;
    }

    public void startTvDestek() {

        try {
            //Programı başlatır path tanımlarken ' kullanmak gerekir
            Process p = Runtime.getRuntime().exec("powershell.exe Start-Process \'C:\\Program Files (x86)\\Superonline\\Superonline Kiosk\\Superonline Kiosk.exe\' -verb RunAs");
        } catch (IOException e) {
            System.out.println(" startTvDestek() metodunda try catch hatası " + e.getMessage());
        }
    }

    public void stopTvDestekUpdater() {

        try {
            Process p1 = Runtime.getRuntime().exec("powershell.exe Stop-Process -processname winRaR*");
        } catch (IOException e) {
            System.out.println(" startTvDestek() metodunda try catch hatası " + e.getMessage());
        }
    }

}
