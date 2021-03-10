Insert into categoria values ('Bases de Datos', 'Libros con contenidos relacionados con la tecnolog�a de Sistemas Gestores de Bases de Datos');
Insert into categoria values ('Programaci�n', 'Libros relacionados con lenguajes y t�cnicas de programaci�n');


Insert into libro (titulo,isbn,editorial,paginas, ano) values ('Fundamentos de bases de datos', '84-481-3654-3', 'McGraw Hill', 787, '2002');
Insert into autor (nombre, orden) values ('A. Silberschatz', 1);
Insert into autor (nombre, orden) values ('H.F. Korth', 2);
Insert into autor (nombre, orden) values ('S. Sudarshan', 3);
Insert into cat_tiene_libro (categoria) values ('Bases de Datos');
Insert into ejemplar (ano_compra, localizador) 
	values('2004','prxd34');
Insert into ejemplar (ano_compra, localizador) 
	values('2005','prxd45');

Insert into libro (titulo,isbn,editorial,paginas, ano) values ('An introduction to database systems','0-321-18956-6', 'Pearson/Addison-Wesley', 983, '2003');
Insert into autor (nombre, orden) values ('C.J. Date', 1);
Insert into cat_tiene_libro (categoria) values ('Bases de Datos');
Insert into ejemplar (ano_compra, localizador) 
	values('2004','dsadt3');
Insert into ejemplar (ano_compra, localizador) 
	values( '2008','dsadt56');

Insert into libro (titulo,isbn,editorial,paginas, ano) values ('Programaci�n en Java','1-235-25345-6', 'Springer', 300, '2005');
Insert into autor (nombre, orden) values ('A.P. Sanchez', 1);
Insert into autor (nombre, orden) values ('N. Theodoridou', 2);
Insert into cat_tiene_libro (categoria) values ('Programaci�n');
Insert into ejemplar (ano_compra, localizador) 
	values('2010','fgf5457');

Insert into libro (titulo,isbn,editorial,paginas, ano) values ('Desarrollo de aplicaciones con PHP y PostgreSQL','0-123-12345-6', 'Idea Group Inc.', 120,  '2004');
Insert into autor (nombre, orden) values ('N. Theodoridou', 1);
Insert into cat_tiene_libro (categoria) values ('Bases de Datos');
Insert into cat_tiene_libro (categoria) values ('Programaci�n');
Insert into ejemplar (ano_compra, localizador) 
	values( '2005','dsa3g65');
Insert into ejemplar( ano_compra, localizador) 
	values('2011','dsa3g89');	


insert into usuario values ('JAlvarez','pja','Juan Alvarez', 'C Blanco Amor', 'JAlvarez@bibdb.es', 'Administrador');
insert into usuario values ('ERomero','per','Elisa Romero', 'C Rosalia de Castro', 'ERomero@comp1.com', 'Normal');
insert into usuario values ('FPerez','pfp','Francisco Perez', 'C del olvido', 'novotom@locamost.org', 'Normal');
insert into usuario values ('SSuarez','pss','Sara Suarez', 'C de la rosa', 'ss34@alo.com', 'Normal');


INSERT INTO public.ejemplar (libro, num_ejemplar, ano_compra, localizador) VALUES (2, 1, '2004', 'dsadt3');
INSERT INTO public.ejemplar (libro, num_ejemplar, ano_compra, localizador) VALUES (2, 2, '2008', 'dsadt56');
INSERT INTO public.ejemplar (libro, num_ejemplar, ano_compra, localizador) VALUES (2, 4, '2003', 'dsadt555');
INSERT INTO public.ejemplar (libro, num_ejemplar, ano_compra, localizador) VALUES (1, 1, '2004', 'prxd34');
INSERT INTO public.ejemplar (libro, num_ejemplar, ano_compra, localizador) VALUES (1, 2, '2005', 'prxd45');
INSERT INTO public.ejemplar (libro, num_ejemplar, ano_compra, localizador) VALUES (3, 2, '2017', '123');
INSERT INTO public.ejemplar (libro, num_ejemplar, ano_compra, localizador) VALUES (4, 1, '2005', 'dsa3g65');
INSERT INTO public.ejemplar (libro, num_ejemplar, ano_compra, localizador) VALUES (4, 2, '2011', 'dsa3g89');


INSERT INTO public.prestamo (ejemplar, fecha_prestamo, libro, usuario, fecha_devolucion) VALUES (2, '2021-01-07', 2, 'ERomero', '2021-03-09');
INSERT INTO public.prestamo (ejemplar, fecha_prestamo, libro, usuario, fecha_devolucion) VALUES (2, '2021-03-09', 2, 'JAlvarez', '2021-03-09');
INSERT INTO public.prestamo (ejemplar, fecha_prestamo, libro, usuario, fecha_devolucion) VALUES (2, '2021-03-09', 2, 'FPerez', '2021-03-09');
INSERT INTO public.prestamo (ejemplar, fecha_prestamo, libro, usuario, fecha_devolucion) VALUES (1, '2021-03-09', 2, 'JAlvarez', '2021-03-09');
INSERT INTO public.prestamo (ejemplar, fecha_prestamo, libro, usuario, fecha_devolucion) VALUES (1, '2021-03-09', 4, 'FPerez', '2021-03-09');
INSERT INTO public.prestamo (ejemplar, fecha_prestamo, libro, usuario, fecha_devolucion) VALUES (2, '2021-03-09', 1, 'FPerez', NULL);
INSERT INTO public.prestamo (ejemplar, fecha_prestamo, libro, usuario, fecha_devolucion) VALUES (2, '2021-03-09', 4, 'FPerez', '2021-03-09');