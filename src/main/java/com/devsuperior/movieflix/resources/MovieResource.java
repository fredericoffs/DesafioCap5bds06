package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieGenreDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "movies")
@PreAuthorize("hasAnyRole('VISITOR','MEMBER')")
public class MovieResource {

  @Autowired
  private MovieService service;

  @GetMapping()
  public ResponseEntity<Page<MovieGenreDTO>> findByGenre(
      @RequestParam(value = "genreId", defaultValue = "0") Long genreId,
      Pageable pageable) {
    Page<MovieGenreDTO> list = service.findByGenre(genreId, pageable);
    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
    MovieDTO dto = service.findById(id);
    return ResponseEntity.ok().body(dto);
  }
}
