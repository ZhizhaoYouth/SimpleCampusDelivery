package com.DeliveryPlatform.Delivery.service.impl;

import com.DeliveryPlatform.Delivery.entity.Task;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.DeliveryPlatform.Delivery.dao.PaymentMapper;
import com.DeliveryPlatform.Delivery.entity.Payment;
import com.DeliveryPlatform.Delivery.service.PaymentService;
import com.DeliveryPlatform.Delivery.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {
    @Autowired
    private TaskService taskService;

    public Payment createPayment(Long taskId, BigDecimal amount) {
        if (hasPaymentForTask(taskId)) {
            return null;
        }
        Task task = taskService.getById(taskId);
        if (task != null && task.getStatus().equals("completed")) {
            Payment payment = new Payment();
            payment.setTaskId(taskId);
            payment.setAmount(amount); // 使用传入的金额
            payment.setStatus("pending");
            payment.setCreatedAt(LocalDateTime.now());
            save(payment);
            return payment;
        } else {
            return null;
        }
    }

    @Override
    public List<Payment> getUserPayments(Long userId) {
        List<Task> userTasks = taskService.list(new QueryWrapper<Task>().eq("publisher_id", userId).or().eq("acceptor_id", userId));
        List<Long> taskIds = userTasks.stream().map(Task::getTaskId).collect(Collectors.toList());
        return list(new QueryWrapper<Payment>().in("task_id", taskIds));
    }
    @Override
    public Payment updatePaymentStatus(Long paymentId, String status) {
        Payment payment = getById(paymentId);
        if (payment != null) {
            payment.setStatus(status);
            updateById(payment);
            return payment;
        } else {
            return null;
        }
    }
    @Override
    public boolean hasPaymentForTask(Long taskId) {
        long count = count(new QueryWrapper<Payment>().eq("task_id", taskId));
        return count > 0;
    }
    // 可以在这里实现其他业务方法
}
