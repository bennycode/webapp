package com.welovecoding.data.base;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.junit.Assert.fail;

public class EntityValidatorTest {

  @Rule
  public TestName name = new TestName();

  @Before
  public void setUp() {
  }

  @Test
  @Ignore
  public void testValidateEntity() {
    System.out.println(name.getMethodName());
    fail("Not tested yet!");
  }

}
