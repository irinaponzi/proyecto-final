INSERT INTO `emails`
(`description`)
VALUES
    ('infominotauro@planeta.es'),
    ('info@cajanegraeditora.com.ar'),
    ('alianzaeditorial@alianzaeditorial.es'),
    ('info@edicioneslariviere.com'),
    ('argentina@editorialivrea.com');


INSERT INTO `editorial`
(`name`, `business_name`, `cuit`, `city`, `address`, `telephone`, `email`)
VALUES
    ('Minotauro', 'Minotauro Ediciones SA', '30-61649666-9', 'Barcelona', 'Av. Diagonal 662', 34928000, 1),
    ('Caja Negra', 'Editora Caja Negra SA', '34-50506969-7', 'Buenos Aires', 'Castillo 1486', 45877440, 2),
    ('Alianza', 'Alianza Editorial SA', '37-44440050-0', 'Madrid', 'C. de J.I Luca de Tena 15', 13938888, 3),
    ('Larivière', 'Ediciones Larivière', '20-89651144-5', 'Buenos Aires', 'Talcahuano 768', 43728383, 4),
    ('Ivrea', 'Editorial Ivrea S.L', '28-78762236-0', 'Buenos Aires', 'Presidente Perón 8561', 59971724, 5);


INSERT INTO `books`
(`title`, `author`, `publication_date`, `stock`, `editorial_id`)
VALUES
    ('¿Sueñan los androides con ovejas eléctricas?', 'Philip K. Dick', 1968, 50, 1),
    ('Ciudad de ilusiones', 'Ursula K. Le Guin', 1967, 10, 1),
    ('Mucho después de medianoche', 'Ray Bradbury', 1976, 15, 1),
    ('Todas las fiestas de mañana', 'William Gibson', 2007, 80, 1),
    ('Guía de películas anime de Ghiblioteca', 'Jake Cunningham', 2022, 100, 1),
    ('K-Punk Volumen 1', 'Mark Fisher', 2019, 20, 2),
    ('K-Punk Volumen 2', 'Mark Fisher', 2020, 25, 2),
    ('La Siliconización del mundo', 'Éric Sadin', 2020, 30, 2),
    ('La inteligencia artificial o el desafío del siglo', 'Éric Sadin', 2020, 10, 2),
    ('¿Qué es la Ilustración?', 'Immanuel Kant', 1784, 5, 3),
    ('Así habló Zaratustra', 'Friedrich Nietzsche', 1883, 25, 3),
    ('La genealogía de la moral', 'Friedrich Nietzsche', 1887, 15, 3),
    ('Tractatus logico-philosophicus', 'Ludwig Wittgenstein', 1921, 15, 3),
    ('El Silencio', 'Humberto Rivas', 2018, 40, 4),
    ('Argentinos', 'Marcos Zimmermann', 2019, 15, 4),
    ('Destiempos', 'Sara Facio', 1997, 20, 4),
    ('Dragon Ball Tomo 2', 'Akira Toriyama', 1995, 150, 5),
    ('Neon Genesis Evangelion Tomo 5', 'Yoshiyuki Sadamoto', 1994, 200, 5),
    ('Neon Genesis Evangelion Tomo 6', 'Yoshiyuki Sadamoto', 1994, 50, 5),
    ('Jojos bizarre adventures', 'Hirohiko Araki', 1987, 15, 5),
    ('Black Paradox', 'Junji Ito',  2009, 150, 5),
    ('Uzumaki', 'Junji Ito',  1999, 140, 5);

