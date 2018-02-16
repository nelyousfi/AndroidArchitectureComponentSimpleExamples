package ma.smartmedia.todoroom.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ma.smartmedia.todoroom.R;
import ma.smartmedia.todoroom.db.entity.CategoryEntity;
import ma.smartmedia.todoroom.db.entity.TodoEntity;
import ma.smartmedia.todoroom.view.callbacks.TodoCallback;

/**
 * Created by Dalvik on 14/02/2018.
 */

public class TodosAdapter extends RecyclerView.Adapter<TodosAdapter.TodoViewHolder> {

    private CategoryEntity category;
    private List<TodoEntity> todos;
    private TodoCallback callback;

    public TodosAdapter(CategoryEntity category, List<TodoEntity> todos, TodoCallback callback) {
        this.category = category;
        this.todos = todos;
        this.callback = callback;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        final TodoEntity todo = todos.get(position);
        holder.nameTextView.setText(todo.getTitle());
        holder.categoryTextView.setText(category.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onClick(todo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView, categoryTextView;

        TodoViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.name_text_view);
            categoryTextView = view.findViewById(R.id.category_text_view);
        }

    }

}
