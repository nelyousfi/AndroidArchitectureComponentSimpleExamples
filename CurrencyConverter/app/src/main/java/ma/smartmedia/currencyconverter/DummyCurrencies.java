package ma.smartmedia.currencyconverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dalvik on 11/02/2018.
 */

public class DummyCurrencies {

    public static List<Currency> generateDummyCurrencies() {
        List<Currency> currencies = new ArrayList<>();
        currencies.add(new Currency("$", "US DOLLAR", 1, 1));
        currencies.add(new Currency("€", "EURO", 0.81, 0.81));
        currencies.add(new Currency("£", "BRITISH POUND", 0.72, 0.72));
        currencies.add(new Currency("¥", "CHINESE YUAN", 6.28, 6.28));
        return currencies;
    }
}
