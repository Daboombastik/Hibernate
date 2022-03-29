package fr.shcherbakov.hibernate.repo;

import fr.shcherbakov.hibernate.model.Movie;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void persist(Movie movie) {
        this.entityManager.persist(movie);
    }

    public Movie findById(Long id) {
        return this.entityManager.find(Movie.class, id);
    }

    public List<Movie> findAll() {
        return this.entityManager.createQuery("select m from Movie m", Movie.class).getResultList();
    }

    @Transactional
    public Optional<Movie> update(Movie movie) {
        if (movie.getId() != null) {
            var result = this.findById(movie.getId());
            if (result != null) {
                this.entityManager.merge(movie);
                return Optional.of(movie);
            }
        }
        return Optional.empty();
    }

    @Transactional
    public void remove(Movie movie) {
        this.entityManager.remove(movie);
    }

    public Movie getReference(Long id) {
        return this.entityManager.getReference(Movie.class, id);
    }
}
