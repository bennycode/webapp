package com.welovecoding.api.v1.category;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.welovecoding.IntegrationTestConfiguration;
import com.welovecoding.SchemaDumper;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {
  IntegrationTestConfiguration.class})
@WebAppConfiguration
@TestExecutionListeners({
  DependencyInjectionTestExecutionListener.class,
  DbUnitTestExecutionListener.class})
public class CategoryResourceIT {

  private static final Logger LOG = LoggerFactory.getLogger(CategoryResourceIT.class.getName());

  @Rule
  public TestName name = new TestName();

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;


  @Before
  public void setUp() {
    DatabaseDataSourceConnection datasource = (DatabaseDataSourceConnection) webApplicationContext.getBean("dbUnitDatabaseConnection");
    try {
      SchemaDumper.dumpSchema("testdb", datasource.getConnection());
    } catch (Exception ex) {
      LOG.error(null, ex);
    }
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @After
  public void tearDown() {
  }

  @Test
  @DatabaseSetup(value = "insert.xml")
  public void testFindAllCategories() throws Exception {
    System.out.println(name.getMethodName());
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().contentType("application/json;charset=UTF-8"))
      .andExpect(jsonPath("$", hasSize(5)))
      .andReturn();

    String content = result.getResponse().getContentAsString();
  }

  @Test
  @DatabaseSetup(value = "insert.xml")
  public void testFindAllCategoriesSorted() throws Exception {
    System.out.println(name.getMethodName());
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories?direction=DESC&attribute=title"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().contentType("application/json;charset=UTF-8"))
      .andExpect(jsonPath("$", hasSize(5)))
      .andReturn();

    String content = result.getResponse().getContentAsString();
  }

}
