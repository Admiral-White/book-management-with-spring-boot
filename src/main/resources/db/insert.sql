SET FOREIGN_KEY_CHECKS  = 0;

truncate table author;
truncate table book;

INSERT into author(`id`, `age`, `genre`, `name`)
VALUES(21, 46, 'Gospel', 'Derick Prince'),
       (22, 54,'Mathematics', 'Kola killer'),
      (23, 57, 'Fantasy', 'Java and Sharks');

INSERT INTO book(`id`, `isbn`, `title`, `author_id`)
VALUES (31, '098-234-123', 'the battle for life', '21'),
       (32,'987-223-887', 'The fundamentals of Calculus','22'),
       (37, '101-234-897', 'Life in Semicolon', '23');

SET FOREIGN_KEY_CHECKS  = 1;
