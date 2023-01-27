package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

  @Autowired
  private ReviewRepository repository;

  @Autowired
  private UserRepository userRepository;

  @Transactional
  public ReviewDTO insert(ReviewDTO dto) {
    Review entity = new Review();
    copyDtoToEntity(dto, entity);
    entity = repository.save(entity);
    return new ReviewDTO(entity);
  }

  public User authenticated() {
    try {
      String username = SecurityContextHolder.getContext().getAuthentication().getName();
      return userRepository.findByEmail(username);
    } catch (Exception e) {
      throw new UnauthorizedException("Invalid user");
    }
  }

  private void copyDtoToEntity(ReviewDTO dto, Review entity) {
    User user = authenticated();
    entity.setText(dto.getText());
    entity.setUser(user);
    entity.setMovie(new Movie(dto.getMovieId(), null, null, null, null, null, null));
  }
}
