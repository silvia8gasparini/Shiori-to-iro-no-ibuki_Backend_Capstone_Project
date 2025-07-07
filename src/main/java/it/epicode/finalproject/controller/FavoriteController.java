package it.epicode.finalproject.controller;

import it.epicode.finalproject.dto.FavoriteDto;
import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.User;
import it.epicode.finalproject.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/toggle/{bookId}")
    public boolean toggleFavorite(@PathVariable int bookId, @AuthenticationPrincipal User user) throws NotFoundException {
        return favoriteService.toggleFavorite(user, bookId);
    }

    @GetMapping("/me")
    public List<FavoriteDto> getMyFavorites(@AuthenticationPrincipal User user) {
        return favoriteService.getFavoritesByUser(user.getId());
    }

    @GetMapping("/me/contains/{bookId}")
    public boolean isFavorite(@PathVariable int bookId, @AuthenticationPrincipal User user) {
        return favoriteService.isFavorite(user.getId(), bookId);
    }
}
