package com.example.movies.controller;


import com.example.movies.dto.AddOrEditMovieDto;
import com.example.movies.entity.Film;
import com.example.movies.repository.ActorRepository;
import com.example.movies.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MovieController {


    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ActorRepository actorRepository;

    @GetMapping("/")
    public String showAllMovies(Model model){
        model.addAttribute("movieList", filmRepository.findAll());
        return "movie-list";
    }

    @GetMapping("/movie-detail/{id}")
    public String showMovieDetail(@PathVariable(required = false) Long id, Model model){

       try{
           model.addAttribute("movie", filmRepository.findById(id).get());
           return "movie-detail";
       }catch(Exception exception){
           return "error";
        }

    }

    @GetMapping(value= {"/movie-form", "/movie-form/{id}"} )
    public String showMovieForm(@PathVariable(required = false) Long id, Model model){

        if(id != null){
            Film byId = filmRepository.findById(id).orElse(new Film());
            model.addAttribute("allActors", actorRepository.findAll());
            model.addAttribute("actorsFromFilm", actorRepository.findAllActorsFromFilm(byId.getTitle()));
            model.addAttribute("movie", byId);
        }else{
            model.addAttribute("movie", new AddOrEditMovieDto());
        }

        return "movie-form";
    }

    @GetMapping("/filter{title}")
    public String movieFilter(@PathVariable(required = false) String title, Model model){

        model.addAttribute("movieList", filmRepository.findFilmsByTitleContaining(title));
        return "movie-list";


    }

    @PostMapping("/addMovie")
    public String movieFormProcess(AddOrEditMovieDto addOrEditMovieDto, Model model){

        Film film = new Film();
        film.setTitle(addOrEditMovieDto.getTitle());
        film.setYear(addOrEditMovieDto.getYear());
        film.setLength(addOrEditMovieDto.getLength());
        film.setId(addOrEditMovieDto.getId());

        filmRepository.save(film);
        return "redirect:/";
    }

}
