DROP DATABASE IF EXISTS BoardgameMaker;
CREATE DATABASE BoardgameMaker CHAR SET UTF8 COLLATE utf8_general_ci;

USE BoardgameMaker;

CREATE TABLE USER
(
    id       BIGINT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    username VARCHAR(80)     NOT NULL,
    password VARCHAR(300)    NOT NULL
);

CREATE TABLE GAME
(
    id          BIGINT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    gameData    LONGBLOB        NOT NULL,
    description TEXT            NOT NULL,
    idUserAdmin BIGINT UNSIGNED NOT NULL
);


ALTER TABLE USER
    ADD CONSTRAINT UK_USER_1_USERNAME
        UNIQUE (username);

ALTER TABLE GAME
    ADD CONSTRAINT FK_GAME_1_USER
        FOREIGN KEY (idUserAdmin)
            REFERENCES USER (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

-- MASTER USER FOR THE DB
DROP USER IF EXISTS BoardgameMaker_Master;
CREATE USER BoardgameMaker_Master
    IDENTIFIED BY "bgm_m";
GRANT ALL ON BoardgameMaker.* TO BoardgameMaker_Master;

-- USER FOR WEBSITE ACTING
DROP USER IF EXISTS BoardgameMaker_Web;
CREATE USER BoardgameMaker_Web
    IDENTIFIED BY "bgm_w";
GRANT SHOW VIEW ON BoardgameMaker.* TO BoardgameMaker_Web;
GRANT ALL ON BoardgameMaker.USER TO BoardgameMaker_Web;
GRANT ALL ON BoardgameMaker.GAME TO BoardgameMaker_Web;