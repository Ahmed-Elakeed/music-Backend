package elakeed.projects.music.repository;

import elakeed.projects.music.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumDAO extends JpaRepository<Album,Long> {
}
