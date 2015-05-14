package com.welovecoding.api.v1.news;

import com.welovecoding.data.news.NewsService;
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

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class NewsResourceTest {

  @Configuration
  @EnableWebMvc
  @ComponentScan(value = "com.welovecoding.api.v1.news",
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Configuration.class}))
  @EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
  static class NewsResourceTestConfig extends WebMvcConfigurerAdapter {

    @Bean
    public NewsService newsService() {
      return Mockito.mock(NewsService.class);
    }
  }

  @Rule
  public TestName name = new TestName();

  private MockMvc mockMvc;

  @Autowired
  private NewsService newsService;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setUp() {

    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    Mockito.reset(newsService);
  }

  @After
  public void tearDown() {
    validateMockitoUsage();
  }

  @Test
  public void testFindOneNews() throws Exception {
    System.out.println(name.getMethodName());
    when(newsService.findOneBySlug(Matchers.anyString())).thenReturn(com.welovecoding.data.news.NewsFactory.constructNews(1, 2));

    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/news/slug1"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().contentType("application/hal+json"))
        //TODO Account json or URL
      .andExpect(jsonPath("slug", is("slug" + 1)))
      .andExpect(jsonPath("title", is("title" + 1)))
      .andExpect(jsonPath("description", is("description" + 1)))
      .andExpect(jsonPath("text", is("text" + 1)))
      .andReturn();

    String content = result.getResponse().getContentAsString();

    verify(newsService, times(1)).findOneBySlug(Matchers.anyString());
    verifyNoMoreInteractions(newsService);
  }

  @Test
  public void testFindAllNews() throws Exception {
    System.out.println(name.getMethodName());
    when(newsService.findAll()).thenReturn(new ArrayList<>(com.welovecoding.data.news.NewsFactory.constructNewsList(10, 1, 2)));

    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/news"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().contentType("application/hal+json"))
      .andExpect(jsonPath("_embedded.newsDTOList", hasSize(10)))
      .andExpect(jsonPath("_embedded.newsDTOList[0].slug", is("slug" + 1)))
      .andExpect(jsonPath("_embedded.newsDTOList[0].title", is("title" + 1)))
      .andExpect(jsonPath("_embedded.newsDTOList[0].description", is("description" + 1)))
      .andExpect(jsonPath("_embedded.newsDTOList[0].text", is("text" + 1)))
      .andExpect(jsonPath("_embedded.newsDTOList[1].slug", is("slug" + 2)))
      .andExpect(jsonPath("_embedded.newsDTOList[1].title", is("title" + 2)))
      .andExpect(jsonPath("_embedded.newsDTOList[1].description", is("description" + 2)))
      .andExpect(jsonPath("_embedded.newsDTOList[1].text", is("text" + 2)))
      .andReturn();

    String content = result.getResponse().getContentAsString();

    verify(newsService, times(1)).findAll();
    verifyNoMoreInteractions(newsService);
  }

  @Test
  public void testCreateNews() {
    System.out.println(name.getMethodName());
    fail("Not tested yet!");
  }

  @Test
  public void testUpdateNews() {
    System.out.println(name.getMethodName());
    fail("Not tested yet!");
  }

  @Test
  public void testDeleteNews() {
    System.out.println(name.getMethodName());
    fail("Not tested yet!");
  }

}
