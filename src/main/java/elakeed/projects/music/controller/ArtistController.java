package elakeed.projects.music.controller;

import elakeed.projects.music.model.Album;
import elakeed.projects.music.model.Artist;
import elakeed.projects.music.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/artists")
@SessionAttributes("sessValue")
public class ArtistController {
    private ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public ModelAndView artistsPage(ModelAndView modelAndView) {
        modelAndView.setViewName("artists");
        modelAndView.addObject("artists", artistService.getAllArtists());
        return modelAndView;
    }

    @GetMapping("/add")
    public String addPage(Model model, HttpSession session) {
        model.addAttribute("vvv", session.getAttribute("sessValue"));
        model.addAttribute("artist", new Artist());
        return "artist_add";
    }

    @GetMapping(path = "/remove/{id}")
    public String deleteArtist(@PathVariable("id") Long id) {
        artistService.deleteArtist(id);
        return "redirect:/artists";
    }

    @PostMapping(path = "/save")
    public String saveArtist(@ModelAttribute("artist") Artist artist) {
        artistService.saveArtist(artist);
        return "redirect:/artists";
    }

    @GetMapping(path = "/update/{id}")
    public ModelAndView updatePage(ModelAndView modelAndView, @PathVariable("id") Long id) {
        modelAndView.addObject("artist", artistService.getArtistById(id));
        modelAndView.setViewName("artist_update");
        return modelAndView;
    }

    @PostMapping(path = "/edit/{id}")
    public String updateArtist(@ModelAttribute("artist") Artist artist, @PathVariable("id") Long id) {
        artist.setArtistId(id);
        artistService.saveArtist(artist);
        return "redirect:/artists";
    }
}
