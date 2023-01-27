package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {

  @Query("SELECT obj FROM Movie obj "
      + "INNER JOIN obj.genre genre "
      + "WHERE genre IN :genres "
      + "ORDER BY obj.title")
  Page<Movie> findByGenre(List<Genre> genres, Pageable pageable);
}
