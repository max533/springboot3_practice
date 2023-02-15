DROP TABLE IF EXISTS good;
DROP TABLE IF EXISTS "system_user";

--- Create Good Schema ---
CREATE TABLE good
(
    id   UUID default GEN_RANDOM_UUID() primary key,
    name varchar(128) not null
);

--- Create SystemUser Schema ---
CREATE TABLE "system_user"
(
    id       UUID default GEN_RANDOM_UUID() not null primary key,
    account  varchar(128)                   not null unique,
    password varchar(128)                   not null,
    name     varchar(128)                   not null,
    role     varchar(128)                   not null
);