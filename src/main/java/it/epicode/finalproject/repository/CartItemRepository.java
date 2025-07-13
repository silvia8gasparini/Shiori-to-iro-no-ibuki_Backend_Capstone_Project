package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.Book;
import it.epicode.finalproject.model.Cart;
import it.epicode.finalproject.model.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    Page<CartItem> findByCartId(int cartId, Pageable pageable);
    List<CartItem> findByCartAndBook(Cart cart, Book book);

}
