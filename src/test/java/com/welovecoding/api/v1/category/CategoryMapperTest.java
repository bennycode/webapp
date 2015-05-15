package com.welovecoding.api.v1.category;


import com.welovecoding.data.category.Category;
import com.welovecoding.data.category.CategoryFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class CategoryMapperTest {

  @Rule
  public TestName name = new TestName();

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void shouldReturnNullWhenEntityIsNull() {
    System.out.println(name.getMethodName());

    CategoryDTO result = CategoryMapper.entityToDto(null, 1);

    assertThat(null, equalTo(result));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenDeptIsNegative() {
    System.out.println(name.getMethodName());
    Category entity = CategoryFactory.constructCategory(1, 2);

    CategoryDTO result = CategoryMapper.entityToDto(entity, -1);
  }

  @Test
  public void shouldReturnNullWhenDeptIsZero() {
    System.out.println(name.getMethodName());
    Category entity = CategoryFactory.constructCategory(1, 1);

    CategoryDTO result = CategoryMapper.entityToDto(entity, 0);

    assertThat(result, nullValue());
  }

  @Test
  public void shouldReturnFlatCategoryWhenDeptIsOne() {
    System.out.println(name.getMethodName());
    int resultDept = 1;
    Category entity = CategoryFactory.constructCategory(1, 3);
    CategoryDTO expResult = CategoryDTOFactory.constructCategoryDTO(1, resultDept);

    CategoryDTO result = CategoryMapper.entityToDto(entity, resultDept);

    assertThat(expResult, samePropertyValuesAs(result));
//    assertThat(result.getNews(), emptyCollectionOf(NewsDTO.class));
  }

  @Test
  public void shouldReturnCategoryWithNewsWhenDeptIsTwo() {
    System.out.println(name.getMethodName());
    int resultDept = 2;
    Category entity = CategoryFactory.constructCategory(1, 3);
    CategoryDTO expResult = CategoryDTOFactory.constructCategoryDTO(1, resultDept);

    CategoryDTO result = CategoryMapper.entityToDto(entity, resultDept);

    assertThat(expResult, samePropertyValuesAs(result));
//    assertThat(result.getNews(), hasItem(instanceOf(NewsDTO.class)));
//    assertThat(new ArrayList<>(result.getNews()).get(0).getCategory(), nullValue());
  }

  @Test
  public void testEntityToDtoWithNullNews() {
    System.out.println(name.getMethodName());

    Category entity = CategoryFactory.constructCategoryWithNullNews(1);
    CategoryDTO expResult = CategoryDTOFactory.constructCategoryDTO(1, 1);

    CategoryDTO result = CategoryMapper.entityToDto(entity, 2);

    assertThat(expResult, samePropertyValuesAs(result));
  }
}
