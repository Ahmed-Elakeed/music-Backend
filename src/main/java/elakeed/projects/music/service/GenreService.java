package elakeed.projects.music.service;

import elakeed.projects.music.model.Genre;
import elakeed.projects.music.repository.GenreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private GenreDAO genreDAO;

    @Autowired
    public GenreService(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }

    public List<Genre> getAllGenres() {
        return genreDAO.findAll();
    }

    public Genre getGenreById(Long id) {
        return genreDAO.findById(id).orElse(null);
    }

    public Genre saveGenre(Genre genre) {
        return genreDAO.save(genre);
    }

    public Genre updateGenre(Long id, Genre genre) {
        genre.setGenreId(id);
        return genreDAO.save(genre);
    }

    public void deleteGenre(Long id) {
        genreDAO.deleteById(id);
    }
}
