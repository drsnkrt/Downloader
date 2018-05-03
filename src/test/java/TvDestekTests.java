import net.egemsoft.updater.util.DownloadFiles;
import net.egemsoft.updater.util.InternetTestConnection;
import net.egemsoft.updater.util.MoveOperations;
import net.egemsoft.updater.util.StartStopOperations;
import org.junit.Assert;
import org.junit.Test;
import net.egemsoft.updater.metodlar.*;

/**
 * Created by drsnkrt on 17.07.2017.
 */
public class TvDestekTests {

    @Test
    public void internetConnectionTest() {

        InternetTestConnection connection = new InternetTestConnection();
//        boolean result = connection.internetTest();
//        Assert.assertTrue(result);
    }

    @Test
    public void DownloadFileTest() {
        DownloadFiles files = new DownloadFiles();
        boolean r = files.downloadFile();
        Assert.assertTrue(r);
    }

    @Test
    public void MoveTest() {

        MoveOperations operations = new MoveOperations();
        operations.moveNewFileToPath();
    }

    @Test
    public void MoveNewFilesTest() {

        MoveNewFiles2Path files = new MoveNewFiles2Path();
//        files.createFwPaths();
//    files.deleteCurrentFw();
    }

    @Test
    public void StartTvDestek() {

        StartStopOperations destek = new StartStopOperations();
        destek.startTvDestek();
    }

    @Test
    public void StopTvDestek() {

        StartStopOperations destek = new StartStopOperations();
        destek.stopTvDestekUpdater();
    }


}
