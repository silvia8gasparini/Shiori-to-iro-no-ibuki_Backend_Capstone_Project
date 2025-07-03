package it.epicode.finalproject.controller;

import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.Cart;
import it.epicode.finalproject.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/user/{userId}")
    public Cart saveCart(@RequestBody @Valid Cart cart, @PathVariable int userId) throws NotFoundException {
        return cartService.saveCart(cart, userId);
    }

    @GetMapping("")
    public Page<Cart> getAllCarts(Pageable pageable) {
        return cartService.getAllCarts(pageable);
    }

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable int id) throws NotFoundException {
        return cartService.getCart(id);
    }

    @PutMapping("/{id}")
    public Cart updateCart(@PathVariable int id, @RequestBody @Valid Cart updatedCart) throws NotFoundException {
        return cartService.updateCart(id, updatedCart);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable int id) throws NotFoundException {
        cartService.deleteCart(id);
    }

    @GetMapping("/user/{userId}")
    public Page<Cart> findByUserId(@PathVariable int userId, Pageable pageable) {
        return cartService.findByUserId(userId, pageable);
    }
}
