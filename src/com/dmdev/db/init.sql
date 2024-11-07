/**
ПОЛЬЗОВАТЕЛЬ может быть автором многих НОВОСТЕЙ, либо вообще не быть автором
ПОЛЬЗОВАТЕЛЬ может оставлять много КОММЕНТАРИЕВ, либо вообще не оставлять
ПОЛЬЗОВАТЕЛь может иметь только одну РОЛЬ (модератор, user, guest)
ПОЛЬЗОВАТЕЛЬ может иметь несколько ПРАВ доступа (чтение, создание, редактирование, удаление, может быть комментарирование) для НОВОСТЕЙ и КОММЕНТАРИЕВ
НОВОСТЬ может иметь только одноо автора - ПОЛЬЗОВАТЕЛЯ
НОВОСТЬ может иметь много КОММЕНТАРИЕВ
НОВОСТЬ может иметь только один статус в одно время
КОММЕНТАРИЙ может принадлежать только одной НОВОСТИ
КОММЕНТАРИЙ может иметь только одного автора - ПОЛЬЗОВАТЕЛЯ
КОММЕНТАРИЙ может иметь только один статус в одно время

Ораничения:
1. Неавторизованный пользователь (guest) не может редактировать новости и оставлять комментарии
2. Обычный пользователь не может удалять и редактировать новости и комментарии

Действия по добавлению:
1. Добавить в систему нового ПОЛЬЗОВАТЕЛЯ
2. Добавить в систему НОВОСТЬ
3. Добавить в систему КОММЕНТАРИЙ

Действия по просмотру:
1. Посмтореть список всех пользователей системы
2. Посмотреть список всех новостей системы
3. Посмотреть список новостей созданных определенным пользователем
4. Посмотреть список всех комментариев к новости
5. Посмотреть список комментариев созданных определенным пользователем
6. Посмотреть новости по определенной дате
7. Посмотреть все новости по определенной категории
8. Удаление старых новостей
*/

CREATE DATABASE news_repository_main;

CREATE TABLE user_right
(
    id SERIAL PRIMARY KEY ,
    user_right VARCHAR(128) NOT NULL
);

CREATE TABLE role
(
    id SERIAL PRIMARY KEY ,
    role VARCHAR(128) NOT NULL
);

CREATE TABLE status
(
    id SERIAL PRIMARY KEY,
    status VARCHAR(128) NOT NULL
);

CREATE TABLE portal_user
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(128)                   NOT NULL,
    last_name  VARCHAR(128)                   NOT NULL,
    nickname   VARCHAR(256) UNIQUE            NOT NULL,
    email      VARCHAR(256)                   NOT NULL,
    password   VARCHAR(128)                   NOT NULL,
    image      VARCHAR(256),
    right_id   INT REFERENCES user_right (id) NOT NULL,
    role_id    INT REFERENCES role (id)       NOT NULL UNIQUE
    -- news_id    BIGINT REFERENCES news (id),
    -- comment_id BIGINT REFERENCES comment (id)
);

CREATE TABLE news
(
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(128)               NOT NULL,
    description VARCHAR(256)               NOT NULL,
    content     TEXT                       NOT NULL,
    created_at  TIMESTAMP                  NOT NULL,
    updated_at  TIMESTAMP,
    category    VARCHAR(128)               NOT NULL,
    image       VARCHAR(256),
    status_id   INT REFERENCES status (id) NOT NULL UNIQUE
    --  comment_id  BIGINT REFERENCES comment (id),
    -- user_id     INT REFERENCES portal_user (id) NOT NULL UNIQUE
);

CREATE TABLE comment
(
    id         BIGSERIAL PRIMARY KEY,
    content    TEXT                            NOT NULL,
    created_at TIMESTAMP                       NOT NULL,
    updated_at TIMESTAMP,
    attachment BYTEA,
    news_id    BIGINT REFERENCES news (id)     NOT NULL UNIQUE,
    user_id    INT REFERENCES portal_user (id) NOT NULL UNIQUE,
    status_id  INT REFERENCES status (id)      NOT NULL UNIQUE
);

ALTER TABLE portal_user
    ADD COLUMN news_id BIGINT REFERENCES news (id);

ALTER TABLE portal_user
    ADD COLUMN comment_id BIGINT REFERENCES comment (id);

ALTER TABLE news
    ADD COLUMN comment_id BIGINT REFERENCES comment (id);

ALTER TABLE news
    ADD COLUMN user_id INT REFERENCES portal_user (id) NOT NULL UNIQUE;
