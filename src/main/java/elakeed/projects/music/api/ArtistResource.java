package elakeed.projects.music.api;

import elakeed.projects.music.model.Album;
import elakeed.projects.music.model.Artist;
import elakeed.projects.music.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping(path = "/api/artists")
@CrossOrigin(origins = "http://localhost:3001")
public class ArtistResource {

    private ArtistService artistService;

    @Autowired
    public ArtistResource(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public ResponseEntity<Set<Artist>> getAllArtists() {
        return ResponseEntity.ok(artistService.getAllArtists().stream().collect(Collectors.toSet()));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable("id") Long id) {
        Artist fetchedArtist = artistService.getArtistById(id);
        if (fetchedArtist != null) {
            return ResponseEntity.ok(fetchedArtist);
        }
        return ResponseEntity.notFound().header("Message", "No artist with Id " + id).build();
    }

    @GetMapping(path = "/{id}/albums")
    public ResponseEntity<List<Album>> getAlbumsForArtistById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(artistService.getArtistById(id).getAlbums().stream().collect(Collectors.toList()));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteArtistById(@PathVariable("id") Long id) {
        Artist fetchedArtist = artistService.getArtistById(id);
        if (fetchedArtist != null) {
            if (fetchedArtist.getAlbums().size() == 0) {
                artistService.deleteArtist(id);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().header("Message", "Can't delete this record because some albums depends on it").build();
        }
        return ResponseEntity.notFound().header("Message", "No artist with Id " + id).build();
    }

    @PostMapping
    public ResponseEntity<Artist> saveArtist(@RequestBody Artist artist) {
        Artist savedArtist = artistService.saveArtist(artist);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedArtist.getArtistId()).toUri();
        return ResponseEntity.created(location).body(savedArtist);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable("id") Long id, @RequestBody Artist artist) {
        Artist fetchedArtist = artistService.getArtistById(id);
        if (fetchedArtist != null) {
            fetchedArtist.setArtistName(artist.getArtistName());
            artistService.saveArtist(fetchedArtist);
            return ResponseEntity.ok(fetchedArtist);
        }
        return ResponseEntity.badRequest().header("Message", "No artist with Id " + id).build();
    }
}
