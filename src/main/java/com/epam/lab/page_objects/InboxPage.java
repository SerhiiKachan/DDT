package com.epam.lab.page_objects;

import com.epam.lab.page_objects.decorator.CustomFieldDecorator;
import com.epam.lab.specific_elements.Button;
import com.epam.lab.specific_elements.CheckBox;
import com.epam.lab.specific_elements.Label;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

import static com.epam.lab.constants.Constants.COMPLETED;

public class InboxPage {

    private WebDriver driver;
    private List<String> oldTitles;

    private final Logger LOG = Logger.getLogger(InboxPage.class);

    @FindBy(css = "table.F.cf.zt tbody div[role='checkbox']")
    private List<CheckBox> checkBoxes;

    @FindBy(css = "span.bog")
    private List<Label> actualTitles;

    @FindBy(css = "div.T-I.J-J5-Ji.nX.T-I-ax7.T-I-Js-Gs.mA")
    private Button deleteButton;

    @FindBy(id = "link_undo")
    private Button undoButton;

    @FindBy(css = "span.gb_9a.gbii")
    private Button account;

    @FindBy(css = "div.gb_Eb")
    private Label actualEmail;

    public InboxPage(WebDriver driver) {
        this.driver = driver;
        oldTitles = new ArrayList<>();
        PageFactory.initElements(new CustomFieldDecorator(driver), this);
    }

    private void selectMessage(CheckBox checkBox, String title) {
        try {
            checkBox.setChecked();
        } catch (StaleElementReferenceException e) {
            checkBox.setChecked();
        }
        oldTitles.add(title);
        LOG.info("=> Message selected");
    }

    public void deleteSelectedMessages() {
        deleteButton.waitAndClick();
    }

    public void undo() {
        undoButton.waitAndClick();
    }

    public void selectSeveralMessages(int quantity) {
        int i = 0;
        if (checkBoxes.size() > quantity) {
            while (i < quantity) {
                selectMessage(checkBoxes.get(i), actualTitles.get(i).getText());
                i++;
            }
        } else {
            while ((i < checkBoxes.size())) {
                selectMessage(checkBoxes.get(i), actualTitles.get(i).getText());
                i++;
            }
        }
    }

    public int getInboxMessagesQuantity() {
        if (checkBoxes.size() == 0) {
            LOG.error("There is no inbox messages.");
            return 0;
        } else
            return checkBoxes.size();
    }

    public boolean isUserLoggedIn(String expectedEmail) {
        LOG.info("===> Checking whether user is logged in...");
        account.click();
        return actualEmail.getText().equals(expectedEmail);
    }

    public boolean isUndoCompleted() {
        LOG.info("===> Checking undo operation result...");
        driver.navigate().refresh();
        int i = 0;
        while (i < oldTitles.size()) {
            try {
                if (!actualTitles.get(i).getText().equals(oldTitles.get(i)))
                    throw new NoSuchElementException("Can't find element with text" + oldTitles.get(i) + " It has been deleted.");
                i++;
            } catch (UnhandledAlertException e) {
                driver.switchTo().alert().accept();
                i++;
            } catch (NoSuchElementException e) {
                LOG.error(e.getMessage());
                return false;
            }
        }
        LOG.info(COMPLETED);
        return true;
    }
}
