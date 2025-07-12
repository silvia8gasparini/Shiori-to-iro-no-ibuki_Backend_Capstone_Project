package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Page<Cart> findByUserId(int userId, Pageable pageable);
    Optional<Cart> findByUserId(int userId);
    boolean existsByUserIdAndCheckedOutFalse(int userId);
}

