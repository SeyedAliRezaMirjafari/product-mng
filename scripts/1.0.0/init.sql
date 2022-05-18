CREATE TABLE `product`
(
    `id`                     bigint       NOT NULL AUTO_INCREMENT,
    `active`                 bit(1)       NOT NULL,
    `code`                   varchar(255) NOT NULL,
    `commentable`            int          DEFAULT NULL,
    `creation_time`          datetime     DEFAULT NULL,
    `description`            varchar(255) DEFAULT NULL,
    `image`                  varchar(255) DEFAULT NULL,
    `public_visible_comment` bit(1)       NOT NULL,
    `title`                  varchar(255) DEFAULT NULL,
    `visible`                bit(1)       NOT NULL,
    `voteable`               int          DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_h3w5r1mx6d0e5c6um32dgyjej` (`code`),
    KEY                      `product_code_visible_active` (`active`,`code`,`visible`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `comment`
(
    `id`                       bigint       NOT NULL AUTO_INCREMENT,
    `creation_time`            datetime     NOT NULL,
    `message`                  varchar(255) DEFAULT NULL,
    `modification_status_time` datetime     DEFAULT NULL,
    `status`                   int          DEFAULT NULL,
    `user_id`                  varchar(255) NOT NULL,
    `product_id`               bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                        `FKm1rmnfcvq5mk26li4lit88pc5` (`product_id`),
    KEY                        `comment_product_status_modification` (`modification_status_time`,`status`,`product_id`) USING BTREE,
    CONSTRAINT `FKm1rmnfcvq5mk26li4lit88pc5` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `vote`
(
    `id`            bigint       NOT NULL AUTO_INCREMENT,
    `creation_time` datetime     NOT NULL,
    `score`         int          NOT NULL,
    `status`        int    DEFAULT NULL,
    `user_id`       varchar(255) NOT NULL,
    `product_id`    bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY             `FKh5477qurpc725lhy6ky00onkc` (`product_id`),
    KEY             `vote_status_product` (`status`,`product_id`) USING BTREE,
    CONSTRAINT `FKh5477qurpc725lhy6ky00onkc` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `provider`
(
    `id`   bigint       NOT NULL AUTO_INCREMENT,
    `code` varchar(255) NOT NULL,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_fql8wc9usg5icu8ngn40oqf5j` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `product_providers`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `product_id`  bigint NOT NULL,
    `provider_id` bigint NOT NULL,
    PRIMARY KEY (`id`),
    KEY           `FKcpqd9g5ts1pc8v5eoic3ivpb8` (`provider_id`),
    KEY           `FKtjc117dhg4rs86gkkvy1hrbbw` (`product_id`),
    CONSTRAINT `FKcpqd9g5ts1pc8v5eoic3ivpb8` FOREIGN KEY (`provider_id`) REFERENCES `provider` (`id`),
    CONSTRAINT `FKtjc117dhg4rs86gkkvy1hrbbw` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;







