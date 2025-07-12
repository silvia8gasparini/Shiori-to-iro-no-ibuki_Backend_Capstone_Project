package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    Page<CartItem> findByCartId(int cartId, Pageable pageable);
}
