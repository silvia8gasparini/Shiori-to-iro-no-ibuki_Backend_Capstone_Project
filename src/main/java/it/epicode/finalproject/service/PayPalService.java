package it.epicode.finalproject.service;

import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Service
public class PayPalService {

    @Autowired
    private PayPalHttpClient payPalHttpClient;

    public String createOrder(Double amount) throws IOException {
        System.out.println("➡️ Creo ordine PayPal per importo: " + amount);
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        AmountWithBreakdown amountWithBreakdown = new AmountWithBreakdown()
                .currencyCode("EUR")
                .value(String.format(Locale.US, "%.2f", amount));
        System.out.println("➡️ Importo formattato: " + amountWithBreakdown.value());

        PurchaseUnitRequest purchaseUnit = new PurchaseUnitRequest()
                .amountWithBreakdown(amountWithBreakdown);

        orderRequest.purchaseUnits(List.of(purchaseUnit));

        ApplicationContext applicationContext = new ApplicationContext()
                .returnUrl("http://localhost:5173/payment-success")
                .cancelUrl("http://localhost:5173/payment-cancel")
                .brandName("Shiori to iro no ibuki")
                .landingPage("BILLING")
                .userAction("PAY_NOW");

        orderRequest.applicationContext(applicationContext);

        OrdersCreateRequest request = new OrdersCreateRequest()
                .prefer("return=representation")
                .requestBody(orderRequest);

        System.out.println("➡️ Invio richiesta a PayPal...");

        HttpResponse<Order> response = payPalHttpClient.execute(request); // 👈 punto critico

        System.out.println("✅ Risposta ricevuta, status: " + response.statusCode());

        for (LinkDescription link : response.result().links()) {
            if ("approve".equalsIgnoreCase(link.rel())) {
                System.out.println("✅ URL approvazione trovato: " + link.href());
                return link.href();
            }
        }
        throw new IllegalStateException("Approval URL non trovata");
    }

    public Order captureOrder(String orderId) throws IOException {
        OrdersCaptureRequest request = new OrdersCaptureRequest(orderId);

        return payPalHttpClient.execute(request).result();
    }
}
