package elakeed.projects.music.repository;

import elakeed.projects.music.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistDAO extends JpaRepository<Artist,Long> {
    Artist findArtistByArtistName(String artistName);
}
