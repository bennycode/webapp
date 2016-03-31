package com.welovecoding.api.v1.category;

import com.welovecoding.data.category.entity.Category;
import com.welovecoding.data.category.entity.CategoryFactory;
import com.welovecoding.data.category.service.CategoryService;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import static org.hamcrest.Matchers.hasSize;
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
    public void testFindAllCategories() throws Exception {
        System.out.println(name.getMethodName());
        Page<Category> pageMock = Mockito.mock(Page.class);
        when(pageMock.getContent()).thenReturn(CategoryFactory.constructList(10, 1, 1));
        when(categoryService.findAllAndSortBy(Sort.Direction.ASC, "id")).thenReturn(pageMock);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(10)))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        verify(categoryService, times(1)).findAllAndSortBy(Sort.Direction.ASC, "id");
        verifyNoMoreInteractions(categoryService);
    }

    @Test
    public void testFindAllCategoriesSorted() throws Exception {
        System.out.println(name.getMethodName());
        Page<Category> pageMock = Mockito.mock(Page.class);

        LinkedList<Category> categories = new LinkedList<>(CategoryFactory.constructList(10, 1, 1));
        Collections.sort(categories);
        Collections.sort(categories, new Comparator<Category>() {
            @Override
            public int compare(Category o1, Category o2) {
                return o2.compareTo(o1);
            }
        });
        when(pageMock.getContent()).thenReturn(categories);
        when(categoryService.findAllAndSortBy(Sort.Direction.DESC, "title")).thenReturn(pageMock);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories?direction=DESC&attribute=title"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(10)))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        verify(categoryService, times(1)).findAllAndSortBy(Sort.Direction.DESC, "title");
        verifyNoMoreInteractions(categoryService);
    }
}
