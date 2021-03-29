package com.example.movies;

import com.example.movies.dataFactory.Creator
import com.example.movies.entity.Actor
import com.example.movies.entity.Film
import com.example.movies.entity.FilmActor
import com.example.movies.repository.ActorRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import([Creator.class])
class ActorRepositoryGroovyTest {


    @Autowired
    private Creator creator;

    @Autowired
    private ActorRepository actorRepository;

    @Test
    void newActorTest() {

        int before = actorRepository.findAll().size();

        Actor actor = new Actor(name: "TestGroovyName1", surname: "TestGroovySurname1")
        creator.save(actor);

        int after = actorRepository.findAll().size();

        Assertions.assertThat(after).isEqualTo(before+1);

        def readFromDb = actorRepository.findById(actor.getId()).get()
        Assertions.assertThat(readFromDb.getSurname()).isEqualTo("TestGroovySurname1");
        Assertions.assertThat(readFromDb.getName()).isEqualTo("TestGroovyName1");


    }

    @Test
    void findActor() {

        Actor actor = new Actor(name: "TestGroovyName2", surname: "TestGroovySurname2")
        creator.save(actor);

        Assertions.assertThat(actorRepository.findActorBySurnameAndName("TestGroovySurname2","TestGroovyName2").getSurname()).isEqualTo("TestGroovySurname2");

    }

    @Test
    void deleteActor() {

        Actor actor = new Actor(name: "TestGroovyName3", surname: "TestGroovySurname3")
        creator.save(actor);

        Assertions.assertThat(actorRepository.findActorBySurnameAndName("TestGroovySurname3","TestGroovyName3").getSurname()).isEqualTo("TestGroovySurname3");

        actorRepository.deleteActorBySurnameAndName("TestGroovySurname3","TestGroovyName3");

        Assertions.assertThat(actorRepository.findActorBySurnameAndName("TestGroovySurname3","TestGroovyName3")).isEqualTo(null);
    }

    @Test
    void findAllActorsFromFilmTest() {


        Film film1 = new Film(title: "TestFilmGroovy1")
        creator.save(film1);

        Actor actor1 = new Actor(name: "TestGroovyName4", surname: "TestGroovySurname4")
        creator.save(actor1);

        Actor actor2 = new Actor(name: "TestGroovyName5", surname: "TestGroovySurname5")
        creator.save(actor2);

        FilmActor filmActor1 = new FilmActor(actor: actor1,film: film1);
        creator.save(filmActor1);

        FilmActor filmActor2 = new FilmActor(actor: actor2,film: film1);
        creator.save(filmActor2);

        List<Actor> allFilmActors = actorRepository.findAllActorsFromFilm("TestFilmGroovy1");

        Assertions.assertThat(allFilmActors.size()).isEqualTo(2);


    }



}
