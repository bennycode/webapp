package com.welovecoding.api.v1.news;

import com.welovecoding.data.news.News;
import com.welovecoding.data.news.NewsFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class NewsMapperTest {

  @Rule
  public TestName name = new TestName();

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testEntityToDtoDeptOne() {
    System.out.println(name.getMethodName());
    int dept = 1;
    News entity = NewsFactory.constructNews(1, dept);
    NewsDTO expResult = NewsDTOFactory.constructNewsDTO(1, dept);

    NewsDTO result = NewsMapper.entityToDto(entity, dept);

    assertThat(expResult, samePropertyValuesAs(result));
  }

  @Test
  public void testEntityToDtoDeptTwo() {
    System.out.println(name.getMethodName());
    int dept = 2;
    News entity = NewsFactory.constructNews(1, dept);
    NewsDTO expResult = NewsDTOFactory.constructNewsDTO(1, dept);

    NewsDTO result = NewsMapper.entityToDto(entity, dept);

    assertThat(expResult, samePropertyValuesAs(result));
  }

  @Test
  public void testEntityToDtoWithNull() {
    System.out.println(name.getMethodName());
    NewsDTO result = NewsMapper.entityToDto(null, 1);
    assertThat(null, equalTo(result));
  }

  @Test
  public void testEntityToDtoWithNullAccount() {
    System.out.println(name.getMethodName());
    fail("Not tested yet!");
  }

  @Test
  public void testDtoToEntity() {
    System.out.println(name.getMethodName());
    fail("Not tested yet!");
  }

  @Test
  public void testEntityListToDtoList() {
    System.out.println(name.getMethodName());
    fail("Not tested yet!");
  }

  @Test
  public void testEntitySetToDtoSet() {
    System.out.println(name.getMethodName());
    fail("Not tested yet!");
  }

}
