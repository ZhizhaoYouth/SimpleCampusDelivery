package com.DeliveryPlatform.Delivery.controller;

import com.DeliveryPlatform.Delivery.entity.Payment;
import com.DeliveryPlatform.Delivery.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{taskId}/create")
    @Operation(summary = "Create payment", description = "Create a new payment for the specified task with the provided amount")
    public ResponseEntity<Payment> createPayment(
            @PathVariable("taskId") Long taskId,
            @RequestParam("amount") BigDecimal amount) {
        Payment payment = paymentService.createPayment(taskId, amount);
        if (payment != null) {
            return ResponseEntity.ok(payment);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get user payments", description = "Retrieve all payments made by the specified user")
    public ResponseEntity<List<Payment>> getUserPayments(
            @PathVariable("userId") Long userId) {
        List<Payment> payments = paymentService.getUserPayments(userId);
        return ResponseEntity.ok(payments);
    }

    @PutMapping("/{paymentId}/update/{status}")
    @Operation(summary = "Update payment status", description = "Update the status of the specified payment")
    public ResponseEntity<Payment> updatePaymentStatus(
            @PathVariable("paymentId") Long paymentId,
            @PathVariable("status") String status) {
        Payment updatedPayment = paymentService.updatePaymentStatus(paymentId, status);
        if (updatedPayment != null) {
            return ResponseEntity.ok(updatedPayment);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // 其他API方法
}
