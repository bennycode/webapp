package com.welovecoding.data.account;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.welovecoding.DbTestUtil;
import com.welovecoding.IntegrationTestConfiguration;
import com.welovecoding.data.base.exception.NoEntityToDeleteFoundException;
import com.welovecoding.data.base.exception.NoEntityToUpdateFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLException;
import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {
  IntegrationTestConfiguration.class})
@WebAppConfiguration
@TestExecutionListeners({
  DependencyInjectionTestExecutionListener.class,
  DbUnitTestExecutionListener.class})
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AccountServiceIT {

  @Rule
  public TestName name = new TestName();

  @Autowired
  private AccountService accountService;

  @Autowired
  private ApplicationContext applicationContext;

  private AccountService getClassUnderTest() {
    return accountService;
  }

  @Before
  public void setUp() {

  }

  @After
  public void tearDown() throws SQLException {
    DbTestUtil.resetAutoIncrementColumns(applicationContext, "account");
    DbTestUtil.resetAutoIncrementColumns(applicationContext, "news");
  }

  @Test
  @DatabaseSetup(value = "testFindOneOrAll.xml")
  public void testFindOne() {
    System.out.println(name.getMethodName());
    Account result = getClassUnderTest().findOne(1L);
    assertTrue("Result should not be null", result != null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFindOneWithNull() {
    System.out.println(name.getMethodName());
    getClassUnderTest().findOne(null);
  }

  @Test
  public void testFindOneWithNegative() {
    System.out.println(name.getMethodName());
    Account result = getClassUnderTest().findOne(Long.MIN_VALUE);
    assertTrue("Result should be null", result == null);
  }

  @Test
  @DatabaseSetup(value = "testFindOneOrAll.xml")
  public void testFindOneNonExistent() {
    System.out.println(name.getMethodName());
    Account result = getClassUnderTest().findOne(Long.MAX_VALUE);
    assertTrue("Result should be null", result == null);
  }

  @Test
  @DatabaseSetup(value = "testFindOneOrAll.xml")
  public void testFindAll() {
    System.out.println(name.getMethodName());
    Collection<Account> result = getClassUnderTest().findAll();
    assertTrue("ResultList should not be empty", !result.isEmpty());
  }

  @Test
  @DatabaseSetup(value = "empty.xml")
  public void testFindAllEmptyList() {
    System.out.println(name.getMethodName());
    Collection<Account> result = getClassUnderTest().findAll();
    assertNotNull("ResultList should not be null", result);
    assertTrue("ResultList should be empty", result.isEmpty());
  }

  @Test
  @DatabaseSetup(value = "empty.xml")
  @ExpectedDatabase(value = "testSave.xml")
  public void testSave() {
    System.out.println(name.getMethodName());
    Account fixture = AccountFactory.constructAccount(null, 1);

    Account result = getClassUnderTest().save(fixture);
    assertNotNull("ID should be set", result.getId());
    assertTrue(result.getId() == 1L);
  }

  @Test
  @DatabaseSetup(value = "testFindOneOrAll.xml")
  @ExpectedDatabase(value = "testUpdate.xml")
  public void testUpdate() throws NoEntityToUpdateFoundException {
    System.out.println(name.getMethodName());
    Account fixtureWithId = AccountFactory.constructAccount(1, 1);

    Account result = getClassUnderTest().update(fixtureWithId);
    assertTrue(result.getId() == 1L);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUpdateWithNull() throws NoEntityToUpdateFoundException {
    System.out.println(name.getMethodName());
    Account fixture = AccountFactory.constructAccount(null, 1);

    getClassUnderTest().update(fixture);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUpdateWithNullId() throws NoEntityToUpdateFoundException {
    System.out.println(name.getMethodName());
    Account fixture = AccountFactory.constructAccount(null, 1);

    getClassUnderTest().update(fixture);
  }

  @Test(expected = NoEntityToUpdateFoundException.class)
  public void testUpdateNonExistent() throws NoEntityToUpdateFoundException {
    System.out.println(name.getMethodName());
    Account fixtureWithId = AccountFactory.constructAccount(2, 1);

    Account result = getClassUnderTest().update(fixtureWithId);
    assertTrue(result == null);
  }

  @Test
  @DatabaseSetup(value = "testFindOneOrAll.xml")
  @ExpectedDatabase(value = "empty.xml")
  public void testDelete() throws NoEntityToDeleteFoundException {
    System.out.println(name.getMethodName());
    getClassUnderTest().delete(1L);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteWithNull() throws NoEntityToDeleteFoundException {
    System.out.println(name.getMethodName());
    getClassUnderTest().delete(null);
  }

  @Test(expected = NoEntityToDeleteFoundException.class)
  public void testDeleteNonExistent() throws NoEntityToDeleteFoundException {
    System.out.println(name.getMethodName());
    getClassUnderTest().delete(2L);
  }

}
