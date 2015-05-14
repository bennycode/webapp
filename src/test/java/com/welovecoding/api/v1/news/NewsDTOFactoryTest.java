package com.welovecoding.api.v1.news;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class NewsDTOFactoryTest {

  @Rule
  public TestName name = new TestName();

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  /////////////////////////////////////////
  //constructNewsDTO
  /////////////////////////////////////////
  @Test
  public void shouldReturnNewsWithNullIdWhenStartIdIsNull() {
    System.out.println(name.getMethodName());

    NewsDTO dto = NewsDTOFactory.constructNewsDTO(null, 1);

    assertThat(dto.getId(), nullValue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenDeptIsNegative() {
    System.out.println(name.getMethodName());

    NewsDTO dto = NewsDTOFactory.constructNewsDTO(1, -1);
  }

  @Test
  public void shouldReturnNullWhenDeptIsZero() {
    System.out.println(name.getMethodName());

    NewsDTO dto = NewsDTOFactory.constructNewsDTO(1, 0);

    assertThat(dto, nullValue());
  }

  @Test
  public void shouldReturnFlatNewsWhenDeptIsOne() {
    System.out.println(name.getMethodName());

    NewsDTO dto = NewsDTOFactory.constructNewsDTO(1, 1);

    assertThat(dto.getAccount(), nullValue());
  }

  @Test
  public void shouldReturnNewsWithAccountWhenDeptIsTwo() {
    System.out.println(name.getMethodName());

    NewsDTO dto = NewsDTOFactory.constructNewsDTO(1, 2);

    assertThat(dto.getAccount(), notNullValue());
    assertThat(dto.getAccount().getNews(), emptyCollectionOf(NewsDTO.class));
  }

  @Test
  public void shouldReturnNewsWithAccountWithNewsWhenDeptIsThree() {
    System.out.println(name.getMethodName());

    NewsDTO dto = NewsDTOFactory.constructNewsDTO(1, 3);

    assertThat(dto.getAccount(), notNullValue());
    assertThat(new ArrayList<>(dto.getAccount().getNews()), notNullValue());
    assertThat(new ArrayList<>(dto.getAccount().getNews()).get(0), notNullValue());
  }

}
