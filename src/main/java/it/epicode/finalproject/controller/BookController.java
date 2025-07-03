package it.epicode.finalproject.controller;

import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.Book;
import it.epicode.finalproject.enumeration.Availability;
import it.epicode.finalproject.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/microseason/{microSeasonId}")
    public Book saveBook(@RequestBody @Valid Book book, @PathVariable int microSeasonId) throws NotFoundException {
        return bookService.saveBook(book, microSeasonId);
    }

    @GetMapping("")
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookService.getAllBooks(pageable);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) throws NotFoundException {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody @Valid Book updatedBook) throws NotFoundException {
        return bookService.updateBook(id, updatedBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) throws NotFoundException {
        bookService.deleteBook(id);
    }

    @GetMapping("/microseason/{microSeasonId}")
    public Page<Book> getBooksByMicroSeason(@PathVariable int microSeasonId, Pageable pageable) {
        return bookService.getBooksByMicroSeason(microSeasonId, pageable);
    }

    @GetMapping("/search/title")
    public Page<Book> searchByTitle(@RequestParam String title, Pageable pageable) {
        return bookService.findByTitleContainingIgnoreCase(title, pageable);
    }

    @GetMapping("/search/author")
    public Page<Book> searchByAuthor(@RequestParam String author, Pageable pageable) {
        return bookService.findByAuthorContainingIgnoreCase(author, pageable);
    }

    @GetMapping("/search/availability")
    public Page<Book> searchByAvailability(@RequestParam Availability availability, Pageable pageable) {
        return bookService.findByAvailability(availability, pageable);
    }

    @GetMapping("/search/price")
    public Page<Book> searchByPriceRange(@RequestParam Double min, @RequestParam Double max, Pageable pageable) {
        return bookService.findByPriceBetween(min, max, pageable);
    }
}
