package com.DeliveryPlatform.Delivery.controller;

import com.DeliveryPlatform.Delivery.entity.Task;
import com.DeliveryPlatform.Delivery.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/publish")
    @Operation(summary = "Publish a task", description = "Publish a new task with the provided task details")
    public ResponseEntity<Task> publishTask(
            @RequestBody Task task) {
        Task publishedTask = taskService.publishTask(task);
        return ResponseEntity.ok(publishedTask);
    }

    @PostMapping("/{taskId}/accept/{acceptorId}")
    @Operation(summary = "Accept a task", description = "Accept a task with the given taskId and acceptorId")
    public ResponseEntity<Task> acceptTask(
            @PathVariable("taskId") Long taskId,
            @PathVariable("acceptorId") Long acceptorId) {
        Task acceptedTask = taskService.acceptTask(taskId, acceptorId);
        if (acceptedTask != null) {
            return ResponseEntity.ok(acceptedTask);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{taskId}/complete")
    @Operation(summary = "Complete a task", description = "Mark a task as completed with the given taskId")
    public ResponseEntity<Task> completeTask(
            @PathVariable("taskId") Long taskId) {
        Task completedTask = taskService.completeTask(taskId);
        if (completedTask != null) {
            return ResponseEntity.ok(completedTask);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{taskId}/cancel")
    @Operation(summary = "Cancel a task", description = "Cancel a task with the given taskId")
    public ResponseEntity<Task> cancelTask(
            @PathVariable("taskId") Long taskId) {
        Task canceledTask = taskService.cancelTask(taskId);
        if (canceledTask != null) {
            return ResponseEntity.ok(canceledTask);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{taskId}")
    @Operation(summary = "Get task by ID", description = "Get the details of a task with the given taskId")
    public ResponseEntity<Task> getTaskById(
            @PathVariable("taskId") Long taskId) {
        Task task = taskService.getTaskById(taskId);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get tasks by user ID", description = "Get all tasks published or accepted by the given userId")
    public ResponseEntity<List<Task>> getUserTasks(
            @PathVariable("userId") Long userId) {
        List<Task> tasks = taskService.getUserTasks(userId);
        return ResponseEntity.ok(tasks);
    }

    // 其他API方法
}
