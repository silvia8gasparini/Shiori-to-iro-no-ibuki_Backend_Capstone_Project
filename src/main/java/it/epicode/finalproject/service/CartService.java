package it.epicode.finalproject.service;

import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.Cart;
import it.epicode.finalproject.model.User;
import it.epicode.finalproject.repository.CartRepository;
import it.epicode.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    public Cart saveCart(Cart cart, int userId) throws NotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        cart.setUser(user);
        cart.setLastUpdated(LocalDateTime.now());

        return cartRepository.save(cart);
    }

    public Cart getCart(int id) throws NotFoundException {
        return cartRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cart not found with id: " + id));
    }

    public Page<Cart> getAllCarts(Pageable pageable) {
        return cartRepository.findAll(pageable);
    }

    public Cart updateCart(int id, Cart updatedCart) throws NotFoundException {
        Cart existing = getCart(id);

        existing.setCheckedOut(updatedCart.isCheckedOut());
        existing.setLastUpdated(LocalDateTime.now());

        return cartRepository.save(existing);
    }

    public void deleteCart(int id) throws NotFoundException {
        Cart cart = getCart(id);
        cartRepository.delete(cart);
    }

    public Page<Cart> findByUserId(int userId, Pageable pageable) {
        return cartRepository.findByUserId(userId, pageable);
    }
}
