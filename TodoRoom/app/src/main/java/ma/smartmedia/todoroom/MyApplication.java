package ma.smartmedia.todoroom;

import android.app.Application;

import ma.smartmedia.todoroom.db.AppDatabase;

/**
 * Created by Dalvik on 16/02/2018.
 */

public class MyApplication extends Application {

    private AppExecutors appExecutors;

    @Override
    public void onCreate() {
        super.onCreate();
        appExecutors = new AppExecutors();
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(AppDatabase.getInstance(this, appExecutors));
    }
}
