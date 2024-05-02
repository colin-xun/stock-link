-- 初始化用户表
DROP TABLE IF EXISTS `userVO`;
CREATE TABLE `userVO` (
                        `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
                        `username` VARCHAR(50) NOT NULL COMMENT '用户名',
                        `password` VARCHAR(255) NOT NULL COMMENT '密码',
                        `phone_number` VARCHAR(20) COMMENT '电话号码',
                        `registration_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册日期',
                        `last_login` TIMESTAMP NULL COMMENT '最后登录时间',
                        `is_active` BOOLEAN NOT NULL DEFAULT TRUE COMMENT '是否激活',
                        `is_admin` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '是否管理员'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
