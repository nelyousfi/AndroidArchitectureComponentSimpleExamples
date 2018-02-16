package ma.smartmedia.todoroom.view.adapters;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ma.smartmedia.todoroom.R;
import ma.smartmedia.todoroom.db.entity.CategoryEntity;
import ma.smartmedia.todoroom.view.callbacks.CategoryCallback;

/**
 * Created by Dalvik on 14/02/2018.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {

    private Context context;
    private List<CategoryEntity> categories;
    private CategoryCallback callback;

    public CategoriesAdapter(Context context, List<CategoryEntity> categories, CategoryCallback callback) {
        this.context = context;
        this.categories = categories;
        this.callback = callback;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        final CategoryEntity category = categories.get(position);
        holder.nameTextView.setText(category.getName());
        holder.iconImageView.setImageResource(category.getIcon());
        GradientDrawable backgroundGradient = (GradientDrawable)holder.iconImageView.getBackground();
        backgroundGradient.setColor(context.getResources().getColor(category.getColor()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onClick(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        ImageView iconImageView;

        CategoryViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.name_text_view);
            iconImageView = view.findViewById(R.id.icon_image_view);
        }

    }

}
