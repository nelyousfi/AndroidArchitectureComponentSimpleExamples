package ma.smartmedia.textbeautifier;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by Dalvik on 13/02/2018.
 */

public class MyViewModel extends ViewModel {

    private MutableLiveData<String> text;

    public MutableLiveData<String> getText() {
        if (text == null) {
            text = new MutableLiveData<>();
        }
        return text;
    }
}
