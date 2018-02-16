package ma.smartmedia.currencyconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;

public class ConverterActivityFinal extends AppCompatActivity implements ConverterCallback {

    private RecyclerView currenciesRecyclerView;
    private CurrenciesAdapter currenciesAdapter;
    private MyConverterFinal myConverter;
    private ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        myConverter = new MyConverterFinal(getLifecycle(), this);
        getLifecycle().addObserver(myConverter);
        initView();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void initView() {
        final EditText currencyEditText = findViewById(R.id.currency_edit_text);
        currencyEditText.setSelection(3);
        currencyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable text) {
                String dollarValue = text.toString();
                if (!dollarValue.equals("")) {
                    convertCurrencies(Double.valueOf(dollarValue));
                } else {
                    convertCurrencies(0);
                }
                showLoading();
            }
        });
        currenciesRecyclerView = findViewById(R.id.currencies_recycler_view);
        currenciesAdapter = new CurrenciesAdapter(myConverter.getCurrencies());
        currenciesRecyclerView.setAdapter(currenciesAdapter);
        currenciesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        loadingProgressBar = findViewById(R.id.loading_progress_bar);
    }

    private void convertCurrencies(double dollarValue) {
        myConverter.convert(dollarValue);
    }

    private void showLoading() {
        currenciesRecyclerView.setVisibility(View.GONE);
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        currenciesRecyclerView.setVisibility(View.VISIBLE);
        loadingProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onConvert() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hideLoading();
                currenciesAdapter.notifyDataSetChanged();
            }
        });
    }
}
