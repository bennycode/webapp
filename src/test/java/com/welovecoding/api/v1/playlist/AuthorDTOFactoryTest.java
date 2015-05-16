package com.welovecoding.api.v1.playlist;


import org.junit.*;
import org.junit.rules.TestName;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;


public class AuthorDTOFactoryTest {

  @Rule
  public TestName name = new TestName();

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  @Ignore
  public void shouldReturnAccountWithNullIdWhenStartIdIsNull() {
    System.out.println(name.getMethodName());

    AuthorDTO dto = AuthorDTOFactory.constructAuthorDTO(null, 1);

//    assertThat(dto.getId(), nullValue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenDeptIsNegative() {
    System.out.println(name.getMethodName());

    AuthorDTO dto = AuthorDTOFactory.constructAuthorDTO(1, -1);
  }

  @Test
  public void shouldReturnNullWhenDeptIsZero() {
    System.out.println(name.getMethodName());

    AuthorDTO dto = AuthorDTOFactory.constructAuthorDTO(1, 0);

    assertThat(dto, nullValue());
  }

}
