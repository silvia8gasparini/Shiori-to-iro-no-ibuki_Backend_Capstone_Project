package it.epicode.finalproject.controller;

import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.Purchase;
import it.epicode.finalproject.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/user/{userId}/book/{bookId}")
    public Purchase createPurchase(@RequestBody @Valid Purchase purchase,
                                   @PathVariable int userId,
                                   @PathVariable int bookId) throws NotFoundException {
        return purchaseService.createPurchase(purchase, userId, bookId);
    }

    @GetMapping("")
    public Page<Purchase> getAllPurchases(Pageable pageable) {
        return purchaseService.getAllPurchases(pageable);
    }

    @GetMapping("/{id}")
    public Purchase getPurchaseById(@PathVariable int id) throws NotFoundException {
        return purchaseService.getPurchaseById(id);
    }

    @GetMapping("/user/{userId}")
    public Page<Purchase> getPurchasesByUser(@PathVariable int userId, Pageable pageable) {
        return purchaseService.getPurchasesByUser(userId, pageable);
    }

    @GetMapping("/book/{bookId}")
    public Page<Purchase> getPurchasesByBook(@PathVariable int bookId, Pageable pageable) {
        return purchaseService.getPurchasesByBook(bookId, pageable);
    }

    @GetMapping("/range")
    public Page<Purchase> getPurchasesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            Pageable pageable) {
        return purchaseService.getPurchasesByDateRange(start, end, pageable);
    }

    @PutMapping("/{id}")
    public Purchase updatePurchase(@PathVariable int id,
                                   @RequestBody @Valid Purchase updatedPurchase) throws NotFoundException {
        return purchaseService.updatePurchase(id, updatedPurchase);
    }

    @DeleteMapping("/{id}")
    public void deletePurchase(@PathVariable int id) throws NotFoundException {
        purchaseService.deletePurchase(id);
    }
}
