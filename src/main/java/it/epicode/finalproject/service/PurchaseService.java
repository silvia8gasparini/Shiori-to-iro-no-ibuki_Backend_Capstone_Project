package it.epicode.finalproject.service;

import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.Book;
import it.epicode.finalproject.model.Purchase;
import it.epicode.finalproject.model.User;
import it.epicode.finalproject.repository.BookRepository;
import it.epicode.finalproject.repository.PurchaseRepository;
import it.epicode.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public Purchase createPurchase(Purchase purchase, int userId, int bookId) throws NotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + bookId));

        purchase.setUser(user);
        purchase.setBook(book);

        return purchaseRepository.save(purchase);
    }

    public Purchase getPurchaseById(int id) throws NotFoundException {
        return purchaseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Purchase not found with id: " + id));
    }

    public Page<Purchase> getAllPurchases(Pageable pageable) {
        return purchaseRepository.findAll(pageable);
    }

    public Page<Purchase> getPurchasesByUser(int userId, Pageable pageable) {
        return purchaseRepository.findByUserId(userId, pageable);
    }

    public Page<Purchase> getPurchasesByBook(int bookId, Pageable pageable) {
        return purchaseRepository.findByBookId(bookId, pageable);
    }

    public Page<Purchase> getPurchasesByDateRange(java.time.LocalDateTime start, java.time.LocalDateTime end, Pageable pageable) {
        return purchaseRepository.findByPurchaseDateBetween(start, end, pageable);
    }

    public Purchase updatePurchase(int id, Purchase updatedPurchase) throws NotFoundException {
        Purchase existing = getPurchaseById(id);
        existing.setPurchaseDate(updatedPurchase.getPurchaseDate());
        existing.setTotal(updatedPurchase.getTotal());
        return purchaseRepository.save(existing);
    }

    public void deletePurchase(int id) throws NotFoundException {
        Purchase purchase = getPurchaseById(id);
        purchaseRepository.delete(purchase);
    }
}
