package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Page<Cart> findByUserId(int userId, Pageable pageable);
    boolean existsByUserIdAndCheckedOutFalse(int userId);
}

