import com.epam.lab.business_objects.InboxBO;
import com.epam.lab.business_objects.LogInBO;
import com.epam.lab.driver.DriverManager;
import com.epam.lab.parser.XML_models.User;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.epam.lab.constants.Constants.LOG4J_PROPERTIES_PATH;

public class GMailTest {

    private final Logger LOG = Logger.getLogger(GMailTest.class);

    static {
        PropertyConfigurator.configure(LOG4J_PROPERTIES_PATH);
    }

    @Test(dataProviderClass = DataProviderForTest.class, dataProvider = "csvDataProvider")
    public void testUndoWithMessagesDeletion(User user) {
        LOG.info("TEST STARTED");
        LogInBO logInBO = new LogInBO();
        logInBO.logIn(user);
        InboxBO inboxBO = new InboxBO();
        Assert.assertTrue(inboxBO.isUserLoggedIn(user.getEmail()));
        Assert.assertNotEquals(0, inboxBO.getInboxMessagesQuantity());
        inboxBO.selectMessages(3);
        inboxBO.deleteSelectedMessages();
        inboxBO.undoDeleting();
        Assert.assertTrue(inboxBO.isUndoCompleted());
        LOG.info("TEST SUCCESSFULLY PASSED");
    }

    @AfterMethod(alwaysRun = true)
    public void exit() {
        DriverManager.exit();
    }
}
