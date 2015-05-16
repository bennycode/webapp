package com.welovecoding.api.v1.playlist;


import com.welovecoding.data.playlist.Playlist;
import com.welovecoding.data.playlist.PlaylistFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class PlaylistMapperTest {

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

    PlaylistDTO result = PlaylistMapper.entityToDto(null, 1);

    assertThat(null, equalTo(result));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenDeptIsNegative() {
    System.out.println(name.getMethodName());
    Playlist entity = PlaylistFactory.constructPlaylist(1, 2);

    PlaylistDTO result = PlaylistMapper.entityToDto(entity, -1);
  }

  @Test
  public void shouldReturnNullWhenDeptIsZero() {
    System.out.println(name.getMethodName());
    Playlist entity = PlaylistFactory.constructPlaylist(1, 1);

    PlaylistDTO result = PlaylistMapper.entityToDto(entity, 0);

    assertThat(result, nullValue());
  }

  @Test
  public void shouldReturnFlatPlaylistWhenDeptIsOne() {
    System.out.println(name.getMethodName());
    int resultDept = 1;
    Playlist entity = PlaylistFactory.constructPlaylist(1, 3);
    PlaylistDTO expResult = PlaylistDTOFactory.constructPlaylistDTO(1, 10, resultDept);

    PlaylistDTO result = PlaylistMapper.entityToDto(entity, resultDept);

    assertThat(expResult, samePropertyValuesAs(result));
//    assertThat(result.getNews(), emptyCollectionOf(NewsDTO.class));
  }

  @Test
  public void shouldReturnPlaylistWithNewsWhenDeptIsTwo() {
    System.out.println(name.getMethodName());
    int resultDept = 2;
    Playlist entity = PlaylistFactory.constructPlaylist(1, 3);
    PlaylistDTO expResult = PlaylistDTOFactory.constructPlaylistDTO(1, 10, resultDept);

    PlaylistDTO result = PlaylistMapper.entityToDto(entity, resultDept);

    assertThat(expResult, samePropertyValuesAs(result));
//    assertThat(result.getNews(), hasItem(instanceOf(NewsDTO.class)));
//    assertThat(new ArrayList<>(result.getNews()).get(0).getPlaylist(), nullValue());
  }

  @Test
  public void testEntityToDtoWithNullNews() {
    System.out.println(name.getMethodName());

    Playlist entity = PlaylistFactory.constructPlaylistWithNullValues(1);
    PlaylistDTO expResult = PlaylistDTOFactory.constructPlaylistDTO(1, 10, 1);

    PlaylistDTO result = PlaylistMapper.entityToDto(entity, 2);

    assertThat(expResult, samePropertyValuesAs(result));
  }
}
