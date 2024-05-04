# DROP TABLE IF EXISTS `ExternalLink`;
# DROP TABLE IF EXISTS `Group`;
# DROP TABLE IF EXISTS `EmbeddedWebsite`;
# DROP TABLE IF EXISTS `Layout`;
# DROP TABLE IF EXISTS `user`;

-- 初始化用户表
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
                        `username` VARCHAR(50) NOT NULL COMMENT '用户名',
                        `password` VARCHAR(255) NOT NULL COMMENT '密码',
                        `phone_number` VARCHAR(20) COMMENT '电话号码',
                        `registration_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册日期',
                        `last_login` TIMESTAMP NULL COMMENT '最后登录时间',
                        `is_active` BOOLEAN NOT NULL DEFAULT TRUE COMMENT '是否激活',
                        `is_admin` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '是否管理员'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 布局表
DROP TABLE IF EXISTS `Layout`;
CREATE TABLE `Layout` (
                          `layout_id` INT PRIMARY KEY AUTO_INCREMENT, -- 布局ID，主键，自动递增
                          `user_id` INT,                              -- 用户ID，外键，关联User表的user_id字段
                          `layout_name` VARCHAR(255) NOT NULL,        -- 布局名称，不能为空
                          `layout_content` TEXT,                      -- 布局内容，可以存储大段文本
                          FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 嵌入网站表
DROP TABLE IF EXISTS `EmbeddedWebsite`;
CREATE TABLE `EmbeddedWebsite` (
                                   `website_id` INT PRIMARY KEY AUTO_INCREMENT, -- 网站ID，主键，自动递增
                                   `user_id` INT,                               -- 用户ID，外键，关联User表的user_id字段
                                   `website_name` VARCHAR(255) NOT NULL,        -- 网站名称，不能为空
                                   `website_url` VARCHAR(255) NOT NULL,         -- 网站URL，不能为空
                                   `layout_id` INT,                             -- 所属布局ID，外键，关联Layout表的layout_id字段
                                   FOREIGN KEY (`user_id`) REFERENCES `user`(`id`),
                                   FOREIGN KEY (`layout_id`) REFERENCES `Layout`(`layout_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 分组表
DROP TABLE IF EXISTS `Group`;
CREATE TABLE `Group` (
                         `group_id` INT PRIMARY KEY AUTO_INCREMENT,   -- 分组ID，主键，自动递增
                         `user_id` INT,                              -- 用户ID，外键，关联User表的user_id字段
                         `group_name` VARCHAR(255) NOT NULL,         -- 分组名称，不能为空
                         `group_description` TEXT,                   -- 分组描述，可以存储大段文本
                         FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 外链表
DROP TABLE IF EXISTS `ExternalLink`;
CREATE TABLE `ExternalLink` (
                                `link_id` INT PRIMARY KEY AUTO_INCREMENT,    -- 外链ID，主键，自动递增
                                `link_name` VARCHAR(255) NOT NULL,          -- 外链名称，不能为空
                                `link_url` VARCHAR(255) NOT NULL,           -- 外链URL，不能为空
                                `is_system_defined` BOOLEAN NOT NULL DEFAULT FALSE,  -- 是否系统内置，默认为FALSE
                                `user_id` INT,                              -- 用户ID，外键，关联User表的user_id字段，如果是用户自定义的外链
                                FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;