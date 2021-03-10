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
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: controla_secuencias_libro(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.controla_secuencias_libro() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare
	ejemplar integer;
begin
  case TG_OP
    when 'INSERT' then
      execute 'create sequence seq_ejemplares_libro_'||cast(NEW.id_libro as text);
      return new;
    when 'UPDATE' then
      IF OLD.id_libro <> NEW.id_libro THEN
	     ejemplar:=nextval('seq_ejemplares_libro_'||cast(OLD.id_libro as text));
	     execute 'drop sequence seq_ejemplares_libro_'||cast(OLD.id_libro as text);
	     execute 'create sequence seq_ejemplares_libro_'||cast(NEW.id_libro as text)|| ' start '||cast(ejemplar as text);
      END IF;
      return new;
    when 'DELETE' then 
      execute 'drop sequence seq_ejemplares_libro_'||cast(OLD.id_libro as text);
      return old;
    else null;
  end case;
end;
$$;


ALTER FUNCTION public.controla_secuencias_libro() OWNER TO postgres;

--
-- Name: get_fecha_vencimiento(date); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.get_fecha_vencimiento(fecha_prestamo date) RETURNS date
    LANGUAGE sql
    AS $$
SELECT fecha_prestamo + interval '30 days';
$$;


ALTER FUNCTION public.get_fecha_vencimiento(fecha_prestamo date) OWNER TO postgres;

--
-- Name: introduce_num_ejemplar(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.introduce_num_ejemplar() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
begin
  new.num_ejemplar:=nextval('seq_ejemplares_libro_'||cast(new.libro as text));
  return new;
end;
$$;


ALTER FUNCTION public.introduce_num_ejemplar() OWNER TO postgres;

--
-- Name: seq_libro_id_libro; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_libro_id_libro
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_libro_id_libro OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: autor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.autor (
    libro integer DEFAULT currval('public.seq_libro_id_libro'::regclass) NOT NULL,
    nombre character varying(100) NOT NULL,
    orden integer NOT NULL
);


ALTER TABLE public.autor OWNER TO postgres;

--
-- Name: cat_tiene_libro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cat_tiene_libro (
    categoria character varying(60) NOT NULL,
    libro integer DEFAULT currval('public.seq_libro_id_libro'::regclass) NOT NULL
);


ALTER TABLE public.cat_tiene_libro OWNER TO postgres;

--
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria (
    nombre character varying(60) NOT NULL,
    descripcion character varying(500)
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- Name: ejemplar; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ejemplar (
    libro integer DEFAULT currval('public.seq_libro_id_libro'::regclass) NOT NULL,
    num_ejemplar integer NOT NULL,
    ano_compra character varying(4) NOT NULL,
    localizador character varying(25) NOT NULL
);


ALTER TABLE public.ejemplar OWNER TO postgres;

--
-- Name: libro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.libro (
    id_libro integer DEFAULT nextval('public.seq_libro_id_libro'::regclass) NOT NULL,
    titulo character varying(150) NOT NULL,
    isbn character(13) NOT NULL,
    editorial character varying(100),
    paginas integer,
    ano character varying(4)
);


ALTER TABLE public.libro OWNER TO postgres;

--
-- Name: prestamo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.prestamo (
    ejemplar integer NOT NULL,
    fecha_prestamo date NOT NULL,
    libro integer NOT NULL,
    usuario character varying(10) NOT NULL,
    fecha_devolucion date
);


ALTER TABLE public.prestamo OWNER TO postgres;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id_usuario character varying(10) NOT NULL,
    clave character varying(8) NOT NULL,
    nombre character varying(150) NOT NULL,
    direccion character varying(200),
    email character varying(30) NOT NULL,
    tipo_usuario character varying(15) DEFAULT 'Normal'::character varying,
    CONSTRAINT usuario_tipo_usuario_check CHECK (((tipo_usuario)::text = ANY ((ARRAY['Normal'::character varying, 'Administrador'::character varying])::text[])))
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- Name: autor autor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.autor
    ADD CONSTRAINT autor_pkey PRIMARY KEY (libro, nombre);


--
-- Name: cat_tiene_libro cat_tiene_libro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cat_tiene_libro
    ADD CONSTRAINT cat_tiene_libro_pkey PRIMARY KEY (categoria, libro);


--
-- Name: categoria categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (nombre);


--
-- Name: ejemplar ejemplar_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ejemplar
    ADD CONSTRAINT ejemplar_pkey PRIMARY KEY (libro, num_ejemplar);


--
-- Name: libro libro_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.libro
    ADD CONSTRAINT libro_pkey PRIMARY KEY (id_libro);


--
-- Name: prestamo pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prestamo
    ADD CONSTRAINT pkey PRIMARY KEY (usuario, libro, ejemplar, fecha_prestamo);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario);


--
-- Name: libro afterlibros; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER afterlibros AFTER INSERT OR DELETE OR UPDATE ON public.libro FOR EACH ROW EXECUTE FUNCTION public.controla_secuencias_libro();


--
-- Name: ejemplar beforeinsertejemplares; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER beforeinsertejemplares BEFORE INSERT ON public.ejemplar FOR EACH ROW EXECUTE FUNCTION public.introduce_num_ejemplar();


--
-- Name: autor autor_libro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.autor
    ADD CONSTRAINT autor_libro_fkey FOREIGN KEY (libro) REFERENCES public.libro(id_libro) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: cat_tiene_libro cat_tiene_libro_categoria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cat_tiene_libro
    ADD CONSTRAINT cat_tiene_libro_categoria_fkey FOREIGN KEY (categoria) REFERENCES public.categoria(nombre) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: cat_tiene_libro cat_tiene_libro_libro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cat_tiene_libro
    ADD CONSTRAINT cat_tiene_libro_libro_fkey FOREIGN KEY (libro) REFERENCES public.libro(id_libro) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: ejemplar ejemplar_libro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ejemplar
    ADD CONSTRAINT ejemplar_libro_fkey FOREIGN KEY (libro) REFERENCES public.libro(id_libro) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: prestamo fkey_ejemplar; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prestamo
    ADD CONSTRAINT fkey_ejemplar FOREIGN KEY (ejemplar, libro) REFERENCES public.ejemplar(num_ejemplar, libro) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: prestamo fkey_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prestamo
    ADD CONSTRAINT fkey_usuario FOREIGN KEY (usuario) REFERENCES public.usuario(id_usuario) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

GRANT USAGE ON SCHEMA public TO postgres;


--
-- Name: SEQUENCE seq_libro_id_libro; Type: ACL; Schema: public; Owner: postgres
--

GRANT USAGE ON SEQUENCE public.seq_libro_id_libro TO PUBLIC;


--
-- Name: TABLE autor; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.autor TO postgres;


--
-- Name: TABLE cat_tiene_libro; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.cat_tiene_libro TO postgres;


--
-- Name: TABLE categoria; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.categoria TO postgres;


--
-- Name: TABLE ejemplar; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.ejemplar TO postgres;


--
-- Name: TABLE libro; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.libro TO postgres;


--
-- Name: TABLE prestamo; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.prestamo TO postgres;


--
-- Name: TABLE usuario; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.usuario TO postgres;


--
-- PostgreSQL database dump complete
--

