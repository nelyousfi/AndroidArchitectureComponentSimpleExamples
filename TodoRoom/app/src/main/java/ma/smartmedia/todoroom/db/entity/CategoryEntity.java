package ma.smartmedia.todoroom.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

import ma.smartmedia.todoroom.model.Category;

/**
 * Created by Dalvik on 15/02/2018.
 */
@Entity(tableName = "category")
public class CategoryEntity implements Category, Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int icon;
    private int iconColor;
    private int color;

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getIconColor() {
        return iconColor;
    }

    public void setIconColor(int iconColor) {
        this.iconColor = iconColor;
    }

    public CategoryEntity(int id, String name, int icon, int iconColor, int color) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.iconColor = iconColor;
        this.color = color;
    }

    @Ignore
    public CategoryEntity(String name, int icon, int iconColor, int color) {
        this.name = name;
        this.icon = icon;
        this.iconColor = iconColor;
        this.color = color;
    }
}
