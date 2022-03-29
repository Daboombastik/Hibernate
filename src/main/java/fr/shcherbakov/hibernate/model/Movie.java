package fr.shcherbakov.hibernate.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @SequenceGenerator(name = "movie_id_sequence", sequenceName = "movie_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_id_sequence")
    Long id;
    String name;
    String description;
    Certification certification;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "movie")
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    public Movie addGenre(Genre genre) {
        if (genre != null) {
            this.genres.add(genre);
            genre.getMovies().add(this);
        }
        return this;
    }

    public Movie removeGenre(Genre genre) {
        if (genre != null) {
            this.genres.remove(genre);
            genre.getMovies().remove(this);
        }
        return this;
    }

    public Set<Genre> getGenres() {
        return Collections.unmodifiableSet(genres);
    }

    public Movie addReview(Review review) {
        if (review != null) {
            this.reviews.add(review);
            review.setMovie(this);
        }
        return this;
    }

    public Movie removeReview(Review review) {
        if (review != null) {
            this.reviews.remove(review);
            review.setMovie(null);
        }
        return this;
    }

    public List<Review> getReviews() {
        return Collections.unmodifiableList(reviews);
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Certification getCertification() {
        return certification;
    }

    public Movie setCertification(Certification certification) {
        this.certification = certification;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Movie setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Movie setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(31);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((!(obj instanceof Movie other))) {
            return false;
        }
        if (id == null && other.getId() == null) {
            return Objects.equals(name, other.getName()) && Objects.equals(description, other.getDescription())
                    && Objects.equals(certification, other.getCertification());
        }
        return id != null && Objects.equals(id, other.getId());
    }

    @Override
    public String toString() {
        return "Movie [id=" + id + ", name=" + name + ", description=" + description + ", certification="
                + certification + "]";
    }


}
