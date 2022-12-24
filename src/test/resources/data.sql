INSERT INTO `emails`
(`email_id`, `description`)
VALUES
    (1, 'infominotauro@planeta.es'),
    (2, 'argentina@editorialivrea.com');


INSERT INTO `editorial`
(`editorial_id`, `name`, `business_name`, `cuit`, `city`, `address`, `telephone`, `email`)
VALUES
    (1, 'Minotauro', 'Minotauro Ediciones SA', '30-61649666-9', 'Barcelona', 'Av. Diagonal 662', 34928000, 1),
    (2, 'Ivrea', 'Editorial Ivrea S.L', '28-78762236-0', 'Buenos Aires', 'Presidente Perón 8561', 59971724, 2);


INSERT INTO `books`
(`books_id`, `title`, `author`, `publication_date`, `stock`, `editorial_id`)
VALUES
    (1, '¿Sueñan los androides con ovejas eléctricas?', 'Philip K. Dick', 1968, 50, 1),
    (2, 'Black Paradox', 'Junji Ito',  2009, 150, 2),
    (3, 'Uzumaki', 'Junji Ito',  1999, 140, 2);

