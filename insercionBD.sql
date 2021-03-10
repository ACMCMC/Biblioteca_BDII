--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1 (Debian 13.1-1.pgdg100+1)
-- Dumped by pg_dump version 13.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', 'public', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: libro; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.libro (id_libro, titulo, isbn, editorial, paginas, ano) VALUES (2, 'An introduction to database systems', '0-321-18956-6', 'Pearson/Addison-Wesley', 983, '2003');
INSERT INTO public.libro (id_libro, titulo, isbn, editorial, paginas, ano) VALUES (4, 'Desarrollo de aplicaciones con PHP y PostgreSQL', '0-123-12345-6', 'Idea Group Inc.', 120, '2004');
INSERT INTO public.libro (id_libro, titulo, isbn, editorial, paginas, ano) VALUES (5, 'Memorias de Idhun', '123456789    ', 'Espasa', 150, '2018');
INSERT INTO public.libro (id_libro, titulo, isbn, editorial, paginas, ano) VALUES (3, 'Programación en Java', '1-235-25345-6', 'Springer', 300, '2005');
INSERT INTO public.libro (id_libro, titulo, isbn, editorial, paginas, ano) VALUES (1, 'Fundamentos de bases de datos', '84-481-3654-3', 'McGraw Hill', 787, '2002');


--
-- Data for Name: autor; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.autor (libro, nombre, orden) VALUES (2, 'C.J. Date', 1);
INSERT INTO public.autor (libro, nombre, orden) VALUES (4, 'N. Theodoridou', 1);
INSERT INTO public.autor (libro, nombre, orden) VALUES (5, 'Iván Castro', 1);
INSERT INTO public.autor (libro, nombre, orden) VALUES (3, 'A.P. Sanchez', 1);
INSERT INTO public.autor (libro, nombre, orden) VALUES (3, 'N. Theodoridou', 2);
INSERT INTO public.autor (libro, nombre, orden) VALUES (1, 'A. Silberschatz', 1);
INSERT INTO public.autor (libro, nombre, orden) VALUES (1, 'H.F. Korth', 2);
INSERT INTO public.autor (libro, nombre, orden) VALUES (1, 'S. Sudarshan', 3);


--
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.categoria (nombre, descripcion) VALUES ('Cocina', 'Libros relacionados con la cocina');
INSERT INTO public.categoria (nombre, descripcion) VALUES ('Programación', 'Libros relacionados con lenguajes y técnicas de programación');
INSERT INTO public.categoria (nombre, descripcion) VALUES ('Bases de Datos', 'Libros con contenidos relacionados con la tecnología de Sistemas Gestores de Bases de Datos');


--
-- Data for Name: cat_tiene_libro; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cat_tiene_libro (categoria, libro) VALUES ('Bases de Datos', 1);
INSERT INTO public.cat_tiene_libro (categoria, libro) VALUES ('Bases de Datos', 5);
INSERT INTO public.cat_tiene_libro (categoria, libro) VALUES ('Cocina', 5);
INSERT INTO public.cat_tiene_libro (categoria, libro) VALUES ('Bases de Datos', 4);
INSERT INTO public.cat_tiene_libro (categoria, libro) VALUES ('Programación', 4);
INSERT INTO public.cat_tiene_libro (categoria, libro) VALUES ('Bases de Datos', 2);
INSERT INTO public.cat_tiene_libro (categoria, libro) VALUES ('Programación', 3);


--
-- Data for Name: ejemplar; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ejemplar (libro, num_ejemplar, ano_compra, localizador) VALUES (2, 1, '2004', 'dsadt3');
INSERT INTO public.ejemplar (libro, num_ejemplar, ano_compra, localizador) VALUES (2, 2, '2008', 'dsadt56');
INSERT INTO public.ejemplar (libro, num_ejemplar, ano_compra, localizador) VALUES (2, 4, '2003', 'dsadt555');
INSERT INTO public.ejemplar (libro, num_ejemplar, ano_compra, localizador) VALUES (2, 5, '2003', 'dsadt5556');
INSERT INTO public.ejemplar (libro, num_ejemplar, ano_compra, localizador) VALUES (1, 1, '2004', 'prxd34');
INSERT INTO public.ejemplar (libro, num_ejemplar, ano_compra, localizador) VALUES (1, 2, '2005', 'prxd45');
INSERT INTO public.ejemplar (libro, num_ejemplar, ano_compra, localizador) VALUES (3, 2, '2017', '123');
INSERT INTO public.ejemplar (libro, num_ejemplar, ano_compra, localizador) VALUES (5, 1, '2019', '1234');
INSERT INTO public.ejemplar (libro, num_ejemplar, ano_compra, localizador) VALUES (4, 1, '2005', 'dsa3g65');
INSERT INTO public.ejemplar (libro, num_ejemplar, ano_compra, localizador) VALUES (4, 2, '2011', 'dsa3g89');


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.usuario (id_usuario, clave, nombre, direccion, email, tipo_usuario) VALUES ('PRodriguez', 'prd', 'Pedro Rodríguez', 'Isaac Newton, 23', 'p.rodr@mail.com', 'Normal');
INSERT INTO public.usuario (id_usuario, clave, nombre, direccion, email, tipo_usuario) VALUES ('JAlvarez', 'pja', 'Juan Alvarez', 'C Blanco Amor', 'JAlvarez@bibdb.es', 'Administrador');
INSERT INTO public.usuario (id_usuario, clave, nombre, direccion, email, tipo_usuario) VALUES ('FPerez', 'pfp', 'Francisco Pérez', 'C del olvido', 'novotom@locamost.org', 'Normal');
INSERT INTO public.usuario (id_usuario, clave, nombre, direccion, email, tipo_usuario) VALUES ('ERomero', 'per', 'Elisa Romero', 'C Rosalia de Castro', 'ERomero@comp1.com', 'Normal');
INSERT INTO public.usuario (id_usuario, clave, nombre, direccion, email, tipo_usuario) VALUES ('CIvan', 'ivc', 'Iván Castro', 'Calle de Prueba, 23', 'ivan.castro@mail.com', 'Administrador');


--
-- Data for Name: prestamo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.prestamo (ejemplar, fecha_prestamo, libro, usuario, fecha_devolucion) VALUES (2, '2021-01-07', 2, 'ERomero', '2021-03-09');
INSERT INTO public.prestamo (ejemplar, fecha_prestamo, libro, usuario, fecha_devolucion) VALUES (4, '2021-01-09', 2, 'JAlvarez', '2021-03-09');
INSERT INTO public.prestamo (ejemplar, fecha_prestamo, libro, usuario, fecha_devolucion) VALUES (2, '2021-03-09', 2, 'JAlvarez', '2021-03-09');
INSERT INTO public.prestamo (ejemplar, fecha_prestamo, libro, usuario, fecha_devolucion) VALUES (2, '2021-03-09', 2, 'FPerez', '2021-03-09');
INSERT INTO public.prestamo (ejemplar, fecha_prestamo, libro, usuario, fecha_devolucion) VALUES (1, '2021-03-09', 2, 'JAlvarez', '2021-03-09');
INSERT INTO public.prestamo (ejemplar, fecha_prestamo, libro, usuario, fecha_devolucion) VALUES (1, '2021-03-09', 4, 'FPerez', '2021-03-09');
INSERT INTO public.prestamo (ejemplar, fecha_prestamo, libro, usuario, fecha_devolucion) VALUES (2, '2021-03-09', 1, 'FPerez', NULL);
INSERT INTO public.prestamo (ejemplar, fecha_prestamo, libro, usuario, fecha_devolucion) VALUES (1, '2021-03-09', 5, 'PRodriguez', NULL);
INSERT INTO public.prestamo (ejemplar, fecha_prestamo, libro, usuario, fecha_devolucion) VALUES (1, '2021-03-09', 4, 'PRodriguez', NULL);
INSERT INTO public.prestamo (ejemplar, fecha_prestamo, libro, usuario, fecha_devolucion) VALUES (2, '2021-03-09', 4, 'FPerez', '2021-03-09');
INSERT INTO public.prestamo (ejemplar, fecha_prestamo, libro, usuario, fecha_devolucion) VALUES (2, '2021-03-09', 4, 'CIvan', NULL);


--
-- Name: seq_ejemplares_libro_1; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_ejemplares_libro_1', 2, true);


--
-- Name: seq_ejemplares_libro_2; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_ejemplares_libro_2', 5, true);


--
-- Name: seq_ejemplares_libro_3; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_ejemplares_libro_3', 2, true);


--
-- Name: seq_ejemplares_libro_4; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_ejemplares_libro_4', 2, true);


--
-- Name: seq_ejemplares_libro_5; Type: SEQUENCE SET; Schema: public; Owner: alumnogreibd
--

SELECT pg_catalog.setval('public.seq_ejemplares_libro_5', 1, true);


--
-- Name: seq_libro_id_libro; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_libro_id_libro', 6, true);


--
-- PostgreSQL database dump complete
--


