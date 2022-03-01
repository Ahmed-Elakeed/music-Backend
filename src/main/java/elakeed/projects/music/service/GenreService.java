package elakeed.projects.music.service;

import elakeed.projects.music.model.Genre;
import elakeed.projects.music.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.getAllGenres();
    }

    public Genre getGenreById(Long id) {
        return genreRepository.getGenreById(id);
    }

    public Genre saveGenre(Genre genre) {
        return genreRepository.saveGenre(genre);
    }

    public Genre updateGenre(Long id, Genre genre) {
        genre.setGenreId(id);
        return genreRepository.saveGenre(genre);
    }

    public void deleteGenre(Long id) {
        genreRepository.deleteGenreById(id);
    }
}
