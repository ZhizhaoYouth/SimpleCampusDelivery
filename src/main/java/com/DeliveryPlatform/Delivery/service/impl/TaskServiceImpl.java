package com.DeliveryPlatform.Delivery.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.DeliveryPlatform.Delivery.dao.TaskMapper;
import com.DeliveryPlatform.Delivery.entity.Task;
import com.DeliveryPlatform.Delivery.service.TaskService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {
    @Override
    public Task publishTask(Task task) {
        task.setStatus("pending");
        task.setCreatedAt(LocalDateTime.now());
        save(task);
        return task;
    }

    @Override
    public Task acceptTask(Long taskId, Long acceptorId) {
        Task task = getById(taskId);
        if (task != null && task.getStatus().equals("pending")) {
            task.setStatus("ongoing");
            task.setAcceptorId(acceptorId);
            updateById(task);
        }
        return task;
    }

    @Override
    public Task completeTask(Long taskId) {
        Task task = getById(taskId);
        if (task != null && task.getStatus().equals("ongoing")) {
            task.setStatus("completed");
            updateById(task);
        }
        return task;
    }

    @Override
    public Task cancelTask(Long taskId) {
        Task task = getById(taskId);
        if (task != null && task.getStatus().equals("pending")) {
            task.setStatus("cancelled");
            updateById(task);
        }
        return task;
    }

    @Override
    public Task getTaskById(Long taskId) {
        return getById(taskId);
    }

    @Override
    public List<Task> getUserTasks(Long userId) {
        return list(new QueryWrapper<Task>().eq("publisher_id", userId).or().eq("acceptor_id", userId));
    }
    // 可以在这里实现其他业务方法
}
