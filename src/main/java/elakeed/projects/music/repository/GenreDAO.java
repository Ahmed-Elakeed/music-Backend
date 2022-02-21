package elakeed.projects.music.repository;

import elakeed.projects.music.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreDAO extends JpaRepository<Genre,Long> {
    Genre findGenreByGenreName(String genreName);
}
