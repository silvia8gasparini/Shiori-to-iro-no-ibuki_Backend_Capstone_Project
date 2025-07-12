package it.epicode.finalproject.service;

import it.epicode.finalproject.dto.CartItemDto;
import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.Book;
import it.epicode.finalproject.model.Cart;
import it.epicode.finalproject.model.CartItem;
import it.epicode.finalproject.model.User;
import it.epicode.finalproject.repository.BookRepository;
import it.epicode.finalproject.repository.CartItemRepository;
import it.epicode.finalproject.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CartRepository cartRepository;

    public CartItem saveCartItemForUser(CartItemDto dto, int userId) throws NotFoundException {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    User user = new User();
                    user.setId(userId);
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new NotFoundException("Libro non trovato con ID " + dto.getBookId()));

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setBook(book);
        item.setQuantity(dto.getQuantity());
        item.setPriceAtSelection(dto.getPriceAtSelection());

        return cartItemRepository.save(item);
    }

    public Page<CartItem> findByUserId(int userId, Pageable pageable) {
        return cartRepository.findByUserId(userId)
                .map(cart -> cartItemRepository.findByCartId(cart.getId(), pageable))
                .orElse(Page.empty(pageable));
    }

    public CartItem updateCartItem(int id, CartItemDto dto) throws NotFoundException {
        CartItem existing = cartItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("CartItem non trovato con ID: " + id));
        existing.setQuantity(dto.getQuantity());
        existing.setPriceAtSelection(dto.getPriceAtSelection());
        return cartItemRepository.save(existing);
    }

    public void deleteCartItem(int id) throws NotFoundException {
        CartItem item = cartItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("CartItem non trovato con ID: " + id));
        cartItemRepository.delete(item);
    }
}
