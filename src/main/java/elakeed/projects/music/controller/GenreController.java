package elakeed.projects.music.controller;

import elakeed.projects.music.model.Genre;
import elakeed.projects.music.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path = "/genres")
@SessionAttributes("sessValue")
public class GenreController {
    private GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ModelAndView genresPage(ModelAndView modelAndView) {
        modelAndView.setViewName("genres");
        modelAndView.addObject("genres", genreService.getAllGenres());
        return modelAndView;
    }

    @GetMapping(path = "/remove/{id}")
    public String deleteGenre(@PathVariable("id") Long id) {
        genreService.deleteGenre(id);
        return "redirect:/genres";
    }

    @GetMapping(path = "/add")
    public String addPage(Model model) {
        model.addAttribute("sessValue","Elakeed & Moaz");
        model.addAttribute("genre", new Genre());
        return "genre_add";
    }
    @GetMapping(path = "/update/{id}")
    public String updatePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("genre", genreService.getGenreById(id));
        return "genre_update";
    }

    @PostMapping(path = "/save")
    public String saveGenre(@ModelAttribute("genre") Genre genre) {
        genreService.saveGenre(genre);
        return "redirect:/genres";
    }

    @PostMapping(path = "/edit/{id}")
    public String updateGenre(@PathVariable("id") Long id, @ModelAttribute("genre") Genre genre) {
        genre.setGenreId(id);
        genreService.saveGenre(genre);
        return "redirect:/genres";
    }

}
