package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUserId(int userId);
    boolean existsByUserIdAndBookId(int userId, int bookId);
    void deleteByUserIdAndBookId(int userId, int bookId);
    Optional<Favorite> findByUserIdAndBookId(int userId, int bookId);
}
