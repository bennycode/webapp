package com.welovecoding.data.account;

import com.welovecoding.data.base.BaseRepository;
import com.welovecoding.data.base.exception.NoEntityToDeleteFoundException;
import com.welovecoding.data.base.exception.NoEntityToUpdateFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

  @Rule
  public TestName name = new TestName();

  @Mock
  private AccountRepository accountRepository;

  @InjectMocks
  private AccountService accountService;

  protected AccountService getClassUnderTest() {
    return accountService;
  }

  private BaseRepository<Account, Long> getRepoMock() {
    return accountRepository;
  }

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @After
  public void tearDown() {
    validateMockitoUsage();
  }

  @Test
  public void testFindOne() {
    System.out.println(name.getMethodName());
    when(getRepoMock().findOne(Matchers.anyLong())).thenReturn(AccountFactory.constructAccount(1, 1));

    Account result = getClassUnderTest().findOne(1L);
    assertTrue("Result should not be null", result != null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFindOneWithNull() {
    System.out.println(name.getMethodName());
    when(getRepoMock().findOne(Matchers.eq((Long) null))).thenThrow(new IllegalArgumentException());

    getClassUnderTest().findOne(null);
  }

  @Test
  public void testFindOneWithNegative() {
    System.out.println(name.getMethodName());
    when(getRepoMock().findOne(Long.MIN_VALUE)).thenReturn(null);

    Account result = getClassUnderTest().findOne(Long.MIN_VALUE);
    assertTrue("Result should be null", result == null);
  }

  @Test
  public void testFindOneNonExistent() {
    System.out.println(name.getMethodName());
    when(getRepoMock().findOne(Long.MAX_VALUE)).thenReturn(null);

    Account result = getClassUnderTest().findOne(Long.MAX_VALUE);
    assertTrue("Result should be null", result == null);
  }

  @Test
  public void testFindAll() {
    System.out.println(name.getMethodName());
    when(getRepoMock().findAll()).thenReturn(new ArrayList<>(AccountFactory.constructAccountList(10, 1, 1)));

    Collection<Account> result = getClassUnderTest().findAll();
    assertTrue("ResultList should not be empty", !result.isEmpty());
  }

  @Test
  public void testSave() {
    System.out.println(name.getMethodName());
    Account fixture = AccountFactory.constructAccount(null, 1);
    Account fixtureWithId = AccountFactory.constructAccount(1, 1);
    when(getRepoMock().save(fixture)).thenReturn(fixtureWithId);

    Account result = getClassUnderTest().save(fixture);
    assertTrue(result.getId() == 1L);

    verify(getRepoMock()).save(any(Account.class));
  }

  @Test
  public void testUpdate() throws NoEntityToUpdateFoundException {
    System.out.println(name.getMethodName());
    Account fixtureWithId = AccountFactory.constructAccount(1, 1);
    when(getRepoMock().save(fixtureWithId)).thenReturn(fixtureWithId);
    when(getRepoMock().exists(fixtureWithId.getId())).thenReturn(true);

    Account result = getClassUnderTest().update(fixtureWithId);
    assertTrue(result.getId() == 1L);

    verify(getRepoMock()).exists(fixtureWithId.getId());
    verify(getRepoMock()).save(fixtureWithId);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUpdateWithNull() throws NoEntityToUpdateFoundException {
    System.out.println(name.getMethodName());
    Account fixture = AccountFactory.constructAccount(null, 1);

    getClassUnderTest().update(fixture);

    verify(getRepoMock()).exists(fixture.getId());
    verify(getRepoMock(), never()).save(any(Account.class));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUpdateWithNullId() throws NoEntityToUpdateFoundException {
    System.out.println(name.getMethodName());
    Account fixture = AccountFactory.constructAccount(null, 1);
    when(getRepoMock().exists(Matchers.eq(null))).thenThrow(new IllegalArgumentException());

    getClassUnderTest().update(fixture);

    verify(getRepoMock()).exists(fixture.getId());
    verify(getRepoMock(), never()).save(any(Account.class));
  }

  @Test(expected = NoEntityToUpdateFoundException.class)
  public void testUpdateNonExistent() throws NoEntityToUpdateFoundException {
    System.out.println(name.getMethodName());
    Account fixtureWithId = AccountFactory.constructAccount(1, 1);
    when(getRepoMock().exists(fixtureWithId.getId())).thenReturn(false);

    Account result = getClassUnderTest().update(fixtureWithId);
    assertTrue(result == null);

    verify(getRepoMock()).exists(fixtureWithId.getId());
    verify(getRepoMock(), never()).save(any(Account.class));
  }

  @Test
  public void testDelete() throws NoEntityToDeleteFoundException {
    System.out.println(name.getMethodName());
    when(getRepoMock().exists(1L)).thenReturn(true);

    getClassUnderTest().delete(1L);

    verify(getRepoMock()).exists(1L);
    verify(getRepoMock()).delete(anyLong());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteWithNull() throws NoEntityToDeleteFoundException {
    System.out.println(name.getMethodName());
    getClassUnderTest().delete(null);

    verify(getRepoMock(), never()).delete(anyLong());
  }

  @Test(expected = NoEntityToDeleteFoundException.class)
  public void testDeleteNonExistent() throws NoEntityToDeleteFoundException {
    System.out.println(name.getMethodName());
    when(getRepoMock().exists(1L)).thenReturn(false);

    getClassUnderTest().delete(1L);

    verify(getRepoMock()).exists(1L);
    verify(getRepoMock(), never()).delete(anyLong());
  }

}
