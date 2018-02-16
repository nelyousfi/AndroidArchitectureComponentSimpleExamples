package ma.smartmedia.todoroom.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import ma.smartmedia.todoroom.DataRepository;
import ma.smartmedia.todoroom.MyApplication;
import ma.smartmedia.todoroom.db.entity.TodoEntity;

/**
 * Created by Dalvik on 16/02/2018.
 */

public class TodosViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    public TodosViewModel(Application application) {
        super(application);

        dataRepository = ((MyApplication) application).getRepository();
    }

    public LiveData<List<TodoEntity>> getTodos(int categoryId) {
        return dataRepository.getTodos(categoryId);
    }

    public void addTodo(TodoEntity todo) {
        dataRepository.insertTodo(todo);
    }

}
