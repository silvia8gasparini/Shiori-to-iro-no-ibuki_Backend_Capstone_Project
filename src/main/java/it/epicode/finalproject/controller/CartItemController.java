package it.epicode.finalproject.controller;

import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.CartItem;
import it.epicode.finalproject.service.CartItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/cart/{cartId}/book/{bookId}")
    public CartItem saveCartItem(@RequestBody @Valid CartItem cartItem,
                                 @PathVariable int cartId,
                                 @PathVariable int bookId) throws NotFoundException {
        return cartItemService.saveCartItem(cartItem, cartId, bookId);
    }

    @GetMapping("")
    public Page<CartItem> getAllCartItems(Pageable pageable) {
        return cartItemService.getAllCartItems(pageable);
    }

    @GetMapping("/{id}")
    public CartItem getCartItem(@PathVariable int id) throws NotFoundException {
        return cartItemService.getCartItem(id);
    }

    @PutMapping("/{id}")
    public CartItem updateCartItem(@PathVariable int id, @RequestBody @Valid CartItem updatedItem)
            throws NotFoundException {
        return cartItemService.updateCartItem(id, updatedItem);
    }

    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable int id) throws NotFoundException {
        cartItemService.deleteCartItem(id);
    }

    @GetMapping("/cart/{cartId}")
    public Page<CartItem> findByCartId(@PathVariable int cartId, Pageable pageable) {
        return cartItemService.findByCartId(cartId, pageable);
    }
}
