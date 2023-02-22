package Utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class UtilityClass {
    public static WebDriver driver;

    static {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    public static void quitDriver(int time){
        try {
            Thread.sleep(time, 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }
    public static void Wait(int seconds){
        try {
            Thread.sleep(seconds, 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


