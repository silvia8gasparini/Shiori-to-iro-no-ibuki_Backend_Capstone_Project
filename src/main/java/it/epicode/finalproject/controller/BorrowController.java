package it.epicode.finalproject.controller;

import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.Borrow;
import it.epicode.finalproject.service.BorrowService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrows")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping("/book/{bookId}/user/{userId}/card/{cardId}")
    public Borrow saveBorrow(@RequestBody @Valid Borrow borrow,
                             @PathVariable int bookId,
                             @PathVariable int userId,
                             @PathVariable int cardId) throws NotFoundException {
        return borrowService.saveBorrow(borrow, bookId, userId, cardId);
    }

    @GetMapping("")
    public Page<Borrow> getAllBorrows(Pageable pageable) {
        return borrowService.getAllBorrows(pageable);
    }

    @GetMapping("/{id}")
    public Borrow getBorrowById(@PathVariable int id) throws NotFoundException {
        return borrowService.getBorrowById(id);
    }

    @PutMapping("/{id}")
    public Borrow updateBorrow(@PathVariable int id, @RequestBody @Valid Borrow updatedBorrow)
            throws NotFoundException {
        return borrowService.updateBorrow(id, updatedBorrow);
    }

    @DeleteMapping("/{id}")
    public void deleteBorrow(@PathVariable int id) throws NotFoundException {
        borrowService.deleteBorrow(id);
    }

    @GetMapping("/user/{userId}")
    public Page<Borrow> getBorrowsByUser(@PathVariable int userId, Pageable pageable) {
        return borrowService.getBorrowsByUser(userId, pageable);
    }

    @GetMapping("/card/{cardId}")
    public Page<Borrow> getBorrowsByCard(@PathVariable int cardId, Pageable pageable) {
        return borrowService.getBorrowsByCard(cardId, pageable);
    }

    @GetMapping("/book/{bookId}")
    public Page<Borrow> getBorrowsByBook(@PathVariable int bookId, Pageable pageable) {
        return borrowService.getBorrowsByBook(bookId, pageable);
    }
}
