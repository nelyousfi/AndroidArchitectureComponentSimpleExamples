package ma.smartmedia.todoroom.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import ma.smartmedia.todoroom.MyApplication;
import ma.smartmedia.todoroom.db.entity.CategoryEntity;

/**
 * Created by Dalvik on 16/02/2018.
 */

public class CategoriesViewModel extends AndroidViewModel {

    private LiveData<List<CategoryEntity>> categories;

    public CategoriesViewModel(Application application) {
        super(application);

        categories = ((MyApplication) application).getRepository().getCategories();
    }

    public LiveData<List<CategoryEntity>> getCategories() {
        return categories;
    }

}
