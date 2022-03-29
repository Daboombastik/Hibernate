package fr.shcherbakov.hibernate.controller;

import fr.shcherbakov.hibernate.model.Movie;
import fr.shcherbakov.hibernate.repo.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    final MovieRepository repository;

    public MovieController(MovieRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Movie> findAllMovies() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findMovieById(@PathVariable Long id) {
        var movie = this.repository.findById(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public Movie saveMovie(@RequestBody Movie movie) {
        if (movie != null) {
            this.repository.persist(movie);
            return movie;
        }
        return null;
    }

    @PutMapping("/update")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) {
        var result = this.repository.update(movie);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/remove")
    public Movie removeMovie(@RequestBody Movie movie) {
        this.repository.remove(movie);
        return movie;
    }
}
