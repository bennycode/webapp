package com.welovecoding.api.v1.account;


import com.welovecoding.api.v1.news.NewsDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class AccountDTOFactoryTest {

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

    AccountDTO dto = AccountDTOFactory.constructAccountDTO(null, 1);

    assertThat(dto.getId(), nullValue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenDeptIsNegative() {
    System.out.println(name.getMethodName());

    AccountDTO dto = AccountDTOFactory.constructAccountDTO(1, -1);
  }

  @Test
  public void shouldReturnNullWhenDeptIsZero() {
    System.out.println(name.getMethodName());

    AccountDTO dto = AccountDTOFactory.constructAccountDTO(1, 0);

    assertThat(dto, nullValue());
  }

  @Test
  public void shouldReturnFlatAccountWhenDeptIsOne() {
    System.out.println(name.getMethodName());

    AccountDTO dto = AccountDTOFactory.constructAccountDTO(1, 1);

    assertThat(dto.getNews(), notNullValue());
    assertThat(dto.getNews(), emptyCollectionOf(NewsDTO.class));
  }

  @Test
  public void shouldReturnAccountWithNewsWhenDeptIsTwo() {
    System.out.println(name.getMethodName());

    AccountDTO dto = AccountDTOFactory.constructAccountDTO(1, 2);

    assertThat(dto.getNews(), notNullValue());
    assertThat(dto.getNews(), hasItem(instanceOf(NewsDTO.class)));
    assertThat(new ArrayList<>(dto.getNews()).get(0).getAccount(), nullValue());
  }

  @Test
  public void shouldReturnAccountWithNewsWithAccountWhenDeptIsThree() {
    System.out.println(name.getMethodName());

    AccountDTO dto = AccountDTOFactory.constructAccountDTO(1, 3);

    assertThat(dto.getNews(), notNullValue());
    assertThat(new ArrayList<>(dto.getNews()).get(0).getAccount(), notNullValue());
    assertThat(new ArrayList<>(dto.getNews()).get(0).getAccount().getNews(), emptyCollectionOf(NewsDTO.class));
  }

}
