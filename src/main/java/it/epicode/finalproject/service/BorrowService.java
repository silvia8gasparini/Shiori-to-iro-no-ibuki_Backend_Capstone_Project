package it.epicode.finalproject.service;

import it.epicode.finalproject.dto.BorrowDto;
import it.epicode.finalproject.dto.BorrowResponseDto;
import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.Book;
import it.epicode.finalproject.model.Borrow;
import it.epicode.finalproject.model.DigitalCard;
import it.epicode.finalproject.model.User;
import it.epicode.finalproject.repository.BookRepository;
import it.epicode.finalproject.repository.BorrowRepository;
import it.epicode.finalproject.repository.DigitalCardRepository;
import it.epicode.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DigitalCardRepository digitalCardRepository;

    public Borrow saveBorrow(BorrowDto dto, User user) throws NotFoundException {
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new NotFoundException("Book not found with id: " + dto.getBookId()));

        DigitalCard card = digitalCardRepository.findById(dto.getDigitalCardId())
                .orElseThrow(() -> new NotFoundException("Digital card not found with id: " + dto.getDigitalCardId()));

        Borrow borrow = new Borrow();
        borrow.setStartDate(dto.getStartDate());
        borrow.setDueDate(dto.getDueDate());
        borrow.setReturnDate(dto.getReturnDate());
        borrow.setReturned(false);
        borrow.setBook(book);
        borrow.setUser(user);
        borrow.setDigitalCard(card);

        return borrowRepository.save(borrow);
    }

    public BorrowResponseDto toDto(Borrow borrow) {
        BorrowResponseDto borrowResponseDto = new BorrowResponseDto();
        borrowResponseDto.setId(borrow.getId());
        borrowResponseDto.setStartDate(borrow.getStartDate());
        borrowResponseDto.setDueDate(borrow.getDueDate());
        borrowResponseDto.setReturned(borrow.isReturned());
        borrowResponseDto.setBookTitle(borrow.getBook().getTitle());
        borrowResponseDto.setUserName(borrow.getUser().getName());
        return borrowResponseDto;
    }

    public Borrow getBorrowById(int id) throws NotFoundException {
        return borrowRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Borrow not found with id: " + id));
    }

    public Page<Borrow> getAllBorrows(Pageable pageable) {
        return borrowRepository.findAll(pageable);
    }

    public Borrow updateBorrow(int id, Borrow updatedBorrow) throws NotFoundException {
        Borrow existingBorrow = getBorrowById(id);

        existingBorrow.setStartDate(updatedBorrow.getStartDate());
        existingBorrow.setDueDate(updatedBorrow.getDueDate());
        existingBorrow.setReturnDate(updatedBorrow.getReturnDate());
        existingBorrow.setReturned(updatedBorrow.isReturned());

        return borrowRepository.save(existingBorrow);
    }

    public void deleteBorrow(int id) throws NotFoundException {
        Borrow borrow = getBorrowById(id);
        borrowRepository.delete(borrow);
    }

    public Page<Borrow> getBorrowsByUser(int userId, Pageable pageable) {
        return borrowRepository.findByUserId(userId, pageable);
    }

    public Page<Borrow> getBorrowsByCard(int cardId, Pageable pageable) {
        return borrowRepository.findByDigitalCardId(cardId, pageable);
    }

    public Page<Borrow> getBorrowsByBook(int bookId, Pageable pageable) {
        return borrowRepository.findByBookId(bookId, pageable);
    }
}
