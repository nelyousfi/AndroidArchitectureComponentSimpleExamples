package ma.smartmedia.todoroom.view.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ma.smartmedia.todoroom.R;
import ma.smartmedia.todoroom.db.entity.CategoryEntity;
import ma.smartmedia.todoroom.view.adapters.CategoriesAdapter;
import ma.smartmedia.todoroom.view.callbacks.CategoryCallback;
import ma.smartmedia.todoroom.viewmodel.CategoriesViewModel;

public class CategoriesActivity extends AppCompatActivity implements CategoryCallback {

    private RecyclerView categoriesRecyclerView;
    private CategoriesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        initViews();

        viewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);
        viewModel.getCategories().observe(this, new Observer<List<CategoryEntity>>() {
            @Override
            public void onChanged(@Nullable List<CategoryEntity> categories) {
                setCategoriesList(categories);
            }
        });
    }

    private void initViews() {
        categoriesRecyclerView = findViewById(R.id.categories_recycler_view);
    }

    private void setCategoriesList(final List<CategoryEntity> categories) {
        CategoriesAdapter adapter = new CategoriesAdapter(this, categories, this);
        categoriesRecyclerView.setAdapter(adapter);
        categoriesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public void onClick(CategoryEntity category) {
        Intent intent = new Intent(this, TodosActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}
