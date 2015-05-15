package com.welovecoding.api.v1.video;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.welovecoding.IntegrationTestConfiguration;
import com.welovecoding.SchemaDumper;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {
  IntegrationTestConfiguration.class})
@WebAppConfiguration
@TestExecutionListeners({
  DependencyInjectionTestExecutionListener.class,
  DbUnitTestExecutionListener.class})
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class VideoResourceIT {

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
    } catch (SQLException ex) {
      Logger.getLogger(com.welovecoding.data.account.AccountServiceIT.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
      Logger.getLogger(com.welovecoding.data.account.AccountServiceIT.class.getName()).log(Level.SEVERE, null, ex);
    }
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @After
  public void tearDown() {
  }


}
