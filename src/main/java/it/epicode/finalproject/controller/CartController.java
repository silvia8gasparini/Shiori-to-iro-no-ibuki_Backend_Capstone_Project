package it.epicode.finalproject.controller;

import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.Cart;
import it.epicode.finalproject.model.User;
import it.epicode.finalproject.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public Cart saveCart(@RequestBody @Valid Cart cart,
                         @AuthenticationPrincipal User user) throws NotFoundException {
        return cartService.saveCart(cart, user.getId());
    }

    @GetMapping
    public Page<Cart> getAllCarts(Pageable pageable) {
        return cartService.getAllCarts(pageable);
    }

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable int id) throws NotFoundException {
        return cartService.getCart(id);
    }

    @PutMapping("/{id}")
    public Cart updateCart(@PathVariable int id,
                           @RequestBody @Valid Cart updatedCart) throws NotFoundException {
        return cartService.updateCart(id, updatedCart);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable int id) throws NotFoundException {
        cartService.deleteCart(id);
    }

    @GetMapping("/me")
    public Page<Cart> getMyCarts(@AuthenticationPrincipal User user, Pageable pageable) {
        return cartService.findByUserId(user.getId(), pageable);
    }
}
