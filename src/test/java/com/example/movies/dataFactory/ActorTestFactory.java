package com.example.movies.dataFactory;

import com.example.movies.entity.Actor;
import com.example.movies.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActorTestFactory {

    @Autowired
    private ActorRepository actorRepository;

    public void saveActor(String name, String surname) {
        Actor actor = new Actor();
        actor.setName(name);
        actor.setSurname(surname);
        actorRepository.save(actor);
    }

    public void saveActor(Actor actor) {

        if(actor.getSurname()==null) actor.setSurname("DefaulSurename");

        actorRepository.save(actor);
    }

}
