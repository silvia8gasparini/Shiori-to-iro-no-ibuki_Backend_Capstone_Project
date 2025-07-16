package it.epicode.finalproject.service;

import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.Book;
import it.epicode.finalproject.model.MicroSeason;
import it.epicode.finalproject.repository.BookRepository;
import it.epicode.finalproject.repository.MicroSeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MicroSeasonRepository microSeasonRepository;

    public Book saveBook(Book book, int microSeasonId) throws NotFoundException {
        MicroSeason microSeason = microSeasonRepository.findById(microSeasonId)
                .orElseThrow(() -> new NotFoundException("MicroSeason not found with id: " + microSeasonId));

        book.setMicroSeason(microSeason);
        return bookRepository.save(book);
    }

    public Book getBookById(int id) throws NotFoundException {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + id));
    }

    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book updateBook(int id, Book updatedBook) throws NotFoundException {
        Book existingBook = getBookById(id);

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setYear(updatedBook.getYear());
        existingBook.setPublisher(updatedBook.getPublisher());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setPrice(updatedBook.getPrice());
        existingBook.setImageUrl(updatedBook.getImageUrl());
        existingBook.setDescription(updatedBook.getDescription());
        existingBook.setAvailability(updatedBook.getAvailability());

        return bookRepository.save(existingBook);
    }

    public void deleteBook(int id) throws NotFoundException {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }

    public Page<Book> getBooksByMicroSeason(int microSeasonId, Pageable pageable) {
        return bookRepository.findByMicroSeasonId(microSeasonId, pageable);
    }

    public Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable) {
        return bookRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    public Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable) {
        return bookRepository.findByAuthorContainingIgnoreCase(author, pageable);
    }

    public Page<Book> findByAvailability(it.epicode.finalproject.enumeration.Availability availability, Pageable pageable) {
        return bookRepository.findByAvailability(availability, pageable);
    }

    public Page<Book> findByPriceBetween(Double min, Double max, Pageable pageable){
        return bookRepository.findByPriceBetween(min, max, pageable);
    }

    public Page<Book> findByTitleOrAuthor(String query, Pageable pageable) {
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query, pageable);
    }

}
