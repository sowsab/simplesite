DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `post`;
DROP TABLE IF EXISTS `user_role`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `id` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`idx`),
  UNIQUE KEY `user_un_email` (`email`),
  UNIQUE KEY `user_un_id` (`id`)
);

CREATE TABLE `user_role` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `user_idx` int(11) NOT NULL,
  `role` varchar(100) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`idx`),
  KEY `user_role_FK` (`user_idx`),
  CONSTRAINT `user_role_FK` FOREIGN KEY (`user_idx`) REFERENCES `user` (`idx`) ON DELETE CASCADE,
  CONSTRAINT `user_role_check` CHECK (`role` in ('ADMIN','USER'))
);

CREATE TABLE `post` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `content` varchar(255) NOT NULL,
  `user_idx` int(11) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_date` timestamp NULL DEFAULT NULL,
  `delete_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `post_FK` (`user_idx`),
  CONSTRAINT `post_FK` FOREIGN KEY (`user_idx`) REFERENCES `user` (`idx`)
);

CREATE TABLE `comment` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(100) NOT NULL,
  `post_idx` int(11) NOT NULL,
  `user_idx` int(11) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_date` timestamp NULL DEFAULT NULL,
  `delete_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `comment_FK_1` (`user_idx`),
  KEY `comment_FK` (`post_idx`),
  CONSTRAINT `comment_FK` FOREIGN KEY (`post_idx`) REFERENCES `post` (`idx`) ON DELETE CASCADE,
  CONSTRAINT `comment_FK_1` FOREIGN KEY (`user_idx`) REFERENCES `user` (`idx`)
);