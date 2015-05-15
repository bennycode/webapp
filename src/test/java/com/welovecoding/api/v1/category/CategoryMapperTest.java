package com.welovecoding.api.v1.category;


import com.welovecoding.api.v1.account.AccountDTO;
import com.welovecoding.api.v1.account.AccountMapper;
import com.welovecoding.api.v1.news.NewsDTO;
import com.welovecoding.data.account.Account;
import com.welovecoding.data.account.AccountFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.util.ArrayList;

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

    AccountDTO result = AccountMapper.entityToDto(null, 1);

    assertThat(null, equalTo(result));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenDeptIsNegative() {
    System.out.println(name.getMethodName());
    Account entity = AccountFactory.constructAccount(1, 2);

    AccountDTO result = AccountMapper.entityToDto(entity, -1);
  }

  @Test
  public void shouldReturnNullWhenDeptIsZero() {
    System.out.println(name.getMethodName());
    Account entity = AccountFactory.constructAccount(1, 1);

    AccountDTO result = AccountMapper.entityToDto(entity, 0);

    assertThat(result, nullValue());
  }

  @Test
  public void shouldReturnFlatAccountWhenDeptIsOne() {
    System.out.println(name.getMethodName());
    int resultDept = 1;
    Account entity = AccountFactory.constructAccount(1, 3);
    AccountDTO expResult = CategoryDTOFactory.constructAccountDTO(1, resultDept);

    AccountDTO result = AccountMapper.entityToDto(entity, resultDept);

    assertThat(expResult, samePropertyValuesAs(result));
    assertThat(result.getNews(), emptyCollectionOf(NewsDTO.class));
  }

  @Test
  public void shouldReturnAccountWithNewsWhenDeptIsTwo() {
    System.out.println(name.getMethodName());
    int resultDept = 2;
    Account entity = AccountFactory.constructAccount(1, 3);
    AccountDTO expResult = CategoryDTOFactory.constructAccountDTO(1, resultDept);

    AccountDTO result = AccountMapper.entityToDto(entity, resultDept);

    assertThat(expResult, samePropertyValuesAs(result));
    assertThat(result.getNews(), hasItem(instanceOf(NewsDTO.class)));
    assertThat(new ArrayList<>(result.getNews()).get(0).getAccount(), nullValue());
  }

  @Test
  public void testEntityToDtoWithNullNews() {
    System.out.println(name.getMethodName());

    Account entity = AccountFactory.constructAccountWithNullNews(1);
    AccountDTO expResult = CategoryDTOFactory.constructAccountDTO(1, 1);

    AccountDTO result = AccountMapper.entityToDto(entity, 2);

    assertThat(expResult, samePropertyValuesAs(result));
  }
}
