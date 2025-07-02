package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.DigitalCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DigitalCardRepository  extends JpaRepository<DigitalCard, Integer> {
    // Trova una tessera digitale associata a uno specifico utente
    Optional<DigitalCard> findByUserId(int userId);

    // Verifica se esiste già una tessera per quell'utente
    boolean existsByUserId(int userId);

    // Cerca una tessera tramite il numero identificativo (es. SHI-2024-0001)
    Optional<DigitalCard> findByCardNumber(String cardNumber);

    // Conta quante tessere ci sono
    long count();
}
