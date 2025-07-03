package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    Page<CartItem> findByCartId(int cartId, Pageable pageable);
}
