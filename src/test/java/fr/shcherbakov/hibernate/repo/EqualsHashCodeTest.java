package fr.shcherbakov.hibernate.repo;


import fr.shcherbakov.hibernate.config.PersistenceConfig;
import fr.shcherbakov.hibernate.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringJUnitConfig(PersistenceConfig.class)
@TestPropertySource("classpath:application-test.properties")
@SqlConfig(dataSource = "dataSourceH2", transactionManager = "transactionManager")
@Sql({ "classpath:data-init.sql" })
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Slf4j
public class EqualsHashCodeTest {

	@Autowired
	private MovieRepository repository;

	@Test
	public void equalsTest() {
		Movie movie = new Movie().setName("Dune");
		repository.persist(movie);

		Movie bdMovie = repository.findById(movie.getId());

		assertThat(movie.equals(bdMovie)).as("il devrait y avoir égalité entre ces deux entités").isTrue();

		Movie refMovie = repository.getReference(movie.getId());
		assertThat(movie.equals(refMovie)).as("il devrait y avoir égalité l'entité et sa référence").isTrue();

	}

	@Test
	public void setTest() {
		Movie movie = new Movie().setName("Dune");
		Set<Movie> monSet = new HashSet<>();
		monSet.add(movie);
		assertThat(monSet.contains(movie)).as("le set devrait contenir le movie").isTrue();
		repository.persist(movie);
		assertThat(monSet.contains(movie)).as("le set devrait contenir le movie une fois persisté").isTrue();
	}
}
