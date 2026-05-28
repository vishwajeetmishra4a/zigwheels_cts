package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String capture(WebDriver driver, String fileName) {

        // time stamp
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // final file name
        String finalName = fileName + "_" + timeStamp;

        // folder path
        String path = System.getProperty("user.dir") + "/screenshots/";

        try {
            // create folder if not exists
            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // take screenshot
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // ✅ destination file
            File dest = new File(path + finalName + ".png");

            // copy file
            FileHandler.copy(src, dest);

            // print path
            System.out.println("Screenshot saved: " + dest.getAbsolutePath());

            return dest.getAbsolutePath();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}