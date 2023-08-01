INSERT INTO `user` (`idx`, `email`, `id`, `password`)
VALUES (1, 'test@test.com', 'test', '123'),
       (2, 'test2@test.com', 'test2', '123');

INSERT INTO `post` (`idx`, `title`, `content`, `user_idx`, `create_date`, `update_date`, `delete_date`)
VALUES (1, '안녕하세요', '안녕하지 못해요', 1, '2023-08-01 13:26:43.000', NULL, NULL),
       (2, '안녕하지못해요', '사실 안녕해요', 1, '2023-08-01 13:27:15.000', NULL, NULL),
       (3, 'ㅎㅇ', 'ㅎㅇ', 2, '2023-08-01 13:35:26.000', NULL, NULL);


INSERT INTO `comment`
(`idx`, `content`, `post_idx`, `user_idx`, `create_date`, `update_date`, `delete_date`)
VALUES (1, 'ㅎㅇ', 1, 2, '2023-08-01 13:37:58.000', NULL, NULL),
       (2, 'ㅎㅇ', 3, 1, '2023-08-01 13:38:19.000', NULL, NULL);
