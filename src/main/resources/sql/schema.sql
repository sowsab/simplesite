DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `post`;
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

CREATE TABLE `post` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `content` varchar(100) NOT NULL,
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
  KEY `comment_FK` (`post_idx`),
  KEY `comment_FK_1` (`user_idx`),
  CONSTRAINT `comment_FK` FOREIGN KEY (`post_idx`) REFERENCES `post` (`idx`),
  CONSTRAINT `comment_FK_1` FOREIGN KEY (`user_idx`) REFERENCES `user` (`idx`)
);