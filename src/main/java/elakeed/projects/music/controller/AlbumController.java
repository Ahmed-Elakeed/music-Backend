package elakeed.projects.music.controller;

import elakeed.projects.music.model.Album;
import elakeed.projects.music.service.AlbumService;
import elakeed.projects.music.service.ArtistService;
import elakeed.projects.music.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(path = {"/", "albums"})
public class AlbumController {
    private AlbumService albumService;
    private GenreService genreService;
    private ArtistService artistService;

    @Autowired
    public AlbumController(AlbumService albumService, GenreService genreService, ArtistService artistService) {
        this.albumService = albumService;
        this.genreService = genreService;
        this.artistService = artistService;
    }


    @GetMapping
    public String albumsPage(Model model) {
        model.addAttribute("albums", albumService.getAllAlbums());
        return "albums";
    }

    @GetMapping(path = "/remove/{id}")
    public String deleteAlbum(@PathVariable("id") Long id) {
        albumService.deleteAlbum(id);
        return "redirect:/albums";
    }

    @GetMapping(path = "/add")
    public String addPage(Model model) {
        model.addAttribute("album", new Album());
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("artists", artistService.getAllArtists());
        return "album_add";
    }

    @PostMapping(path = "/save")
    public String saveAlbum(@ModelAttribute("album") Album album, @RequestParam("released_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date releasedDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
        String date = simpleDateFormat.format(releasedDate);
        album.setReleasedDate(date);
        albumService.saveAlbum(album);
        return "redirect:/albums";
    }

    @GetMapping(path = "/update/{id}")
    public String updatePage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("album", albumService.getAlbumById(id));
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("artists", artistService.getAllArtists());
        return "album_update";
    }

    @PostMapping(path = "/edit/{id}")
    public String updateAlbum(@ModelAttribute("album") Album album, @PathVariable("id") Long id, @RequestParam("released_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date releasedDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
        String date = simpleDateFormat.format(releasedDate);
        album.setReleasedDate(date);
        album.setAlbumId(id);
        albumService.saveAlbum(album);
        return "redirect:/albums";
    }
}
