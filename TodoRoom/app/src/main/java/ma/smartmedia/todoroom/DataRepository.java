package ma.smartmedia.todoroom;

import android.arch.lifecycle.LiveData;

import java.util.List;

import ma.smartmedia.todoroom.db.AppDatabase;
import ma.smartmedia.todoroom.db.entity.CategoryEntity;
import ma.smartmedia.todoroom.db.entity.TodoEntity;

/**
 * Created by Dalvik on 16/02/2018.
 */

public class DataRepository {

    private static DataRepository instance;

    private final AppDatabase database;

    private DataRepository(final AppDatabase database) {
        this.database = database;
    }

    public static DataRepository getInstance(final AppDatabase database) {
        if (instance == null) {
            synchronized (DataRepository.class) {
                if (instance == null) {
                    instance = new DataRepository(database);
                }
            }
        }
        return instance;
    }

    public LiveData<List<CategoryEntity>> getCategories() {
        return database.categoryDao().loadAllCategories();
    }

    public LiveData<List<TodoEntity>> getTodos(final int categoryId) {
        return database.todoDao().loadEntities(categoryId);
    }

    public void insertTodo(final TodoEntity todo) {
        new AppExecutors().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                database.todoDao().insert(todo);
            }
        });
    }

    public void deleteTodo(final TodoEntity todo) {
        new AppExecutors().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                database.todoDao().delete(todo);
            }
        });
    }

    public void update(final TodoEntity todo) {
        new AppExecutors().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                database.todoDao().update(todo);
            }
        });
    }
}
