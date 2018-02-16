package ma.smartmedia.todoroom.view.callbacks;

import ma.smartmedia.todoroom.db.entity.TodoEntity;

/**
 * Created by Dalvik on 14/02/2018.
 */

public interface TodoCallback {
    void onClick(TodoEntity todo);
}
