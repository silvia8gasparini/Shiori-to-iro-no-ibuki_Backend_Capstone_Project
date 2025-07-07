package it.epicode.finalproject.service;

import it.epicode.finalproject.dto.FavoriteDto;
import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.Book;
import it.epicode.finalproject.model.Favorite;
import it.epicode.finalproject.model.User;
import it.epicode.finalproject.repository.BookRepository;
import it.epicode.finalproject.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepo;

    @Autowired
    private BookRepository bookRepo;

    public boolean toggleFavorite(User user, int bookId) throws NotFoundException {
        int userId = user.getId();

        if (favoriteRepo.existsByUserIdAndBookId(userId, bookId)) {
            favoriteRepo.deleteByUserIdAndBookId(userId, bookId);
            return false;
        } else {
            Book book = bookRepo.findById(bookId)
                    .orElseThrow(() -> new NotFoundException("Libro non trovato"));

            Favorite favorite = new Favorite();
            favorite.setUser(user);
            favorite.setBook(book);

            favoriteRepo.save(favorite);
            return true;
        }
    }

    public List<FavoriteDto> getFavoritesByUser(int userId) {
        return favoriteRepo.findByUserId(userId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public boolean isFavorite(int userId, int bookId) {
        return favoriteRepo.existsByUserIdAndBookId(userId, bookId);
    }

    private FavoriteDto mapToDto(Favorite favorite) {
        Book book = favorite.getBook();

        FavoriteDto favoriteDto = new FavoriteDto();
        favoriteDto.setBookId(book.getId());
        favoriteDto.setTitle(book.getTitle());
        favoriteDto.setAuthor(book.getAuthor());
        favoriteDto.setImageUrl(book.getImageUrl());

        return favoriteDto;
    }
}
