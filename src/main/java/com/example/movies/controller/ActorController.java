package com.example.movies.controller;

import com.example.movies.dto.AddOrEditActorDto;
import com.example.movies.entity.Actor;
import com.example.movies.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    @GetMapping("/actor-list")
    public String showAllActors(Model model){
        model.addAttribute("actorList", actorRepository.findAll());
        return "actor-list";
    }

    @GetMapping("/actor-detail/{id}")
    public String showActorDetail(@PathVariable(required = false) Long id, Model model){

        try{
            model.addAttribute("actor", actorRepository.findById(id).get());
            return "actor-detail";
        }catch(Exception exception){
            return "error";
        }

    }

    @GetMapping(value= {"/actor-form", "/actor-form/{id}"} )
    public String showActorForm(@PathVariable(required = false) Long id, Model model){

        if(id != null){
            Actor byId = actorRepository.findById(id).orElse(new Actor());
            model.addAttribute("actor", byId);
        }else{
            model.addAttribute("actor", new AddOrEditActorDto());
        }

        return "actor-form";
    }


    @PostMapping("/addActor")
    public String actorFormProcess(AddOrEditActorDto addOrEditActorDto, Model model){

        Actor actor = new Actor();
        actor.setName(addOrEditActorDto.getName());
        actor.setSurname(addOrEditActorDto.getSurname());
        actor.setId(addOrEditActorDto.getId());
        actorRepository.save(actor);
        return "redirect:/actor-list";
    }

}
