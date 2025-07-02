package it.epicode.finalproject.service;

import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.DigitalCard;
import it.epicode.finalproject.model.User;
import it.epicode.finalproject.repository.DigitalCardRepository;
import it.epicode.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DigitalCardService {
    @Autowired
    private DigitalCardRepository digitalCardRepository;

    @Autowired
    private UserRepository userRepository;

    public DigitalCard createCard(int userId) throws NotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        if (digitalCardRepository.existsByUserId(userId)) {
            throw new IllegalArgumentException("Digital card already exists for user with id: " + userId);
        }

        DigitalCard card = new DigitalCard();
        card.setUser(user);
        card.setIssuedAt(LocalDate.now());

        String year = String.valueOf(LocalDate.now().getYear());
        String cardNumber = "SHI-" + year + "-" + String.format("%04d", userId);
        card.setCardNumber(cardNumber);

        return digitalCardRepository.save(card);
    }

    public DigitalCard getCardById(int id) throws NotFoundException {
        return digitalCardRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Card not found with id: " + id));
    }

    public DigitalCard getByCardNumber(String cardNumber) throws NotFoundException {
        return digitalCardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new NotFoundException("Digital card not found with number: " + cardNumber));
    }

    public Page<DigitalCard> getAllCards(Pageable pageable) {
        return digitalCardRepository.findAll(pageable);
    }

    public DigitalCard getCardByUserId(int userId) throws NotFoundException {
        return digitalCardRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Card not found for user with id: " + userId));
    }

    public DigitalCard updateCard(int id, DigitalCard updatedCard) throws NotFoundException {
        DigitalCard card = getCardById(id);
        card.setCardNumber(updatedCard.getCardNumber());
        card.setIssuedAt(updatedCard.getIssuedAt());
        return digitalCardRepository.save(card);
    }

    public void deleteCard(int id) throws NotFoundException {
        DigitalCard card = getCardById(id);
        digitalCardRepository.delete(card);
    }

}
