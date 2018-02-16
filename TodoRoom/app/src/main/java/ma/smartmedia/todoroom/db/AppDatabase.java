package ma.smartmedia.todoroom.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import ma.smartmedia.todoroom.AppExecutors;
import ma.smartmedia.todoroom.db.dao.CategoryDao;
import ma.smartmedia.todoroom.db.dao.TodoDao;
import ma.smartmedia.todoroom.db.entity.CategoryEntity;
import ma.smartmedia.todoroom.db.entity.TodoEntity;

/**
 * Created by Dalvik on 16/02/2018.
 */
@Database(entities = {CategoryEntity.class, TodoEntity.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract CategoryDao categoryDao();

    public abstract TodoDao todoDao();

    public static AppDatabase getInstance(final Context context, final AppExecutors executors) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = buildDatabase(context.getApplicationContext(), executors);
                }
            }
        }
        return instance;
    }

    private static AppDatabase buildDatabase(final Context appContext, final AppExecutors appExecutors) {
        return Room.databaseBuilder(appContext, AppDatabase.class, "todos")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        appExecutors.diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                AppDatabase database = AppDatabase.getInstance(appContext, appExecutors);
                                database.categoryDao().insertAll(DummyCategories.generateCategories());
                            }
                        });
                    }
                })
                .fallbackToDestructiveMigration()
                .build();

    }

}
