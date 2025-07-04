package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.DigitalCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DigitalCardRepository  extends JpaRepository<DigitalCard, Integer> {
    Optional<DigitalCard> findByUserId(int userId);
    boolean existsByUserId(int userId);
    Optional<DigitalCard> findByCardNumber(String cardNumber);
    long count();
}
