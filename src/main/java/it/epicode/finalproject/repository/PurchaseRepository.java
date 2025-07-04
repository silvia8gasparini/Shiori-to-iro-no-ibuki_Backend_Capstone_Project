package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    Page<Purchase> findByUserId(int userId, Pageable pageable);
    Page<Purchase> findByBookId(int bookId, Pageable pageable);
    Page<Purchase> findByPurchaseDateBetween(java.time.LocalDateTime start, java.time.LocalDateTime end, Pageable pageable);
}
