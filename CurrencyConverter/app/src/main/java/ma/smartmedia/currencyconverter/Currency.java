package ma.smartmedia.currencyconverter;

/**
 * Created by Dalvik on 11/02/2018.
 */

public class Currency {

    private String symbol, name;
    private double oneDollarValue, value;

    public Currency(String symbol, String name, double oneDollarValue) {
        this.symbol = symbol;
        this.name = name;
        this.oneDollarValue = oneDollarValue;
        this.value = 0.0;
    }

    public Currency(String symbol, String name, double oneDollarValue, double value) {
        this.symbol = symbol;
        this.name = name;
        this.oneDollarValue = oneDollarValue;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getOneDollarValue() {
        return oneDollarValue;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
