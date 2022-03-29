package fr.shcherbakov.hibernate.repo;

//import fr.shcherbakov.hibernate.PersistenceConfigTest;
import fr.shcherbakov.hibernate.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(scripts = {"classpath:data-init.sql"})
@SqlConfig(dataSource = "dataSourceH2", transactionManager = "transactionManager")
@Slf4j
//@ConfigurationPropertiesScan(basePackageClasses = {PersistenceConfigTest.class})
public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;

    @Test
    void test1() {
        var movie = new Movie();
        movie.setName("movie");
        movieRepository.persist(movie);
    }

    @Test
    void test2() {
        Movie byId = movieRepository.findById(-100L);
        log.trace("{}", byId.getName());
    }

    @Test
    void test3() {
        List<Movie> movies = movieRepository.findAll();
        log.trace("{}", movies);
    }

    @Test
    void test4() {
        Movie movie = movieRepository.findById(-100L);
        movie.setName("New name");
        Optional<Movie> updated = movieRepository.update(movie);
        updated.ifPresent(value -> assertThat("Name updated", value.getName().equals("New name")));
        log.trace("{}", updated);
    }
}
