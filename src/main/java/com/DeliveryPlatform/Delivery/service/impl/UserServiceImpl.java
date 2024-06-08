package com.DeliveryPlatform.Delivery.service.impl;

import com.DeliveryPlatform.Delivery.dao.TaskMapper;
import com.DeliveryPlatform.Delivery.entity.Task;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.DeliveryPlatform.Delivery.dao.UserMapper;
import com.DeliveryPlatform.Delivery.entity.User;
import com.DeliveryPlatform.Delivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public ResponseEntity<String> registerUser(User user) {
        // 检查用户名是否已存在
        if (userMapper.selectCount(new QueryWrapper<User>().eq("username", user.getUsername())) > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }

        // 校验邮箱格式是否合法
        if (!isValidEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email address");
        }

        // 校验密码强度
        if (!isValidPassword(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password is too weak");
        }

        // 加密密码
        String encryptedPassword = encryptPassword(user.getPassword());
        user.setPassword(encryptedPassword);

        // 设置默认值或者将null值转换为相应的默认值
        if (user.getFullName() == null) {
            user.setFullName("");
        }
        if (user.getPhoneNumber() == null) {
            user.setPhoneNumber("");
        }

        // 保存用户信息到数据库
        userMapper.insert(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
    // 检查邮箱格式是否合法
    private boolean isValidEmail(String email) {
        // 在实际项目中应该使用正则表达式进行更严格的校验
        return email != null && email.contains("@");
    }

    // 校验密码强度
    private boolean isValidPassword(String password) {
        // 在实际项目中应该根据具体需求定义密码的复杂度要求
        return password != null && password.length() >= 6;
    }

    // 对密码进行加密（示例，实际项目中应使用更安全的加密方式，如BCrypt）
    private String encryptPassword(String password) {
        // 在实际项目中应使用安全的加密算法，如BCrypt
        return password;
    }
    @Override
    public ResponseEntity<User> loginUser(String username, String password) {
        // 根据用户名查询用户信息
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 用户不存在
        }

        // 检查密码是否匹配
        if (!password.equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 密码不匹配
        }

        return ResponseEntity.ok(user); // 登录成功
    }

    @Override
    public ResponseEntity<User> getUserByUsername(String username) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<String> updateUser(String username, User user) {
        // 根据用户名查询用户信息
        User existingUser = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if (existingUser == null) {
            return ResponseEntity.notFound().build(); // 用户不存在
        }

        // 更新用户信息
        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhoneNumber(user.getPhoneNumber());

        // 保存更新后的用户信息到数据库
        userMapper.updateById(existingUser);

        return ResponseEntity.ok("User information updated successfully");
    }


    // 可以在这里实现其他业务方法
}
