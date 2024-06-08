package com.DeliveryPlatform.Delivery.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.DeliveryPlatform.Delivery.entity.Task;

import java.util.List;

public interface TaskService extends IService<Task> {
    Task publishTask(Task task);
    Task acceptTask(Long taskId, Long acceptorId);
    Task completeTask(Long taskId);
    Task cancelTask(Long taskId);
    Task getTaskById(Long taskId);
    List<Task> getUserTasks(Long userId);
    // 定义其他业务方法
}
