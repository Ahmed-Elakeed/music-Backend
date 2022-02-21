package elakeed.projects.music.api;

import elakeed.projects.music.model.Album;
import elakeed.projects.music.service.AlbumService;
import elakeed.projects.music.service.ArtistService;
import elakeed.projects.music.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/albums")
@CrossOrigin("*")
public class AlbumResource {
    private AlbumService albumService;
    private ArtistService artistService;
    private GenreService genreService;

    @Autowired
    public AlbumResource(AlbumService albumService, ArtistService artistService, GenreService genreService) {
        this.albumService = albumService;
        this.artistService = artistService;
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<Set<Album>> getAllAlbums() {
        return ResponseEntity.ok(albumService.getAllAlbums().stream().collect(Collectors.toSet()));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable("id") Long id) {
        Album fetchedAlbum = albumService.getAlbumById(id);
        if (fetchedAlbum != null) {
            return ResponseEntity.ok(fetchedAlbum);
        }
        return ResponseEntity.notFound().header("Message", "No album with Id " + id).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteAlbumById(@PathVariable("id") Long id) {
        Album fetchedAlbum = albumService.getAlbumById(id);
        if (fetchedAlbum != null) {
            albumService.deleteAlbum(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().header("Message", "No album with Id " + id).build();
    }

    @PostMapping
    public ResponseEntity<Album> saveAlbum(@RequestBody Album album) {
        if (artistService.getArtistById(album.getArtist().getArtistId()) == null
                || genreService.getGenreById(album.getGenre().getGenreId()) == null) {
            return ResponseEntity.badRequest().header("Message", "Wrong artist or genre data").build();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(albumService.saveAlbum(album).getAlbumId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable("id") Long id, @RequestBody Album album) {
        Album fetchedAlbum = albumService.getAlbumById(id);
        if (fetchedAlbum != null) {
            if (artistService.getArtistById(album.getArtist().getArtistId()) == null
                    || genreService.getGenreById(album.getGenre().getGenreId()) == null) {
                return ResponseEntity.badRequest().header("Message", "Wrong artist or genre data").build();
            }
            album.setAlbumId(id);
            albumService.saveAlbum(album);
            return ResponseEntity.ok(album);
        }
        return ResponseEntity.notFound().header("Message", "No album with Id " + id).build();
    }
}
