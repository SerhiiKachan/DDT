package com.epam.lab.page_objects;

import com.epam.lab.page_objects.decorator.CustomFieldDecorator;
import com.epam.lab.specific_elements.Button;
import com.epam.lab.specific_elements.TextInput;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthorizationPage {

    private final Logger LOG = Logger.getLogger(AuthorizationPage.class);

    @FindBy(xpath = "//input[@id='identifierId']")
    private TextInput email;

    @FindBy(id = "identifierNext")
    private Button emailNextButton;

    @FindBy(name = "password")
    private TextInput password;

    public AuthorizationPage(WebDriver driver) {
        PageFactory.initElements(new CustomFieldDecorator(driver), this);
    }

    public void enterEmailAndClickNext(String mail) {
        LOG.info("===> Entering email...");
        email.fillInputWith(mail);
        LOG.info("===> Submitting email...");
        emailNextButton.click();
    }

    public void enterPasswordAndClickNext(String pass) {
        LOG.info("===> Entering password...");
        password.fillInputWith(pass + Keys.ENTER);
        LOG.info("===> Password submitted");
    }
}
