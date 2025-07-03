package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    // Trova tutti gli acquisti di un determinato utente
    Page<Purchase> findByUserId(int userId, Pageable pageable);

    // Trova tutti gli acquisti relativi a un determinato libro
    Page<Purchase> findByBookId(int bookId, Pageable pageable);

    // Trova acquisti entro un certo intervallo di tempo
    Page<Purchase> findByPurchaseDateBetween(java.time.LocalDateTime start, java.time.LocalDateTime end, Pageable pageable);
}
