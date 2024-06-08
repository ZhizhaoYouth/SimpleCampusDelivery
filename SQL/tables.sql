CREATE DATEBASE campus_delivery;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `task` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `description` text,
  `publisher_id` int(11) DEFAULT NULL,
  `acceptor_id` int(11) DEFAULT NULL,
  `status` enum('pending','ongoing','completed','cancelled') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`task_id`),
  KEY `publisher_id` (`publisher_id`),
  KEY `acceptor_id` (`acceptor_id`),
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`publisher_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `task_ibfk_2` FOREIGN KEY (`acceptor_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) DEFAULT NULL,
  `amount` decimal(10,2) NOT NULL,
  `status` enum('pending','completed') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`payment_id`),
  KEY `task_id` (`task_id`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 插入用户数据
INSERT INTO `user` (`username`, `password`, `full_name`, `email`, `phone_number`) VALUES
('user1', 'password1', 'User One', 'user1@example.com', '1234567890'),
('user2', 'password2', 'User Two', 'user2@example.com', '9876543210'),
('user3', 'password3', 'User Three', 'user3@example.com', '1111111111'),
('user4', 'password4', 'User Four', 'user4@example.com', '2222222222'),
('user5', 'password5', 'User Five', 'user5@example.com', '3333333333');

-- 插入任务数据
INSERT INTO `task` (`title`, `description`, `publisher_id`, `acceptor_id`, `status`) VALUES
('Task 1', 'Description for Task 1', 1, 2, 'completed'),
('Task 2', 'Description for Task 2', 2, 1, 'completed'),
('Task 3', 'Description for Task 3', 1, NULL, 'pending'),
('Task 4', 'Description for Task 4', 2, NULL, 'pending'),
('Task 5', 'Description for Task 5', 3, 4, 'ongoing'),
('Task 6', 'Description for Task 6', 4, 3, 'completed'),
('Task 7', 'Description for Task 7', 3, NULL, 'pending'),
('Task 8', 'Description for Task 8', 4, NULL, 'cancelled'),
('Task 9', 'Description for Task 9', 3, 2, 'ongoing'),
('Task 10', 'Description for Task 10', 2, 5, 'completed');

--插入订单数据
INSERT INTO `payment` (`task_id`, `amount`, `status`) VALUES
(1, 50.50, 'completed'),
(10, 75.80, 'pending');