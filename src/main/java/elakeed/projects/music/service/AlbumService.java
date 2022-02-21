package elakeed.projects.music.service;

import elakeed.projects.music.model.Album;
import elakeed.projects.music.repository.AlbumDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private AlbumDAO albumDAO;

    @Autowired
    public AlbumService(AlbumDAO albumDAO) {
        this.albumDAO = albumDAO;
    }

    public List<Album> getAllAlbums() {
        return albumDAO.findAll();
    }

    public Album getAlbumById(Long id) {
        return albumDAO.findById(id).orElse(null);
    }

    public Album saveAlbum(Album album) {
        return albumDAO.save(album);
    }

    public Album updateAlbum(Long id, Album album) {
        album.setAlbumId(id);
        return albumDAO.save(album);
    }

    public void deleteAlbum(Long id) {
        albumDAO.deleteById(id);
    }
}
