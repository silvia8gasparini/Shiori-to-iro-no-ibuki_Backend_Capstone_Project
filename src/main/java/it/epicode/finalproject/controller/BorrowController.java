package it.epicode.finalproject.controller;

import it.epicode.finalproject.dto.BorrowDto;
import it.epicode.finalproject.dto.BorrowResponseDto;
import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.Borrow;
import it.epicode.finalproject.model.User;
import it.epicode.finalproject.service.BorrowService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/borrows")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping
    public BorrowResponseDto createBorrow(@RequestBody @Valid BorrowDto borrowDto,
                                          @AuthenticationPrincipal User user) throws NotFoundException {
        Borrow saved = borrowService.saveBorrow(borrowDto, user);
        return borrowService.toDto(saved);
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
