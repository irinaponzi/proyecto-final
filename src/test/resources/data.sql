INSERT INTO `emails`
(`description`)
VALUES
    ('infominotauro@planeta.es'),
    ('argentina@editorialivrea.com');


INSERT INTO `editorial`
(`name`, `business_name`, `cuit`, `city`, `address`, `telephone`, `email`)
VALUES
    ('Minotauro', 'Minotauro Ediciones SA', '30-61649666-9', 'Barcelona', 'Av. Diagonal 662', 34928000, 1),
    ('Ivrea', 'Editorial Ivrea S.L', '28-78762236-0', 'Buenos Aires', 'Presidente Perón 8561', 59971724, 2);


INSERT INTO `books`
(`title`, `author`, `publication_date`, `stock`, `editorial_id`)
VALUES
    ('¿Sueñan los androides con ovejas eléctricas?', 'Philip K. Dick', 1968, 50, 1),
    ('Black Paradox', 'Junji Ito',  2009, 150, 2),
    ('Uzumaki', 'Junji Ito',  1999, 140, 2);

