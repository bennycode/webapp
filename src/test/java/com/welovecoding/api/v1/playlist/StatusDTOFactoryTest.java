package com.welovecoding.api.v1.playlist;


import com.welovecoding.api.v1.category.CategoryDTO;
import org.junit.*;
import org.junit.rules.TestName;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class StatusDTOFactoryTest {

  @Rule
  public TestName name = new TestName();

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void shouldReturnAccountWithNullIdWhenStartIdIsNull() {
    System.out.println(name.getMethodName());

    CategoryDTO dto = StatusDTOFactory.constructCategoryDTO(null, 1);

    assertThat(dto.getId(), nullValue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenDeptIsNegative() {
    System.out.println(name.getMethodName());

    CategoryDTO dto = StatusDTOFactory.constructCategoryDTO(1, -1);
  }

  @Test
  public void shouldReturnNullWhenDeptIsZero() {
    System.out.println(name.getMethodName());

    CategoryDTO dto = StatusDTOFactory.constructCategoryDTO(1, 0);

    assertThat(dto, nullValue());
  }

  @Test
  @Ignore
  public void shouldReturnFlatAccountWhenDeptIsOne() {
    System.out.println(name.getMethodName());

    CategoryDTO dto = StatusDTOFactory.constructCategoryDTO(1, 1);

//    assertThat(dto.getNews(), notNullValue());
//    assertThat(dto.getNews(), emptyCollectionOf(NewsDTO.class));
  }

  @Test
  @Ignore
  public void shouldReturnAccountWithNewsWhenDeptIsTwo() {
    System.out.println(name.getMethodName());

    CategoryDTO dto = StatusDTOFactory.constructCategoryDTO(1, 2);

//    assertThat(dto.getNews(), notNullValue());
//    assertThat(dto.getNews(), hasItem(instanceOf(NewsDTO.class)));
//    assertThat(new ArrayList<>(dto.getNews()).get(0).getAccount(), nullValue());
  }

  @Test
  @Ignore
  public void shouldReturnAccountWithNewsWithAccountWhenDeptIsThree() {
    System.out.println(name.getMethodName());

    CategoryDTO dto = StatusDTOFactory.constructCategoryDTO(1, 3);

//    assertThat(dto.getNews(), notNullValue());
//    assertThat(new ArrayList<>(dto.getNews()).get(0).getAccount(), notNullValue());
//    assertThat(new ArrayList<>(dto.getNews()).get(0).getAccount().getNews(), emptyCollectionOf(NewsDTO.class));
  }

}
