package com.example.movies;


import com.example.movies.dataFactory.FilmTestFactory;
import com.example.movies.entity.Film;
import com.example.movies.repository.FilmRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.assertj.core.api.Assertions;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(FilmTestFactory.class)
class FilmRepositoryTest {


    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private FilmTestFactory filmTestFactory;


    @Test
    void newFilmTest() {

        int before = filmRepository.findAll().size();

        filmTestFactory.saveFilm("Test1");

        int after = filmRepository.findAll().size();

        Assertions.assertThat(after).isEqualTo(before+1);

    }

    @Test
    void findFilm() {

        filmTestFactory.saveFilm("Test2");

        Assertions.assertThat(filmRepository.findFilmByTitleContains("Test2").getTitle()).isEqualTo("Test2");

    }

    @Test
    void deleteFilm() {

        int before = filmRepository.findFilmsByTitleContaining("Test3").size();

        filmTestFactory.saveFilm("Test3");

        Assertions.assertThat(filmRepository.findFilmByTitleContains("Test3").getTitle()).isEqualTo("Test3");

        filmRepository.deleteFilmByTitle("Test3");

        int after = filmRepository.findFilmsByTitleContaining("Test3").size();

        Assertions.assertThat(before).isEqualTo(after);
    }

    @Test
    void findAllFilmsBetweenYear() {

        Film film1 = new Film();
        film1.setTitle("Film1");
        film1.setYear(3000);

        Film film2 = new Film();
        film2.setTitle("Film2");
        film2.setYear(3005);

        Film film3 = new Film();
        film3.setTitle("Film3");
        film3.setYear(3010);

        filmRepository.save(film1);
        filmRepository.save(film2);
        filmRepository.save(film3);


        int size = filmRepository.findFilmByYearBetween(3002, 3012).size();

        Assertions.assertThat(2).isEqualTo(size);


    }


}
