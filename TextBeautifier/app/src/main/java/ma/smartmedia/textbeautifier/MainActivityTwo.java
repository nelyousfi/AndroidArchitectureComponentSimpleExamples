package ma.smartmedia.textbeautifier;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivityTwo extends AppCompatActivity {

    private EditText uglyEditText;
    private TextView beautifulTextView;
    private TextWatcher textWatcher;
    private MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initView();
        convertToHtml(uglyEditText.getText().toString());
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                viewModel.getText().setValue(editable.toString());
            }
        };
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        LiveData<String> awesomeData = Transformations.map(viewModel.getText(), new Function<String, String>() {
            @Override
            public String apply(String input) {
                return "~> " + input;
            }
        });
        awesomeData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                convertToHtml(s);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        uglyEditText.addTextChangedListener(textWatcher);
    }

    @Override
    protected void onPause() {
        super.onPause();
        uglyEditText.removeTextChangedListener(textWatcher);
    }

    private void initView() {
        uglyEditText = findViewById(R.id.ugly_edit_text);
        beautifulTextView = findViewById(R.id.beautiful_text_view);
    }

    private void convertToHtml(String text) {
        String html = generateHtml(text);
        showBeautifulHtml(html);
    }

    private void showBeautifulHtml(String html) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            beautifulTextView.setText(Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY));
        } else {
            beautifulTextView.setText(Html.fromHtml(html));
        }
    }

    private static String generateHtml(String source) {
        if (source.length() < 3) return source;
        if (source.charAt(0) == '*' && source.charAt(source.length() - 1) == '*') {
            return "<b>" + source.substring(1, source.length() - 1) + "</b>";
        }
        if (source.charAt(0) == '~' && source.charAt(source.length() - 1) == '~') {
            return "<del>" + source.substring(1, source.length() - 1) + "</del>";
        }
        if (source.charAt(0) == '_' && source.charAt(source.length() - 1) == '_') {
            return "<i>" + source.substring(1, source.length() - 1) + "</i>";
        }
        return source;
    }

}
