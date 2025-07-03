package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    // Trova tutti i carrelli associati a un dato utente
    Page<Cart> findByUserId(int userId, Pageable pageable);

    // Verifica se un utente ha un carrello attivo (non ancora finalizzato)
    boolean existsByUserIdAndCheckedOutFalse(int userId);
}

