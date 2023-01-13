package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.services.GenreService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/genres")
public class GenreResource {

  @Autowired
  private GenreService service;

  @GetMapping
  @PreAuthorize("hasAnyRole('VISITOR','MEMBER')")
  public ResponseEntity<List<GenreDTO>> findAll() {
    List<GenreDTO> list = service.findAll();
    return ResponseEntity.ok().body(list);
  }
}
