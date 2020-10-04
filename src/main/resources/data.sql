DELETE FROM USERS_ROLES;
DELETE FROM POSTS;
DELETE FROM ROLES;
DELETE FROM USERS;

INSERT INTO ROLES(ID, NAME) VALUES (1, 'ADMIN');
INSERT INTO ROLES(ID, NAME) VALUES (2, 'USER');

INSERT INTO USERS(ID, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES (1, 'john@email.com', 'John', 'Doe', '123456');

INSERT INTO USERS(ID, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES (2, 'jack@email.com', 'Jack', 'Sparrow', '123456');

INSERT INTO USERS(ID, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES (3, 'jane@email.com', 'Jane', 'Doe', '123456');

INSERT INTO USERS(ID, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES (4, 'jessica@email.com', 'Jessica', 'Parker', '123456');

INSERT INTO USERS(ID, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES (5, 'smartass@email.com', 'Smartass', 'Recruiter', '123456');

INSERT INTO USERS_ROLES VALUES (1,2);
INSERT INTO USERS_ROLES VALUES (2,2);
INSERT INTO USERS_ROLES VALUES (3,2);
INSERT INTO USERS_ROLES VALUES (4,2);
INSERT INTO USERS_ROLES VALUES (5,2);

INSERT INTO POSTS(TITLE, DESCRIPTION, IMAGE, AUTHOR_ID, WHEN_ADD)
VALUES ('Buy an elephant', 'Old good elephant, not expensive!', 'https://beststickers.net/image/cache/catalog/products/stickers/php-elephant-vinyl-stickers-decal-2800x1600.jpg', 1, NOW());
INSERT INTO POSTS(TITLE, DESCRIPTION, IMAGE, AUTHOR_ID, WHEN_ADD)
VALUES ('Cat is missing', 'Life is not the same without a cat!', 'https://24tv.ua/resources/photos/news/1200x675_DIR/201905/1154587.jpg?201905183220', 2, NOW());
INSERT INTO POSTS(TITLE, DESCRIPTION, IMAGE, AUTHOR_ID, WHEN_ADD)
VALUES ('The ukulele teacher', 'Ukulele is better then accordion!', 'https://i.pinimg.com/originals/50/b6/2b/50b62bfc7ba315c4f8a3c056a1960987.jpg', 3, NOW());
INSERT INTO POSTS(TITLE, DESCRIPTION, AUTHOR_ID, WHEN_ADD)
VALUES ('No image', 'At all...', 3, NOW());
INSERT INTO POSTS(TITLE, DESCRIPTION, IMAGE, AUTHOR_ID, WHEN_ADD)
VALUES ('The last one', 'Really, nothing interesting on the next page.', 'https://memegenerator.net/img/instances/65507342/why-god-why.jpg', 4, NOW());
INSERT INTO POSTS(TITLE, DESCRIPTION, IMAGE, AUTHOR_ID, WHEN_ADD)
VALUES ('Hiring  Junior Java developers', 'At list 10 years of experience, compensation $300 + free coffee', 'https://dev.by/storage/images/78/78/55/55/derived/445103f7cc308ea5b0b3f2a521fd9753.jpg', 5, NOW());
INSERT INTO POSTS(TITLE, DESCRIPTION, IMAGE, AUTHOR_ID, WHEN_ADD)
VALUES ('Look!', 'The parrot!', 'https://media-cdn.tripadvisor.com/media/photo-s/09/aa/56/87/the-parrot-place.jpg', 1, NOW());
INSERT INTO POSTS(TITLE, DESCRIPTION, IMAGE, AUTHOR_ID, WHEN_ADD)
VALUES ('The python farm', 'Come and find your pet!', 'https://content.techgig.com/photo/77759470/learn-python-by-playing-games-on-this-platform.jpg', 4, NOW());
INSERT INTO POSTS(TITLE, DESCRIPTION, IMAGE, AUTHOR_ID, WHEN_ADD)
VALUES ('Group of penguins escaped Kyiv zoo', 'Are considered armed and extremely dangerous', 'https://i.ytimg.com/vi/G603tN1i63g/maxresdefault.jpg', 2, NOW());
INSERT INTO POSTS(TITLE, DESCRIPTION, IMAGE, AUTHOR_ID, WHEN_ADD)
VALUES ('Buy an elephant', 'Old good elephant, not expensive!', 'https://beststickers.net/image/cache/catalog/products/stickers/php-elephant-vinyl-stickers-decal-2800x1600.jpg', 1, NOW());
INSERT INTO POSTS(TITLE, DESCRIPTION, IMAGE, AUTHOR_ID, WHEN_ADD)
VALUES ('Cat is missing', 'Life is not the same without a cat!', 'https://24tv.ua/resources/photos/news/1200x675_DIR/201905/1154587.jpg?201905183220', 2, NOW());
INSERT INTO POSTS(TITLE, DESCRIPTION, IMAGE, AUTHOR_ID, WHEN_ADD)
VALUES ('The ukulele teacher', 'Ukulele is better then accordion!', 'https://i.pinimg.com/originals/50/b6/2b/50b62bfc7ba315c4f8a3c056a1960987.jpg', 3, NOW());
INSERT INTO POSTS(TITLE, DESCRIPTION, IMAGE, AUTHOR_ID, WHEN_ADD)
VALUES ('The python farm', 'Come and find your pet!', 'https://content.techgig.com/photo/77759470/learn-python-by-playing-games-on-this-platform.jpg', 4, NOW());
INSERT INTO POSTS(TITLE, DESCRIPTION, IMAGE, AUTHOR_ID, WHEN_ADD)
VALUES ('Hiring  Junior Java developers', 'At list 10 years of experience, compensation $300 + free coffee', 'https://dev.by/storage/images/78/78/55/55/derived/445103f7cc308ea5b0b3f2a521fd9753.jpg', 5, NOW());
INSERT INTO POSTS(TITLE, DESCRIPTION, IMAGE, AUTHOR_ID, WHEN_ADD)
VALUES ('Look!', 'The parrot!', 'https://media-cdn.tripadvisor.com/media/photo-s/09/aa/56/87/the-parrot-place.jpg', 1, NOW());
INSERT INTO POSTS(TITLE, DESCRIPTION, IMAGE, AUTHOR_ID, WHEN_ADD)
VALUES ('Group of penguins escaped Kyiv zoo', 'Are considered armed and extremely dangerous', 'https://i.ytimg.com/vi/G603tN1i63g/maxresdefault.jpg', 2, NOW());
INSERT INTO POSTS(TITLE, DESCRIPTION, AUTHOR_ID, WHEN_ADD)
VALUES ('No image', 'At all...', 3, NOW());
INSERT INTO POSTS(TITLE, DESCRIPTION, IMAGE, AUTHOR_ID, WHEN_ADD)
VALUES ('The last one', 'Really, no more jokes', 'https://memegenerator.net/img/instances/65507342/why-god-why.jpg', 4, NOW());
INSERT INTO POSTS(TITLE, DESCRIPTION, IMAGE, AUTHOR_ID, WHEN_ADD)
VALUES ('Buy an elephant', 'Old good elephant, not expensive!', 'https://beststickers.net/image/cache/catalog/products/stickers/php-elephant-vinyl-stickers-decal-2800x1600.jpg', 1, NOW());
INSERT INTO POSTS(TITLE, DESCRIPTION, IMAGE, AUTHOR_ID, WHEN_ADD)
VALUES ('Cat is missing', 'Life is not the same without a cat!', 'https://24tv.ua/resources/photos/news/1200x675_DIR/201905/1154587.jpg?201905183220', 2, NOW());
INSERT INTO POSTS(TITLE, DESCRIPTION, IMAGE, AUTHOR_ID, WHEN_ADD)
VALUES ('The ukulele teacher', 'Ukulele is better then accordion!', 'https://i.pinimg.com/originals/50/b6/2b/50b62bfc7ba315c4f8a3c056a1960987.jpg', 3, NOW());
INSERT INTO POSTS(TITLE, DESCRIPTION, IMAGE, AUTHOR_ID, WHEN_ADD)
VALUES ('The python farm', 'Come and find your pet!', 'https://content.techgig.com/photo/77759470/learn-python-by-playing-games-on-this-platform.jpg', 4, NOW());
INSERT INTO POSTS(TITLE, DESCRIPTION, IMAGE, AUTHOR_ID, WHEN_ADD)
VALUES ('Hiring  Junior Java developers', 'At list 10 years of experience, compensation $300 + free coffee', 'https://dev.by/storage/images/78/78/55/55/derived/445103f7cc308ea5b0b3f2a521fd9753.jpg', 5, NOW());


