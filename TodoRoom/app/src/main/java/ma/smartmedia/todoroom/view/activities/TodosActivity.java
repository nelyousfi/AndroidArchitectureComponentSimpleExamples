package ma.smartmedia.todoroom.view.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.List;

import ma.smartmedia.todoroom.R;
import ma.smartmedia.todoroom.db.entity.CategoryEntity;
import ma.smartmedia.todoroom.db.entity.TodoEntity;
import ma.smartmedia.todoroom.view.adapters.TodosAdapter;
import ma.smartmedia.todoroom.view.callbacks.TodoCallback;
import ma.smartmedia.todoroom.viewmodel.TodosViewModel;

public class TodosActivity extends AppCompatActivity implements TodoCallback {

    private RecyclerView todosRecyclerView;
    private BottomSheetBehavior sheetBehavior;
    private RelativeLayout layoutBottomSheet;
    private FloatingActionButton btnBottomSheet;
    private TodosViewModel viewModel;
    private CategoryEntity category;
    private ImageView sadImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        category = (CategoryEntity) getIntent().getSerializableExtra("category");

        initViews();

        initBottomSheet();

        viewModel = ViewModelProviders.of(this).get(TodosViewModel.class);
        viewModel.getTodos(category.getId()).observe(this, new Observer<List<TodoEntity>>() {
            @Override
            public void onChanged(@Nullable List<TodoEntity> todos) {
                setTodosList(category, todos);
                if (todos.size() > 0) {
                    hideSadFace();
                } else {
                    showSadFace();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void initViews() {
        todosRecyclerView = findViewById(R.id.todos_recycler_view);
        sadImageView = findViewById(R.id.sad_image_view);
    }

    private void initBottomSheet() {
        layoutBottomSheet = findViewById(R.id.bottom_sheet);
        ImageView addTodoButton = findViewById(R.id.todo_add_button);
        final EditText todoEditText = findViewById(R.id.todo_edit_text);
        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        btnBottomSheet = findViewById(R.id.btnBottomSheet);
        btnBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        addTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = todoEditText.getText().toString();
                viewModel.addTodo(new TodoEntity(title, category.getId()));
                todoEditText.setText("");
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    private void showSadFace() {
        sadImageView.setVisibility(View.VISIBLE);
    }

    private void hideSadFace() {
        sadImageView.setVisibility(View.GONE);
    }

    private void setTodosList(CategoryEntity category, List<TodoEntity> todos) {
        TodosAdapter adapter = new TodosAdapter(category, todos, this);
        todosRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        todosRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(TodoEntity todo) {
        viewModel.moveToDone(todo);
    }

    @Override
    public void onDelete(TodoEntity todo) {
        viewModel.deleteTodo(todo);
    }
}
