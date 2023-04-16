CREATE TABLE `tb_classe` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_guild` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nivel` int NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4wv83hfajry5tdoamn8wsqa6x` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_c9lijtmr0x68iu1vxftbu2u33` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_user_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  KEY `FKft1jmfcluls775jqp5142wvl8` (`role_id`),
  KEY `FK19t64ocsol5x06fy2cytp7gey` (`user_id`),
  CONSTRAINT `FK19t64ocsol5x06fy2cytp7gey` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FKft1jmfcluls775jqp5142wvl8` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_player` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `alt` bit(1) NOT NULL,
  `ativo` bit(1) NOT NULL,
  `nivel` int NOT NULL,
  `nick` varchar(255) NOT NULL,
  `power` decimal(19,2) NOT NULL,
  `principal` bit(1) NOT NULL,
  `qtd_codex` int NOT NULL,
  `classe_id` bigint DEFAULT NULL,
  `guild_id` bigint DEFAULT NULL,
  `player_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6ydwwx6pmrdtjmdyi5a2e07kn` (`nick`),
  KEY `FKm0nh5kc4gdntbu5b79cvhrnhf` (`classe_id`),
  KEY `FK4ouyx3a5m5ry5lr1xy64ebv2k` (`guild_id`),
  KEY `FK3kelt6nvau88e08j4bt3elffu` (`player_user`),
  CONSTRAINT `FK3kelt6nvau88e08j4bt3elffu` FOREIGN KEY (`player_user`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FK4ouyx3a5m5ry5lr1xy64ebv2k` FOREIGN KEY (`guild_id`) REFERENCES `tb_guild` (`id`),
  CONSTRAINT `FKm0nh5kc4gdntbu5b79cvhrnhf` FOREIGN KEY (`classe_id`) REFERENCES `tb_classe` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;