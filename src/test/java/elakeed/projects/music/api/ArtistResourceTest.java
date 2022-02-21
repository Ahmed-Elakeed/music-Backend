package elakeed.projects.music.api;


import elakeed.projects.music.MusicApplication;
import elakeed.projects.music.model.Artist;
import elakeed.projects.music.repository.ArtistDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.util.*;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = MusicApplication.class)
@WebMvcTest(ArtistResource.class)
public class ArtistResourceTest {

    @InjectMocks
    private ArtistResource artistResource;

    @Mock
    private ArtistDAO artistDAO;

    @Test
    public void getAllArtistsTest() {
        List<Artist> artistList = new ArrayList<>(Arrays.asList(new Artist("Ahmed"), new Artist("Zakaria"), new Artist("Elakeed")));
        when(artistDAO.findAll()).thenReturn(artistList);

        ResponseEntity<Set<Artist>> listResponseEntity = artistResource.getAllArtists();
        assertEquals(artistList.size(), listResponseEntity.getBody().size());
        assertEquals(artistList.get(0).getArtistName(),listResponseEntity.getBody().stream().collect(Collectors.toList()).get(0).getArtistName());
    }

    @Test
    public void addArtistTest(){
        MockHttpServletRequest mockHttpServletRequest=new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));

        Artist artist=new Artist("art test");
        artist.setArtistId(100L);
        when(artistDAO.save(any(Artist.class))).thenReturn(artist);


        ResponseEntity<Artist> artistResponseEntity=artistResource.saveArtist(artist);

        assertEquals(201,artistResponseEntity.getStatusCodeValue());
        assertEquals("/100",artistResponseEntity.getHeaders().getLocation().getPath());
    }

    @Test
    public void deleteEndPointTest(){
        Artist artist=new Artist("arrr");
        artist.setArtistId(1L);
        artistResource.deleteArtistById(artist.getArtistId());
        verify(artistDAO,times(1)).deleteById(artist.getArtistId());
    }
}
