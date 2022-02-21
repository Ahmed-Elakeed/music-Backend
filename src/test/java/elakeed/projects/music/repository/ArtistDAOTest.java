package elakeed.projects.music.repository;


import elakeed.projects.music.MusicApplication;
import elakeed.projects.music.model.Artist;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MusicApplication.class)
@DataJpaTest
public class ArtistDAOTest {

    @Autowired
    private ArtistDAO artistDAO;

    @Before
    public void addSampleData(){
        artistDAO.save(new Artist("Wegz"));
        artistDAO.save(new Artist("Pablo"));
        artistDAO.save(new Artist("Mekky"));
    }
    @After
    public void removeSampleData(){
        artistDAO.deleteAll();
    }

    @Test
    public void saveArtist(){
        Artist artist=new Artist("newExample");
        Artist savedArtist=artistDAO.save(artist);
        System.out.println(savedArtist);
        assertNotNull(savedArtist);
        assertEquals(artist.getArtistName(),savedArtist.getArtistName());
    }

    @Test
    public void getAll(){
        assertEquals(3,artistDAO.findAll().size());
    }


}
