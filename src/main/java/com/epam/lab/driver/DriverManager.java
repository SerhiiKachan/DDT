package com.epam.lab.driver;

import com.epam.lab.parser.MyParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.epam.lab.constants.Constants.DRIVER_PROPERTIES_PATH;

public class DriverManager {

    private DriverManager() {
    }

    private static final ThreadLocal<WebDriver> drivers = ThreadLocal.withInitial(() -> {
        Properties driverProperties = MyParser.parsePropertiesFile(DRIVER_PROPERTIES_PATH);
        System.setProperty("webdriver.chrome.driver", driverProperties.getProperty("browser_driver"));
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(driverProperties.getProperty("implicit_wait")), TimeUnit.SECONDS);
        return driver;
    });

    public static WebDriver getDriver() {
        return drivers.get();
    }

    public static void exit() {
        drivers.get().quit();
        drivers.remove();
    }
}
