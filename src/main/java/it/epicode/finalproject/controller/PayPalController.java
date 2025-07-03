package it.epicode.finalproject.controller;

import com.paypal.orders.Order;
import it.epicode.finalproject.service.PayPalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/paypal")
public class PayPalController {

    @Autowired
    private PayPalService payPalService;
    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestParam Double amount) {
        try {
            String approvalUrl = payPalService.createOrder(amount);
            return ResponseEntity.ok(approvalUrl);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Errore durante la creazione dell’ordine PayPal");
        }
    }

    @PostMapping("/capture")
    public ResponseEntity<Order> captureOrder(@RequestParam String orderId) {
        try {
            Order order = payPalService.captureOrder(orderId);
            return ResponseEntity.ok(order);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
