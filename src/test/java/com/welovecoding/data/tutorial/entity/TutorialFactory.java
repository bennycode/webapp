package com.welovecoding.data.tutorial.entity;

import com.welovecoding.data.category.entity.CategoryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TutorialFactory {

    private static final Logger LOG = LoggerFactory.getLogger(TutorialFactory.class.getName());

    public static final Date FIXED_DATE = new Date(1000L);

    public static Tutorial constructOne(Integer id, int dept) {
        LOG.info("Constructing Tutorial");
        Long longId = null;
        dept--;
        if (dept > -1) {
            if (id != null) {
                longId = new Long(id);
            }
            return new Tutorial(
                    longId,
                    FIXED_DATE,
                    FIXED_DATE,
                    "slug1",
                    "title1",
                    "desc1",
                    true,
                    null,//AuthorFactory.constructList(10, 1, dept),
                    CategoryFactory.constructOne(1, dept),
                    LanguageCode.EN,
                    Difficulty.EASY
            );
        }
        return null;
    }

    public static Tutorial constructWithNullValues(Integer id) {
        LOG.info("Constructing Tutorial With Null Values");
        Long longId = null;
        if (id != null) {
            longId = new Long(id);
        }
        return new Tutorial(
                longId,
                FIXED_DATE,
                FIXED_DATE,
                "slug1",
                "title1",
                "desc1",
                true,
                null,
                null,
                LanguageCode.EN,
                Difficulty.EASY
        );
    }

    public static List<Tutorial> constructList(int size, int startId, int dept) {
        LOG.info("Constructing Tutorial List");
        List<Tutorial> tutorials = new ArrayList<>();
        dept--;
        if (dept > -1) {
            for (int i = startId; i < (size + startId); i++) {
                List<Author> set = new ArrayList<>();
                if (dept > -1) {
//          set = AuthorFactory.constructList(10, i, dept);
                }
                tutorials.add(new Tutorial(
                        new Long(i),
                        FIXED_DATE,
                        FIXED_DATE,
                        "slug" + i,
                        "title" + i,
                        "desc" + i,
                        true,
                        set,
                        CategoryFactory.constructOne(i, dept),
                        LanguageCode.EN,
                        Difficulty.EASY
                ));
            }
        }
        return tutorials;
    }

}
