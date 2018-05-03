package net.egemsoft.updater.ui;

import static net.egemsoft.updater.util.MoveOperations.fileName;

/**
 * Created by drsnkrt on 20.07.2017.
 */
public class DefaultSettings {

    public static final String INTERNET_CONN_TEST_URL = "http://www.egemsoft.net/";
    public static final String TVDESTEK_FXML_PAGE = "/layouts/TvDestekUpdater.fxml";
    public static final String TEMP_FILE_PATH = "\\.superonline\\tvdestek\\temp\\";
    public static final String JAR_FILE_LINK = "http://egemsoft.net/superonlinekiosk/kiosk-client-ui-sol-1.0.0-SNAPSHOT-executable-win32_x86.jar";
    public static final String DOWNED_JAR_FILE_PATH = System.getProperty("user.home") + "\\.superonline\\tvdestek\\temp\\kiosk-client-ui-sol-1.0.0-SNAPSHOT-executable-win32_x86.jar";
    public static final String OLDVERSION_CREATED_PATH = "\\.superonline\\tvdestek\\oldversions\\";
    public static final String TVDESTEK_MAIN_SOURCE_PATH = "C:\\Program Files (x86)\\Superonline\\tvdestek\\kiosk-client-ui-sol-1.0.0-SNAPSHOT-executable-win32_x86.jar";
    public static final String OLDVER_DEST_PATH = System.getProperty("user.home") + "\\.superonline\\tvdestek\\oldversions\\" + fileName + "\\kiosk-client-ui-sol-1.0.0-SNAPSHOT-executable-win32_x86.jar";
    public static final String OLDVER_CREATED_PATH = "\\.superonline\\tvdestek\\oldversions\\" + fileName + "\\";
    public static final String UPDATER_TXT_URI = "http://egemsoft.net/superonlinekiosk/updater.txt";
    public static final String NEW_VERSION_SOURCE_PATH = System.getProperty("user.home") + "\\.superonline\\tvdestek\\temp\\kiosk-client-ui-sol-1.0.0-SNAPSHOT-executable-win32_x86.jar";
    public static final String NEW_VERSION_DEST_PATH = "C:\\Program Files (x86)\\Superonline\\tvdestek\\kiosk-client-ui-sol-1.0.0-SNAPSHOT-executable-win32_x86.jar";

}
