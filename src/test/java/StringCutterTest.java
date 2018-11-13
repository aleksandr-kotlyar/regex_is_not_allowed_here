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
    private static final String plusDotSpace = "+55.99 RUB";
    private static final String minusDotSpace = "-55.99 RUB";
    private static final String commaSpace = "55,99 RUB";
    private static final String dotTwoSpaces = "55.99  RUB";
    private static final String dotTabSpace = "55.99  \t RUB";
    private static final String dotNewLine = "55.99 \nRUB";
    private static final String minusSpaceDotSpace = "- 55.99 RUB";
    private static final String minusSpaceDotCommaSpace = "- 55.99, RUB";
    private List<String> positivePrices = Arrays.asList(plusDotSpace,
            commaSpace,
            dotTwoSpaces,
            dotTabSpace,
            dotNewLine);
    private List<String> negativePrices = Arrays.asList(minusDotSpace,
            minusSpaceDotSpace,
            minusSpaceDotCommaSpace);

    @Test
    public void testNegativePrices() {
        Double expectedPrice = -55.99;
        for (String price : negativePrices) {
            Double parsedPrice = parseAndConvertToDouble(price);
            assertEquals(expectedPrice, parsedPrice);
        }
    }

    @Test
    public void testPositivePrices() {
        Double expectedPrice = 55.99;
        for (String price : positivePrices) {
            Double parsedPrice = parseAndConvertToDouble(price);
            assertEquals(expectedPrice, parsedPrice);
        }
    }

    private Double parseAndConvertToDouble(String price) {
        String parsedPrice;
        parsedPrice = StringUtils.deleteWhitespace(price);
        parsedPrice = StringUtils.remove(parsedPrice, "RUB");
        parsedPrice = StringUtils.removeEnd(parsedPrice, ",");
        parsedPrice = StringUtils.replaceOnce(parsedPrice, ",", ".");
        info("unParsedPrice = '" + price + "'\n" +
                "  parsedPrice = '" + parsedPrice + "'");
        return new Double(parsedPrice);
    }

    private void info(String logMessage) {
        String s = "\n--------------------------------\n";
        getLoggerInstance().info(s + logMessage + s);
    }

    private static final Logger LOGGER = Logger.getLogger(StringCutterTest.class.getName());

    private Logger getLoggerInstance() {
        return LOGGER;
    }
}
