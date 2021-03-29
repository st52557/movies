package com.example.movies;


import com.example.movies.entity.Actor;
import com.example.movies.entity.Film;
import com.example.movies.entity.FilmActor;
import com.example.movies.repository.ActorRepository;
import com.example.movies.repository.FilmActorRepository;
import com.example.movies.repository.FilmRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ActorRepositoryTest {


    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private FilmActorRepository filmActorRepository;


    @Test
    void newActor() {

        int before = actorRepository.findAll().size();

        Actor actor = new Actor();
        actor.setName("Lukas");
        actor.setSurname("Semorad");
        actorRepository.save(actor);

        int after = actorRepository.findAll().size();

        Assertions.assertThat(after).isEqualTo(before+1);

    }

    @Test
    void actorsFromFilm() {


        Film film = new Film();
        film.setTitle("TestFilmActors");
        filmRepository.save(film);

        Actor actor1 = new Actor();
        actor1.setName("Lukas");
        actorRepository.save(actor1);

        Actor actor2 = new Actor();
        actor2.setName("Iveta");
        actorRepository.save(actor2);

        Actor actor3 = new Actor();
        actor3.setName("Štěpán");
        actorRepository.save(actor3);

        FilmActor filmActor1 = new FilmActor();
        filmActor1.setActor(actor1);
        filmActor1.setFilm(film);
        filmActorRepository.save(filmActor1);

        FilmActor filmActor2 = new FilmActor();
        filmActor2.setActor(actor2);
        filmActor2.setFilm(film);
        filmActorRepository.save(filmActor2);

        List<Actor> allFilmActors = actorRepository.findAllActorsFromFilm("TestFilmActors");

        Assertions.assertThat(allFilmActors.size()).isEqualTo(2);

    }

}
