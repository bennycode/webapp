package com.welovecoding.api.v1.video;


import com.welovecoding.api.v1.news.NewsDTO;
import com.welovecoding.data.video.VideoFactory;
import com.welovecoding.data.video.entity.Video;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class VideoMapperTest {

  @Rule
  public TestName name = new TestName();

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void shouldReturnNullWhenEntityIsNull() {
    System.out.println(name.getMethodName());

    VideoDTO result = VideoMapper.entityToDto(null, 1);

    assertThat(null, equalTo(result));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenDeptIsNegative() {
    System.out.println(name.getMethodName());
    Video entity = VideoFactory.constructVideo(1, 2);

    VideoDTO result = VideoMapper.entityToDto(entity, -1);
  }

  @Test
  public void shouldReturnNullWhenDeptIsZero() {
    System.out.println(name.getMethodName());
    Video entity = VideoFactory.constructVideo(1, 1);

    VideoDTO result = VideoMapper.entityToDto(entity, 0);

    assertThat(result, nullValue());
  }

  @Test
  public void shouldReturnFlatVideoWhenDeptIsOne() {
    System.out.println(name.getMethodName());
    int resultDept = 1;
    Video entity = VideoFactory.constructVideo(1, 3);
    VideoDTO expResult = com.welovecoding.api.v1.video.VideoDTOFactory.constructVideoDTO(1, resultDept);

    VideoDTO result = VideoMapper.entityToDto(entity, resultDept);

    assertThat(expResult, samePropertyValuesAs(result));
    assertThat(result.getNews(), emptyCollectionOf(NewsDTO.class));
  }

  @Test
  public void shouldReturnVideoWithNewsWhenDeptIsTwo() {
    System.out.println(name.getMethodName());
    int resultDept = 2;
    Video entity = VideoFactory.constructVideo(1, 3);
    VideoDTO expResult = com.welovecoding.api.v1.video.VideoDTOFactory.constructVideoDTO(1, resultDept);

    VideoDTO result = VideoMapper.entityToDto(entity, resultDept);

    assertThat(expResult, samePropertyValuesAs(result));
    assertThat(result.getNews(), hasItem(instanceOf(NewsDTO.class)));
    assertThat(new ArrayList<>(result.getNews()).get(0).getVideo(), nullValue());
  }

  @Test
  public void testEntityToDtoWithNullNews() {
    System.out.println(name.getMethodName());

    Video entity = VideoFactory.constructVideoWithNullNews(1);
    VideoDTO expResult = VideoDTOFactory.constructVideoDTO(1, 1);

    VideoDTO result = VideoMapper.entityToDto(entity, 2);

    assertThat(expResult, samePropertyValuesAs(result));
  }
}
