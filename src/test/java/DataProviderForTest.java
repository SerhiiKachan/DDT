import com.epam.lab.parser.MyParser;
import org.testng.annotations.DataProvider;

import static com.epam.lab.constants.Constants.*;

public class DataProviderForTest {

    @DataProvider(name = "csvDataProvider", parallel = true)
    public static Object[] getCSVUsers() {
        return MyParser.parseCSV(USER_CSV_PATH);
    }

    @DataProvider(name = "xmlDataProvider", parallel = true)
    public static Object[] getXMLUsers() {
        return MyParser.parseXML(USER_XML_PATH);
    }

    @DataProvider(name = "excelDataProvider", parallel = true)
    public static Object[] getEXCELUsers() {
        return MyParser.parseEXCEL(USER_EXCEL_PATH);
    }
}
