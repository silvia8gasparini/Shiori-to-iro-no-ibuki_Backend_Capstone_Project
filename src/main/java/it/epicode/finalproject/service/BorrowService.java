package it.epicode.finalproject.service;

import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.Book;
import it.epicode.finalproject.model.Borrow;
import it.epicode.finalproject.model.DigitalCard;
import it.epicode.finalproject.model.User;
import it.epicode.finalproject.repository.BookRepository;
import it.epicode.finalproject.repository.BorrowRepository;
import it.epicode.finalproject.repository.DigitalCardRepository;
import it.epicode.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DigitalCardRepository digitalCardRepository;

    public Borrow saveBorrow(Borrow borrow, int bookId, int userId, int cardId) throws NotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + bookId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        DigitalCard card = digitalCardRepository.findById(cardId)
                .orElseThrow(() -> new NotFoundException("DigitalCard not found with id: " + cardId));

        borrow.setBook(book);
        borrow.setUser(user);
        borrow.setDigitalCard(card);

        return borrowRepository.save(borrow);
    }

    public Borrow getBorrowById(int id) throws NotFoundException {
        return borrowRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Borrow not found with id: " + id));
    }

    public Page<Borrow> getAllBorrows(Pageable pageable) {
        return borrowRepository.findAll(pageable);
    }

    public Borrow updateBorrow(int id, Borrow updatedBorrow) throws NotFoundException {
        Borrow existingBorrow = getBorrowById(id);

        existingBorrow.setStartDate(updatedBorrow.getStartDate());
        existingBorrow.setDueDate(updatedBorrow.getDueDate());
        existingBorrow.setReturnDate(updatedBorrow.getReturnDate());
        existingBorrow.setReturned(updatedBorrow.isReturned());

        return borrowRepository.save(existingBorrow);
    }

    public void deleteBorrow(int id) throws NotFoundException {
        Borrow borrow = getBorrowById(id);
        borrowRepository.delete(borrow);
    }

    public Page<Borrow> getBorrowsByUser(int userId, Pageable pageable) {
        return borrowRepository.findByUserId(userId, pageable);
    }

    public Page<Borrow> getBorrowsByCard(int cardId, Pageable pageable) {
        return borrowRepository.findByDigitalCardId(cardId, pageable);
    }

    public Page<Borrow> getBorrowsByBook(int bookId, Pageable pageable) {
        return borrowRepository.findByBookId(bookId, pageable);
    }
}
