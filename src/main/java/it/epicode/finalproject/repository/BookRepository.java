package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    // Cerca tutti i libri associati a una micro-stagione specifica
    Page<Book> findByMicroSeasonId(int microSeasonId, Pageable pageable);

    // Cerca per titolo parziale ignorando maiuscole/minuscole
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    // Cerca per autore
    Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);

    // Cerca per disponibilità
    Page<Book> findByAvailability(it.epicode.finalproject.enumeration.Availability availability, Pageable pageable);

    // Cerca per range di prezzo
    Page<Book> findByPriceBetween(Double min, Double max, Pageable pageable);
}
