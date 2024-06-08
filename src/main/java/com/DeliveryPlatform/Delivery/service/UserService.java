package com.DeliveryPlatform.Delivery.service;

import com.DeliveryPlatform.Delivery.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;
import com.DeliveryPlatform.Delivery.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService extends IService<User> {
    ResponseEntity<String> registerUser(User user);

    ResponseEntity<User> loginUser(String username, String password);

    ResponseEntity<User> getUserByUsername(String username);

    ResponseEntity<String> updateUser(String username, User user);
    // 定义其他业务方法
}
