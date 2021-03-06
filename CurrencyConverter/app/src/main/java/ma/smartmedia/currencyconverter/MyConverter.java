package ma.smartmedia.currencyconverter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import java.util.List;

/**
 * Created by Dalvik on 11/02/2018.
 */

public class MyConverter implements LifecycleObserver {

    private List<Currency> currencies;
    private ConverterCallback callback;

    public static final String TAG = "Naoufal";

    public MyConverter(ConverterCallback callback) {
        currencies = DummyCurrencies.generateDummyCurrencies();
        this.callback = callback;
    }

    public void onStart() {
        Log.d(TAG, "onStart()");
    }

    public void onStop() {
        Log.d(TAG, "onStop()");
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void convert(final double dollarValue) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {

                }
                for (Currency currency : currencies) {
                    currency.setValue(dollarValue * currency.getOneDollarValue());
                }
                callback.onConvert();
            }
        }).start();
    }

}
