package com.welovecoding.data.news;


import com.welovecoding.data.base.exception.NoEntityToDeleteFoundException;
import com.welovecoding.data.base.exception.NoEntityToUpdateFoundException;
import org.junit.*;
import org.junit.rules.TestName;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class NewsServiceTest {

  @Rule
  public TestName name = new TestName();

  @Mock
  private NewsRepository newsRepository;

  @InjectMocks
  private NewsService newsService;

  private NewsService getClassUnderTest() {
    return newsService;
  }

  private NewsRepository getRepoMock() {
    return newsRepository;
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
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
    when(getRepoMock().findOne(Matchers.anyLong())).thenReturn(NewsFactory.constructNews(1, 1));

    News result = getClassUnderTest().findOne(1L);
    assertTrue("Result is null", result != null);
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

    News result = getClassUnderTest().findOne(Long.MIN_VALUE);
    assertTrue("Result is not null", result == null);
  }

  @Test
  public void testFindOneNonExistent() {
    System.out.println(name.getMethodName());
    when(getRepoMock().findOne(Long.MAX_VALUE)).thenReturn(null);

    News result = getClassUnderTest().findOne(Long.MAX_VALUE);
    assertTrue("Result is not null", result == null);
  }

  @Test
  public void testFindOneBySlug() {
    System.out.println(name.getMethodName());
    when(getRepoMock().findOneBySlug("slug1")).thenReturn(NewsFactory.constructNews(1, 1));

    News result = getClassUnderTest().findOneBySlug("slug1");
    assertTrue("Result is null", result != null);
  }

  @Test
  public void testFindOneBySlugWithNull() {
    System.out.println(name.getMethodName());
    fail("Not tested yet!");
  }

  @Test
  public void testFindOneBySlugNonExistent() {
    System.out.println(name.getMethodName());
    fail("Not tested yet!");
  }

  @Test
  public void testFindAll() {
    System.out.println(name.getMethodName());
    when(getRepoMock().findAll()).thenReturn(new ArrayList<>(NewsFactory.constructNewsList(10, 1, 1)));

    Collection<News> result = getClassUnderTest().findAll();
    assertTrue("ResultList is empty", !result.isEmpty());
  }

  @Test
  public void testSave() {
    System.out.println(name.getMethodName());
    News fixture = NewsFactory.constructNews(null, 2);
    News fixtureWithId = NewsFactory.constructNews(1, 2);
    when(getRepoMock().save(fixture)).thenReturn(fixtureWithId);

    News result = getClassUnderTest().save(fixture);

    verify(getRepoMock()).save(any(News.class));
    assertTrue(result.getId() == 1L);
  }

  @Test
  public void testUpdate() throws NoEntityToUpdateFoundException {
    System.out.println(name.getMethodName());
    News fixtureWithId = NewsFactory.constructNews(1, 2);
    when(getRepoMock().findOne(fixtureWithId.getId())).thenReturn(fixtureWithId);
    when(getRepoMock().save(fixtureWithId)).thenReturn(fixtureWithId);

    News result = getClassUnderTest().update(fixtureWithId);

    verify(getRepoMock()).save(fixtureWithId);
    assertTrue(result.getId() == 1L);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUpdateWithNull() throws NoEntityToUpdateFoundException {
    System.out.println(name.getMethodName());
    News fixture = null;

    getClassUnderTest().update(fixture);

    verify(getRepoMock(), never()).save(any(News.class));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUpdateWithNullId() throws NoEntityToUpdateFoundException {
    System.out.println(name.getMethodName());
    News fixture = NewsFactory.constructNews(null, 1);

    getClassUnderTest().update(fixture);

    verify(getRepoMock(), never()).save(any(News.class));
  }

  @Test(expected = NoEntityToUpdateFoundException.class)
  public void testUpdateNonExistent() throws NoEntityToUpdateFoundException {
    System.out.println(name.getMethodName());
    News fixtureWithId = NewsFactory.constructNews(1, 2);

    News result = getClassUnderTest().update(fixtureWithId);

    verify(getRepoMock(), never()).save(any(News.class));
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
