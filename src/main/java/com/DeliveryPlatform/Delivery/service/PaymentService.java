package com.DeliveryPlatform.Delivery.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.DeliveryPlatform.Delivery.entity.Payment;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService extends IService<Payment> {
    Payment createPayment(Long taskId, BigDecimal amount);
    List<Payment> getUserPayments(Long userId);
    Payment updatePaymentStatus(Long paymentId, String status);
    boolean hasPaymentForTask(Long taskId);
    // 可以定义其他业务方法
}
