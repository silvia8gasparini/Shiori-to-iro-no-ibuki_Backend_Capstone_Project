package it.epicode.finalproject.service;

import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.Book;
import it.epicode.finalproject.model.Cart;
import it.epicode.finalproject.model.CartItem;
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

    public CartItem saveCartItem(CartItem cartItem, int cartId, int bookId) throws NotFoundException {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundException("Cart not found with id: " + cartId));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + bookId));

        cartItem.setCart(cart);
        cartItem.setBook(book);

        return cartItemRepository.save(cartItem);
    }

    public CartItem getCartItem(int id) throws NotFoundException {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("CartItem not found with id: " + id));
    }

    public Page<CartItem> getAllCartItems(Pageable pageable) {
        return cartItemRepository.findAll(pageable);
    }

    public CartItem updateCartItem(int id, CartItem updatedItem) throws NotFoundException {
        CartItem existing = getCartItem(id);
        existing.setQuantity(updatedItem.getQuantity());
        existing.setPriceAtSelection(updatedItem.getPriceAtSelection());
        return cartItemRepository.save(existing);
    }

    public void deleteCartItem(int id) throws NotFoundException {
        CartItem item = getCartItem(id);
        cartItemRepository.delete(item);
    }

    public Page<CartItem> findByCartId(int cartId, Pageable pageable) {
        return cartItemRepository.findByCartId(cartId, pageable);
    }
}
