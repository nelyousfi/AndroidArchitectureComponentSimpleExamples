package ma.smartmedia.currencyconverter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dalvik on 11/02/2018.
 */

public class CurrenciesAdapter extends RecyclerView.Adapter<CurrenciesAdapter.CurrencyViewHolder> {

    private List<Currency> currencies;

    public CurrenciesAdapter(List<Currency> currencies) {
        this.currencies = currencies;
    }

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.currencies_list_item, parent, false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, int position) {
        Currency currency = currencies.get(position);
        holder.setCurrencySymbol(currency.getSymbol());
        holder.setCurrencyName(currency.getName());
        holder.setCurrencyValue(currency.getValue(), currency.getSymbol());
        holder.changeViewBackground(position);
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

    static class CurrencyViewHolder extends RecyclerView.ViewHolder {

        private TextView currencySymbolTextView, currencyNameTextView, currencyValueTextView;

        public CurrencyViewHolder(View view) {
            super(view);
            currencySymbolTextView = view.findViewById(R.id.currency_symbol_text_view);
            currencyNameTextView = view.findViewById(R.id.currency_name_text_view);
            currencyValueTextView = view.findViewById(R.id.currency_value_text_view);
        }

        public void setCurrencySymbol(String symbol) {
            currencySymbolTextView.setText(symbol);
        }

        public void setCurrencyName(String name) {
            currencyNameTextView.setText(name);
        }

        public void setCurrencyValue(double value, String symbol) {
            currencyValueTextView.setText(String.valueOf(Math.floor(value * 100) / 100) + " " + symbol);
        }

        public void changeViewBackground(int position) {
            if (position % 2 == 0) {
                itemView.setBackgroundColor(Color.parseColor("#51C0D6"));
            } else {
                itemView.setBackgroundColor(Color.parseColor("#1EB0CD"));
            }
        }
    }

}
