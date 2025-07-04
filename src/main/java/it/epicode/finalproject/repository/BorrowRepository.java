package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.Borrow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {
    Page<Borrow> findByUserId(int userId, Pageable pageable);
    Page<Borrow> findByDigitalCardId(int cardId, Pageable pageable);
    Page<Borrow> findByBookId(int bookId, Pageable pageable);
}
