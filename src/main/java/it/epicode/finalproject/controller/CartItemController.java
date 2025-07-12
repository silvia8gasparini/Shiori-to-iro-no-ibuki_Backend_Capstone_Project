package it.epicode.finalproject.controller;

import it.epicode.finalproject.dto.CartItemDto;
import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.CartItem;
import it.epicode.finalproject.model.User;
import it.epicode.finalproject.service.CartItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping
    public CartItem addToCart(@RequestBody @Valid CartItemDto dto,
                              @AuthenticationPrincipal User user) throws NotFoundException {
        return cartItemService.saveCartItemForUser(dto, user.getId());
    }

    @GetMapping("/me")
    public Page<CartItem> getMyCartItems(@AuthenticationPrincipal User user, Pageable pageable) {
        return cartItemService.findByUserId(user.getId(), pageable);
    }

    @PutMapping("/{id}")
    public CartItem updateCartItem(@PathVariable int id,
                                   @RequestBody @Valid CartItemDto dto) throws NotFoundException {
        return cartItemService.updateCartItem(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable int id) throws NotFoundException {
        cartItemService.deleteCartItem(id);
    }
}
