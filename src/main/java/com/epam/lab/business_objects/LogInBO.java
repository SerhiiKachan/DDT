package com.epam.lab.business_objects;

import com.epam.lab.driver.DriverManager;
import com.epam.lab.page_objects.AuthorizationPage;
import com.epam.lab.parser.XML_models.User;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static com.epam.lab.constants.Constants.START_URL;

public class LogInBO {

    private Logger LOG = Logger.getLogger(LogInBO.class);
    private AuthorizationPage authorizationPage;

    public LogInBO() {
        WebDriver driver = DriverManager.getDriver();
        authorizationPage = new AuthorizationPage(driver);
        driver.get(START_URL);
    }

    public void logIn(User user) {
        LOG.info("=> Authorization...");
        authorizationPage.enterEmailAndClickNext(user.getEmail());
        authorizationPage.enterPasswordAndClickNext(user.getPassword());
    }
}
