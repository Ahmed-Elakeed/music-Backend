package elakeed.projects.music.api;

import elakeed.projects.music.MusicApplication;
import elakeed.projects.music.model.Genre;
import elakeed.projects.music.service.GenreService;
import org.junit.Test;


import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = MusicApplication.class)
public class GenreResourceTest {

    @InjectMocks
    private GenreResource genreResource;

    @Mock
    private GenreService genreService;

    @Test
    public void contextLoads() throws Exception {
        assertNotNull(genreService);
        assertNotNull(genreResource);
    }

    @Test
    public void getAllGenres_success() throws Exception {
        Genre genre = new Genre("Ahmed");
        genre.setGenreId(1L);
        Genre genre1 = new Genre("Elakeed");
        genre1.setGenreId(2L);
        List<Genre> genres = new ArrayList<>(Arrays.asList(genre,genre1));

        when(genreService.getAllGenres()).thenReturn(genres);

        assertEquals(genreService.getAllGenres().stream().collect(Collectors.toSet()), genreResource.getAllGenres().getBody());
        assertEquals(genreService.getAllGenres().size(),genreResource.getAllGenres().getBody().size());
    }
    @Test
    public void getGenreByIdTest(){
        Genre genre=new Genre("Genre");
        genre.setGenreId(100L);
        when(genreService.getGenreById(genre.getGenreId())).thenReturn(genre);

        Genre genre1=new Genre("Genre11");
        genre1.setGenreId(200L);
        when(genreService.getGenreById(genre1.getGenreId())).thenReturn(genre1);

        assertEquals(genre,genreResource.getGenreById(300L).getBody());
    }
    @Test
    public void saveGenreTest(){
        MockHttpServletRequest mockHttpServletRequest=new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));

        Genre genre=new Genre("Genre");
        genre.setGenreId(1L);

        when(genreService.saveGenre(genre)).thenReturn(genre);

        assertEquals("/"+genre.getGenreId().toString(),genreResource.saveGenre(genre).getHeaders().getLocation().getPath());
    }

}
