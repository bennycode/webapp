package com.welovecoding.api.v1.category;

import com.welovecoding.data.account.AccountFactory;
import com.welovecoding.data.category.CategoryFactory;
import com.welovecoding.data.category.CategoryService;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class CategoryResourceTest {

  @Configuration
  @EnableWebMvc
  @ComponentScan(value = "com.welovecoding.api.v1.category",
    excludeFilters = @Filter(type = FilterType.ANNOTATION, value = {Configuration.class}))
  @EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
  static class CategoryResourceTestConfig extends WebMvcConfigurerAdapter {

    @Bean
    public CategoryService categoryService() {
      return Mockito.mock(CategoryService.class);
    }
  }

  @Rule
  public TestName name = new TestName();

  private MockMvc mockMvc;

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setUp() {
    Mockito.reset(categoryService);
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @After
  public void tearDown() {
    validateMockitoUsage();
  }

  @Test
  public void testFindAccountByUsername() throws Exception {
    System.out.println(name.getMethodName());
    when(categoryService.findOneByUsername(Matchers.anyString())).thenReturn(AccountFactory.constructAccount(1, 1));

    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/category/username1"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().contentType("application/hal+json"))
      .andExpect(jsonPath("username", is("username" + 1)))
      .andReturn();

    String content = result.getResponse().getContentAsString();

    verify(categoryService, times(1)).findOneByUsername(Matchers.anyString());
    verifyNoMoreInteractions(categoryService);
  }

  @Test
  public void testFindAllCategoriesOrderedByName() throws Exception {
    System.out.println(name.getMethodName());
    when(categoryService.findAllOrderedByName()).thenReturn(CategoryFactory.constructCategory(1, 1));

    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/category/username1"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().contentType("application/hal+json"))
      .andExpect(jsonPath("username", is("username" + 1)))
      .andReturn();

    String content = result.getResponse().getContentAsString();

    verify(categoryService, times(1)).findOneByUsername(Matchers.anyString());
    verifyNoMoreInteractions(categoryService);
  }
}
