import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

/**
 * Created by aleksandr.kotlyar on 12.11.2018. 22:10
 */
public class StringCutterTest {
    private static final String plusDotSpace = "+55.9 RUB";
    private static final String minusDotSpace = "-55.9 RUB";
    private static final String commaSpace = "55,9 RUB";
    private static final String dotTwoSpaces = "55.9  RUB";
    private static final String dotTabSpace = "55.9  \t RUB";
    private static final String dotNewLine = "55.9 \nRUB";
    private static final String minusSpaceDotSpace = "- 55.9 RUB";
    private List<String> prices = Arrays.asList(plusDotSpace,
            minusDotSpace,
            commaSpace,
            dotTwoSpaces,
            dotTabSpace,
            dotNewLine,
            minusSpaceDotSpace);

    @Test
    public void testSubstring() {
        String fixPrice = "55,9RUB";
        for (String price : prices) {
            info("unParsed price= '" + price + "'");
            price = StringUtils.remove(price, "+");
            price = StringUtils.remove(price, "-");
            price = StringUtils.deleteWhitespace(price);
            price = StringUtils.replaceOnce(price, ".", ",");
            info("price = '" + price + "', fix = '55,9RUB'");
            assertEquals(price, fixPrice);
        }
    }

    private void info(String logMessage) {
        getLoggerInstance().info("\n--------------------------------\n" + logMessage + "\n--------------------------------\n");
    }

    private static final Logger LOGGER = Logger.getLogger(StringCutterTest.class.getName());

    private Logger getLoggerInstance() {
        return LOGGER;
    }
}
