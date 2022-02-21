package elakeed.projects.music.service;

import elakeed.projects.music.model.Artist;
import elakeed.projects.music.repository.ArtistDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {
    private ArtistDAO artistDAO;

    @Autowired
    public ArtistService(ArtistDAO artistDAO) {
        this.artistDAO = artistDAO;
    }

    public List<Artist> getAllArtists() {
        return artistDAO.findAll();
    }

    public Artist getArtistById(Long id) {
        return artistDAO.findById(id).orElse(null);
    }

    public Artist saveArtist(Artist artist) {
        return artistDAO.save(artist);
    }

    public Artist updateArtist(Long id, Artist artist) {
        artist.setArtistId(id);
        return artistDAO.save(artist);
    }

    public void deleteArtist(Long id) {
        artistDAO.deleteById(id);
    }
}
