package it.epicode.finalproject.controller;

import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.DigitalCard;
import it.epicode.finalproject.service.DigitalCardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/digital-cards")
public class DigitalCardController {

    @Autowired
    private DigitalCardService digitalCardService;

    @PostMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public DigitalCard createCard(@PathVariable int userId) throws NotFoundException {
        return digitalCardService.createCard(userId);
    }

    @GetMapping("")
    public Page<DigitalCard> getAllCards(Pageable pageable) {
        return digitalCardService.getAllCards(pageable);
    }

    @GetMapping("/{id}")
    public DigitalCard getCardById(@PathVariable int id) throws NotFoundException {
        return digitalCardService.getCardById(id);
    }

    @GetMapping("/by-number/{cardNumber}")
    public DigitalCard getByCardNumber(@PathVariable String cardNumber) throws NotFoundException {
        return digitalCardService.getByCardNumber(cardNumber);
    }

    @GetMapping("/by-user/{userId}")
    public DigitalCard getByUserId(@PathVariable int userId) throws NotFoundException {
        return digitalCardService.getCardByUserId(userId);
    }

    @PutMapping("/{id}")
    public DigitalCard updateCard(@PathVariable int id, @RequestBody @Valid DigitalCard updatedCard)
            throws NotFoundException {
        return digitalCardService.updateCard(id, updatedCard);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCard(@PathVariable int id) throws NotFoundException {
        digitalCardService.deleteCard(id);
    }
}
