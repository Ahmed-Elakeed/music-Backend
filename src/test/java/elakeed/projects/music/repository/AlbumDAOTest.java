package elakeed.projects.music.repository;

import elakeed.projects.music.MusicApplication;
import elakeed.projects.music.model.Album;
import elakeed.projects.music.model.Artist;
import elakeed.projects.music.model.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MusicApplication.class)
@DataJpaTest
public class AlbumDAOTest {

    @Autowired
    private AlbumDAO albumDAO;

    @Autowired
    private GenreDAO genreDAO;

    @Autowired
    private ArtistDAO artistDAO;

    @Test
    public void saveAlbum(){
        Artist savedArtist=artistDAO.save(new Artist("Drake"));
        Genre savedGenre=genreDAO.save(new Genre("Rap"));
        Artist artist=artistDAO.findById(savedArtist.getArtistId()).orElse(null);
        Genre genre=genreDAO.findById(savedGenre.getGenreId()).orElse(null);
        Album savedAlbum=albumDAO.save(new Album("adad","17-5-1893",artist,genre));
        assertEquals("adad",albumDAO.findAll().get(0).getAlbumName());
        assertEquals(savedAlbum.getAlbumId(),albumDAO.findAll().get(0).getAlbumId());

    }
    @Test
    public void saveWrongDataAlbum(){
        Artist artist=artistDAO.findById(100L).orElse(null);
        Genre genre=genreDAO.findById(100L).orElse(null);
        assertThrows(ConstraintViolationException.class,()->{
            albumDAO.save(new Album("adad","17-5-1893",artist,genre));
        });
    }
}
