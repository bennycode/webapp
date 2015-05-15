package com.welovecoding.api.v1.video;

import com.welovecoding.data.video.VideoService;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static org.mockito.Mockito.validateMockitoUsage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class VideoResourceTest {

  @Configuration
  @EnableWebMvc
  @ComponentScan(value = "com.welovecoding.api.v1.video",
    excludeFilters = @Filter(type = FilterType.ANNOTATION, value = {Configuration.class}))
  @EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
  static class VideoResourceTestConfig extends WebMvcConfigurerAdapter {

    @Bean
    public VideoService videoService() {
      return Mockito.mock(VideoService.class);
    }
  }

  @Rule
  public TestName name = new TestName();

  private MockMvc mockMvc;

  @Autowired
  private VideoService videoService;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setUp() {
    Mockito.reset(videoService);
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @After
  public void tearDown() {
    validateMockitoUsage();
  }


}
