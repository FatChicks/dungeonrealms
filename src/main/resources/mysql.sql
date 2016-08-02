CREATE TABLE IF NOT EXISTS `players`
(
  player_id  INT                  AUTO_INCREMENT,
  uuid       VARCHAR(36) NOT NULL,
  username   VARCHAR(17) NOT NULL,
  level      INT(11)     NOT NULL DEFAULT 0,
  experience DOUBLE      NOT NULL DEFAULT 0.0,
  PRIMARY KEY (player_id)
);
CREATE TABLE IF NOT EXISTS `player_cache`
(
  player_id INT         NOT NULL,
  world     VARCHAR(10) NOT NULL DEFAULT 'world',
  x         DOUBLE      NOT NULL DEFAULT 0,
  y         DOUBLE      NOT NULL DEFAULT 0,
  z         DOUBLE      NOT NULL DEFAULT 0,
  yaw       FLOAT       NOT NULL DEFAULT 0,
  pitch     FLOAT       NOT NULL DEFAULT 0
);
CREATE TABLE IF NOT EXISTS `player_achievements`
(
  ID            INT AUTO_INCREMENT,
  player_id     INT    NOT NULL,
  achievementId INT    NOT NULL,
  time          BIGINT NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS `achievements`
(
  ID              INT AUTO_INCREMENT,
  achievementName VARCHAR(20) NOT NULL,
  achievmentDesc  MEDIUMTEXT  NOT NULL,
  PRIMARY KEY (ID)
);