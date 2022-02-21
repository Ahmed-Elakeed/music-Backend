package elakeed.projects.music.repository;


import elakeed.projects.music.MusicApplication;
import elakeed.projects.music.model.Genre;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MusicApplication.class)
@DataJpaTest
public class GenreDAOTest {

    @Autowired
    private GenreDAO genreDAO;


    @Test
    public void saveGenre() {
        Genre genre = new Genre("Hip hop");
        genreDAO.save(genre);
        List<Genre> genreList= genreDAO.findAll();
        Assert.assertEquals(genre.getGenreName(), genreList.get(0).getGenreName());
    }

    @Test
    public void deleteGenre() {
        Genre savedGenre = genreDAO.save(new Genre("Rap"));
        assertNotNull(genreDAO.findById(savedGenre.getGenreId()).orElse(null));
        genreDAO.deleteById(savedGenre.getGenreId());
        assertNull(genreDAO.findById(savedGenre.getGenreId()).orElse(null));
    }
}
