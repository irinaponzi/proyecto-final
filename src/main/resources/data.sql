INSERT INTO `emails`
(`email_id`, `description`)
VALUES
    (1, 'infominotauro@planeta.es'),
    (2, 'info@cajanegraeditora.com.ar'),
    (3, 'alianzaeditorial@alianzaeditorial.es'),
    (4, 'info@edicioneslariviere.com'),
    (5, 'argentina@editorialivrea.com');


INSERT INTO `editorial`
(`editorial_id`, `name`, `business_name`, `cuit`, `city`, `address`, `telephone`, `email`)
VALUES
    (1, 'Minotauro', 'Minotauro Ediciones SA', '30-61649666-9', 'Barcelona', 'Av. Diagonal 662', 34928000, 1),
    (2, 'Caja Negra', 'Editora Caja Negra SA', '34-50506969-7', 'Buenos Aires', 'Castillo 1486', 45877440, 2),
    (3, 'Alianza', 'Alianza Editorial SA', '37-44440050-0', 'Madrid', 'C. de J.I Luca de Tena 15', 13938888, 3),
    (4, 'Larivière', 'Ediciones Larivière', '20-89651144-5', 'Buenos Aires', 'Talcahuano 768', 43728383, 4),
    (5, 'Ivrea', 'Editorial Ivrea S.L', '28-78762236-0', 'Buenos Aires', 'Presidente Perón 8561', 59971724, 5);


INSERT INTO `books`
(`books_id`, `title`, `author`, `publication_date`, `stock`, `editorial_id`)
VALUES
    (1, '¿Sueñan los androides con ovejas eléctricas?', 'Philip K. Dick', 1968, 50, 1),
    (2, 'Ciudad de ilusiones', 'Ursula K. Le Guin', 1967, 10, 1),
    (3, 'Mucho después de medianoche', 'Ray Bradbury', 1976, 15, 1),
    (4, 'Todas las fiestas de mañana', 'William Gibson', 2007, 80, 1),
    (5, 'Guía de películas anime de Ghiblioteca', 'Jake Cunningham', 2022, 100, 1),
    (6, 'K-Punk Volumen 1', 'Mark Fisher', 2019, 20, 2),
    (7, 'K-Punk Volumen 2', 'Mark Fisher', 2020, 25, 2),
    (8, 'La Siliconización del mundo', 'Éric Sadin', 2020, 30, 2),
    (9, 'La inteligencia artificial o el desafío del siglo', 'Éric Sadin', 2020, 10, 2),
    (10, '¿Qué es la Ilustración?', 'Immanuel Kant', 1784, 5, 3),
    (11, 'Así habló Zaratustra', 'Friedrich Nietzsche', 1883, 25, 3),
    (12, 'La genealogía de la moral', 'Friedrich Nietzsche', 1887, 15, 3),
    (13, 'Tractatus logico-philosophicus', 'Ludwig Wittgenstein', 1921, 15, 3),
    (14, 'El Silencio', 'Humberto Rivas', 2018, 40, 4),
    (15, 'Argentinos', 'Marcos Zimmermann', 2019, 15, 4),
    (16, 'Destiempos', 'Sara Facio', 1997, 20, 4),
    (17, 'Dragon Ball Tomo 2', 'Akira Toriyama', 1995, 150, 5),
    (18, 'Neon Genesis Evangelion Tomo 5', 'Yoshiyuki Sadamoto', 1994, 200, 5),
    (19, 'Neon Genesis Evangelion Tomo 6', 'Yoshiyuki Sadamoto', 1994, 50, 5),
    (20, 'Jojos bizarre adventures', 'Hirohiko Araki', 1987, 15, 5),
    (21, 'Black Paradox', 'Junji Ito',  2009, 150, 5),
    (22, 'Uzumaki', 'Junji Ito',  1999, 140, 5);

