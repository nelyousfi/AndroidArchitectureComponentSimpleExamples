package ma.smartmedia.todoroom.db;

import java.util.ArrayList;
import java.util.List;

import ma.smartmedia.todoroom.R;
import ma.smartmedia.todoroom.db.entity.CategoryEntity;

/**
 * Created by Dalvik on 14/02/2018.
 */

public class DummyCategories {

    public static List<CategoryEntity> generateCategories() {
        List<CategoryEntity> categories = new ArrayList<>();
        categories.add(new CategoryEntity("Personal", R.drawable.ic_user, R.color.personal, R.color.personalBackground));
        categories.add(new CategoryEntity("Work", R.drawable.ic_briefcase, R.color.work, R.color.workBackground));
        categories.add(new CategoryEntity("Meeting", R.drawable.ic_meeting, R.color.meeting, R.color.meetingBackground));
        categories.add(new CategoryEntity("Home", R.drawable.ic_home, R.color.home, R.color.homeBackground));
        categories.add(new CategoryEntity("Sport", R.drawable.ic_award, R.color.sport, R.color.sportBackground));
        categories.add(new CategoryEntity("Other", R.drawable.ic_package, R.color.other, R.color.otherBackground));
        return categories;
    }

}
