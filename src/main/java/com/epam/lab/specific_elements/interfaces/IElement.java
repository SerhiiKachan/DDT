package com.epam.lab.specific_elements.interfaces;

import com.epam.lab.driver.DriverManager;
import com.epam.lab.constants.Constants;
import com.epam.lab.parser.MyParser;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public interface IElement {

    class Element implements IElement {

        protected WebElement webElement;
        WebDriverWait wait;

        public Element(WebElement webElement) {
            Properties driverProperties = MyParser.parsePropertiesFile(Constants.DRIVER_PROPERTIES_PATH);
            this.webElement = webElement;
            this.wait = new WebDriverWait(DriverManager.getDriver(), Long.parseLong(driverProperties.getProperty("explicit_wait")));
        }

        public void click() {
            webElement.click();
        }

        public void waitAndClick() {
            wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
        }
    }
}
