package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Page<Book> findByMicroSeasonId(int microSeasonId, Pageable pageable);
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);
    Page<Book> findByAvailability(it.epicode.finalproject.enumeration.Availability availability, Pageable pageable);
    Page<Book> findByPriceBetween(Double min, Double max, Pageable pageable);
    Page<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author, Pageable pageable);
    boolean existsByIsbn(String isbn);
}
