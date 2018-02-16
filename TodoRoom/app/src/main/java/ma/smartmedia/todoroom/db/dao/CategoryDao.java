package ma.smartmedia.todoroom.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import ma.smartmedia.todoroom.db.entity.CategoryEntity;

/**
 * Created by Dalvik on 16/02/2018.
 */
@Dao
public interface CategoryDao {
    @Query("SELECT * FROM category")
    LiveData<List<CategoryEntity>> loadAllCategories();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<CategoryEntity> categories);
}
