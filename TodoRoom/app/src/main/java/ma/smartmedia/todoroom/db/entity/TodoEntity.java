package ma.smartmedia.todoroom.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import ma.smartmedia.todoroom.model.Todo;

/**
 * Created by Dalvik on 15/02/2018.
 */
@Entity(tableName = "todo",
        foreignKeys = {
            @ForeignKey(entity = CategoryEntity.class,
                    parentColumns = "id",
                    childColumns = "categoryId",
                    onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index(value = "categoryId")}
        )
public class TodoEntity implements Todo {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private int categoryId;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public TodoEntity(String title, int categoryId) {
        this.title = title;
        this.categoryId = categoryId;
    }
}
