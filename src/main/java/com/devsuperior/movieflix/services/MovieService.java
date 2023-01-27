package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieGenreDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {

  @Autowired
  private MovieRepository repository;

  @Autowired
  private GenreRepository genreRepository;

  @Transactional(readOnly = true)
  public MovieDTO findById(Long id) {
    Optional<Movie> obj = repository.findById(id);
    Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
    return new MovieDTO(entity);
  }

  @Transactional(readOnly = true)
  public Page<MovieGenreDTO> findByGenre(Long genreId, Pageable pageable) {
    List<Genre> genres =
        (genreId == 0) ? genreRepository.findAll() : Arrays.asList(genreRepository.getOne(genreId));
    Page<Movie> page = repository.findByGenre(genres, pageable);
    return page.map(MovieGenreDTO::new);
  }
}
