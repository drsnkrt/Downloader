package net.egemsoft.updater.metodlar;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by drsnkrt on 17.07.2017.
 */
public class DownloadFilesOld {

    static BufferedInputStream in = null;
    static FileOutputStream fout = null;
    static long startTime = 0;
    static long endTime = 0;
    static long finishTime = 0;

    public static void downloadFile() {


        try {

//            File tmpFolder = new File("C:\\Users\\drsnkrt\\tempTvDestek");
//            if (!tmpFolder.exists()) {
//                tmpFolder.mkdir();
//                System.out.println("Temp dosyası oluşturuldu..");
//            }

            in = new BufferedInputStream(new URL("https://doc-0o-38-docs.googleusercontent.com/docs/securesc/5vaqsnuv382gsdv6ernf2sjbrijrh7pj/346dqrs5msb9sahnis8k9ag5bq134b6s/1500292800000/14529778210927207293/14529778210927207293/0B1Y9MRhCo2zycjllRlUyMVFMWDg?e=download&nonce=bgjgo9551habi&user=14529778210927207293&hash=2o73aqpeg2hoo2km8g7ouqq8ca58nbt0").openStream());
            fout = new FileOutputStream("C:\\Users\\drsnkrt\\Desktop\\updater\\asd.rar");


            startTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
            final byte data[] = new byte[1024];
            int count;
            int down = 0;
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
                down = down + count / 1024;
                System.out.println("Dosyadan " + down + " kb indi");
            }
            endTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
            finishTime = endTime - startTime;
            System.out.println("Dosya " + finishTime + "saniyede indi !");

            double kilobytes = (fout.getChannel().size() / 1024);
            double megabytes = (kilobytes / 1024);
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            System.out.println("Dosya " + decimalFormat.format(megabytes) + " Mbdir ");
            in.close();
            fout.close();
        } catch (Exception e) {
            System.out.println("Dosya yazılamadı !" + e.getMessage());
        } finally {
            System.out.println("saddasdasdsadsad");
        }
    }

    public static void download() {

        try {
            URL website = new URL("https://doc-0o-38-docs.googleusercontent.com/docs/securesc/5vaqsnuv382gsdv6ernf2sjbrijrh7pj/346dqrs5msb9sahnis8k9ag5bq134b6s/1500292800000/14529778210927207293/14529778210927207293/0B1Y9MRhCo2zycjllRlUyMVFMWDg?e=download&nonce=bgjgo9551habi&user=14529778210927207293&hash=2o73aqpeg2hoo2km8g7ouqq8ca58nbt0");
            ReadableByteChannel rbc;
            rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream("C:\\Users\\drsnkrt\\Desktop\\updater\\asd.rar");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
