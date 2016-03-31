package com.welovecoding.data.category.entity;

import com.welovecoding.data.tutorial.entity.Tutorial;
import com.welovecoding.data.tutorial.entity.TutorialFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CategoryFactory {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryFactory.class.getName());

    public static final Date FIXED_DATE = new Date(1000L);

    public static Category constructOne(Integer id, int dept) {
        LOG.info("Constructing Category");
        Long longId = null;
        dept--;
        if (dept > -1) {
            if (id != null) {
                longId = new Long(id);
            }
            return new Category(longId, FIXED_DATE, FIXED_DATE, "slug1", "title1", "#EF9608", TutorialFactory.constructList(10,
                    1, dept));
        }
        return null;
    }

    public static Category constructOneWithNullValues(Integer id) {
        LOG.info("Constructing Video With Null Values");
        Long longId = null;
        if (id != null) {
            longId = new Long(id);
        }
        return new Category(longId, FIXED_DATE, FIXED_DATE, "slug1", "title1", "#EF9608", null);
    }

    public static List<Category> constructList(int size, int startId, int dept) {
        LOG.info("Constructing Category List");
        List<Category> categories = new ArrayList<>();
        dept--;
        if (dept > -1) {
            for (int i = startId; i < (size + startId); i++) {
                List<Tutorial> list = new ArrayList<>();
                if (dept > -1) {
                    list = TutorialFactory.constructList(10, i, dept);
                }
                categories.add(new Category(new Long(i), FIXED_DATE, FIXED_DATE, "slug" + i, "title" + i, "#EF9608", list));
            }
        }
        return categories;
    }

}
