INSERT INTO `user` (`idx`, `email`, `id`, `password`)
VALUES (1, 'test@test.com', 'test', '123'),
       (2, 'test2@test.com', 'test2', '123');

INSERT INTO `post` (`idx`, `title`, `content`, `user_idx`, `create_date`, `update_date`, `delete_date`)
VALUES (1, '안녕하세요', '안녕하지 못해요', 1, NOW(), NULL, NULL),
       (2, '안녕하지못해요', '사실 안녕해요', 1, NOW(), NULL, NULL),
       (3, 'ㅎㅇ', 'ㅎㅇ', 2, NOW(), NULL, NULL);


INSERT INTO `comment`
(`idx`, `content`, `post_idx`, `user_idx`, `create_date`, `update_date`, `delete_date`)
VALUES (1, 'ㅎㅇ', 1, 2, NOW(), NULL, NULL),
       (2, 'ㅎㅇ', 3, 1, NOW(), NULL, NULL),
       (3, '뉘신지?', 1, 1, NOW(), NULL, NULL),
       (4, 'ㅎㅇ', 3, 1, NOW(), NULL, NULL),
       (5, 'ㅎㅇ', 3, 1, NOW(), NULL, NULL);
