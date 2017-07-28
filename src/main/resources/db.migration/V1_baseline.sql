
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `birthday` datetime NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `passwordHash` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `User_email_uindex` (`email`),
  UNIQUE KEY `User_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE portal.User ALTER COLUMN role SET DEFAULT "USER";

ALTER TABLE portal.User_Role CHANGE User_userId User_Id VARCHAR(255) NOT NULL;