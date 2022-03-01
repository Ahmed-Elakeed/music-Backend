package elakeed.projects.music.api;

import elakeed.projects.music.aspect.TrackTime;
import elakeed.projects.music.model.Album;
import elakeed.projects.music.model.Genre;
import elakeed.projects.music.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/genres")
@CrossOrigin("*")
public class GenreResource {
    private GenreService genreService;

    @Autowired
    public GenreResource(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    @TrackTime
    public ResponseEntity<Set<Genre>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres().stream().collect(Collectors.toSet()));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable("id") Long id) {
        Genre fetchedGenre = genreService.getGenreById(id);
        if (fetchedGenre != null) {
            return ResponseEntity.ok(fetchedGenre);
        }
        return ResponseEntity.notFound().header("Message", "No genre with Id " + id).build();
    }

    @GetMapping(path = "/{id}/albums")
    @Transactional
    public List<Album> getAlbumsForGenreById(@PathVariable("id") Long id) {
        return genreService.getGenreById(id).getAlbums().stream().collect(Collectors.toList());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteGenreById(@PathVariable("id") Long id) {
        Genre fetchedGenre = genreService.getGenreById(id);
        if (fetchedGenre != null) {
            if (fetchedGenre.getAlbums().size() == 0) {
                genreService.deleteGenre(id);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().body("Can't delete this record because some albums depends on it");
        }
        return ResponseEntity.notFound().header("Message", "No genre with Id " + id).build();
    }

    @PostMapping
    public ResponseEntity<Genre> saveGenre(@RequestBody Genre genre) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(genreService.saveGenre(genre).getGenreId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable("id") Long id, @RequestBody Genre genre) {
        Genre fetchedGenre = genreService.getGenreById(id);
        if (fetchedGenre != null) {
            genre.setGenreId(id);
            genreService.saveGenre(genre);
            return ResponseEntity.ok(genre);
        }
        return ResponseEntity.badRequest().header("Message", "No genre with Id " + id).build();
    }

}
