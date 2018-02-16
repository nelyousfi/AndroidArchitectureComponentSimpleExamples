package ma.smartmedia.todoroom.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import ma.smartmedia.todoroom.db.entity.TodoEntity;

/**
 * Created by Dalvik on 16/02/2018.
 */
@Dao
public interface TodoDao {
    @Query("SELECT * FROM todo WHERE categoryId = :categoryId")
    LiveData<List<TodoEntity>> loadEntities(int categoryId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TodoEntity entity);
}