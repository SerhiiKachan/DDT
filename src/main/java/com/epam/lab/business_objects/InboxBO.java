package com.epam.lab.business_objects;

import com.epam.lab.driver.DriverManager;
import com.epam.lab.page_objects.InboxPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class InboxBO{

    private Logger LOG = Logger.getLogger(InboxBO.class);
    private InboxPage inboxPage;

    public InboxBO() {
        WebDriver driver = DriverManager.getDriver();
        inboxPage = new InboxPage(driver);
    }

    public void selectMessages(int quantity) {
        LOG.info("=> Selecting messages to delete...");
        inboxPage.selectSeveralMessages(quantity);
    }

    public void deleteSelectedMessages() {
        LOG.info("=> Deleting messages...");
        inboxPage.deleteSelectedMessages();
    }

    public int getInboxMessagesQuantity(){
        return inboxPage.getInboxMessagesQuantity();
    }

    public boolean isUserLoggedIn(String email){
        return inboxPage.isUserLoggedIn(email);
    }

    public void undoDeleting(){
        LOG.info("=> Undo deleting...");
        inboxPage.undo();
    }

    public boolean isUndoCompleted() {
        return inboxPage.isUndoCompleted();
    }
}
