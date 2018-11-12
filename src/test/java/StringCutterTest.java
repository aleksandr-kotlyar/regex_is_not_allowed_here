import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;
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
        BigDecimal fixPrice = new BigDecimal(-55.99).setScale(2, BigDecimal.ROUND_DOWN);
        for (String price : negativePrices) {
            BigDecimal parsedPrice = convertToDecimalPriceWithoutShit(price);
            assertEquals(parsedPrice, fixPrice);
        }
    }

    @Test
    public void testPositivePrices() {
        BigDecimal fixPrice = new BigDecimal(55.99).setScale(2, BigDecimal.ROUND_DOWN);
        for (String price : positivePrices) {
            BigDecimal parsedPrice = convertToDecimalPriceWithoutShit(price);
            assertEquals(parsedPrice, fixPrice);
        }
    }

    private BigDecimal convertToDecimalPriceWithoutShit(String price) {
        info("unParsedPrice= '" + price + "'");
        price = StringUtils.deleteWhitespace(price);
        price = StringUtils.remove(price, "RUB");
        price = StringUtils.removeEnd(price, ",");
        price = StringUtils.replaceOnce(price, ",", ".");
        info("parsedPrice = '" + price + "'");
        return new BigDecimal(price).setScale(2, BigDecimal.ROUND_DOWN);
    }

    private void info(String logMessage) {
        String s = "\n--------------------------------\n";
        getLoggerInstance().info( s + logMessage + s);
    }

    private static final Logger LOGGER = Logger.getLogger(StringCutterTest.class.getName());

    private Logger getLoggerInstance() {
        return LOGGER;
    }
}
