DROP TABLE IF EXISTS public.case CASCADE;
DROP TABLE IF EXISTS public.autori CASCADE;
DROP TABLE IF EXISTS public.ruoli CASCADE;
DROP TABLE IF EXISTS public.libri CASCADE;
DROP TABLE IF EXISTS public.film CASCADE;
DROP TABLE IF EXISTS public.videogiochi CASCADE;
DROP TABLE IF EXISTS public.scritto_da CASCADE;
DROP TABLE IF EXISTS public.crew_film CASCADE;
DROP TABLE IF EXISTS public.crew_videogioco CASCADE;
DROP TABLE IF EXISTS public.tag CASCADE;
DROP TABLE IF EXISTS public.libri_assegnamento_tag CASCADE;
DROP TABLE IF EXISTS public.film_assegnamento_tag CASCADE;
DROP TABLE IF EXISTS public.videogiochi_assegnamento_tag CASCADE;
DROP TABLE IF EXISTS public.utenti CASCADE;
DROP TABLE IF EXISTS public.videogiochi_giocati CASCADE;
DROP TABLE IF EXISTS public.libri_letti CASCADE;
DROP TABLE IF EXISTS public.film_visti CASCADE;
DROP TABLE IF EXISTS public.recensioni CASCADE;



--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4 (Debian 17.4-1.pgdg120+2)
-- Dumped by pg_dump version 17.4 (Debian 17.4-1.pgdg120+2)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: autori; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.autori (
    autore_id integer NOT NULL,
    nome text NOT NULL,
    secondo_nome text,
    cognome text NOT NULL,
    data_di_nascita date NOT NULL,
    nazionalita text NOT NULL
);


ALTER TABLE public.autori OWNER TO "postgresMaster";

--
-- Name: autori_autore_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.autori_autore_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.autori_autore_id_seq OWNER TO "postgresMaster";

--
-- Name: autori_autore_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.autori_autore_id_seq OWNED BY public.autori.autore_id;


--
-- Name: case; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public."case" (
    casa_id integer NOT NULL,
    nome text NOT NULL,
    nazionalita text NOT NULL
);


ALTER TABLE public."case" OWNER TO "postgresMaster";

--
-- Name: casa_casa_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.casa_casa_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.casa_casa_id_seq OWNER TO "postgresMaster";

--
-- Name: casa_casa_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.casa_casa_id_seq OWNED BY public."case".casa_id;


--
-- Name: crew_film; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.crew_film (
    crew_id integer NOT NULL,
    film_id integer NOT NULL,
    autore_id integer NOT NULL,
    ruolo_id integer NOT NULL
);


ALTER TABLE public.crew_film OWNER TO "postgresMaster";

--
-- Name: crew_videogioco; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.crew_videogioco (
    crew_id integer NOT NULL,
    videogioco_id integer NOT NULL,
    autore_id integer NOT NULL,
    ruolo_id integer NOT NULL
);


ALTER TABLE public.crew_videogioco OWNER TO "postgresMaster";

--
-- Name: film; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.film (
    film_id integer NOT NULL,
    titolo text NOT NULL,
    durata integer NOT NULL,
    data_di_pubblicazione date NOT NULL,
    descrizione text NOT NULL,
    casa_di_produzione integer NOT NULL,
    anno_di_pubblicazione date NOT NULL,
    voto integer NOT NULL,
    immagine_di_copertina text NOT NULL
);


ALTER TABLE public.film OWNER TO "postgresMaster";

--
-- Name: film_assegnamento_tag; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.film_assegnamento_tag (
    assegnamento_id integer NOT NULL,
    tag_id integer NOT NULL,
    film_id integer NOT NULL
);


ALTER TABLE public.film_assegnamento_tag OWNER TO "postgresMaster";

--
-- Name: film_assegnamento_tag_assegnamento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.film_assegnamento_tag_assegnamento_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.film_assegnamento_tag_assegnamento_id_seq OWNER TO "postgresMaster";

--
-- Name: film_assegnamento_tag_assegnamento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.film_assegnamento_tag_assegnamento_id_seq OWNED BY public.film_assegnamento_tag.assegnamento_id;


--
-- Name: film_film_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.film_film_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.film_film_id_seq OWNER TO "postgresMaster";

--
-- Name: film_film_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.film_film_id_seq OWNED BY public.film.film_id;


--
-- Name: film_prodotto_da_prodotto_da_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.film_prodotto_da_prodotto_da_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.film_prodotto_da_prodotto_da_id_seq OWNER TO "postgresMaster";

--
-- Name: film_prodotto_da_prodotto_da_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.film_prodotto_da_prodotto_da_id_seq OWNED BY public.crew_film.crew_id;


--
-- Name: film_visti; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.film_visti (
    film_visto_id integer NOT NULL,
    film_id integer NOT NULL,
    utente_id integer NOT NULL,
    recensione_id integer
);


ALTER TABLE public.film_visti OWNER TO "postgresMaster";

--
-- Name: film_visti_film_visti_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.film_visti_film_visti_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.film_visti_film_visti_id_seq OWNER TO "postgresMaster";

--
-- Name: film_visti_film_visti_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.film_visti_film_visti_id_seq OWNED BY public.film_visti.film_visto_id;


--
-- Name: libri; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.libri (
    libro_id integer NOT NULL,
    titolo text NOT NULL,
    numero_di_pagine integer DEFAULT 0 NOT NULL,
    casa_produttrice_id integer NOT NULL,
    descrizione text NOT NULL,
    anno_di_pubblicazione date NOT NULL,
    voto integer DEFAULT 0 NOT NULL,
    immagine_di_copertina text NOT NULL
);


ALTER TABLE public.libri OWNER TO "postgresMaster";

--
-- Name: libri_assegnamento_tag; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.libri_assegnamento_tag (
    assegnamento_id integer NOT NULL,
    tag_id integer NOT NULL,
    libro_id integer NOT NULL
);


ALTER TABLE public.libri_assegnamento_tag OWNER TO "postgresMaster";

--
-- Name: libri_assegnamento_tag_assegnamento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.libri_assegnamento_tag_assegnamento_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.libri_assegnamento_tag_assegnamento_id_seq OWNER TO "postgresMaster";

--
-- Name: libri_assegnamento_tag_assegnamento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.libri_assegnamento_tag_assegnamento_id_seq OWNED BY public.libri_assegnamento_tag.assegnamento_id;


--
-- Name: libri_letti; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.libri_letti (
    libro_letto_id integer NOT NULL,
    libro_id integer NOT NULL,
    utente_id integer NOT NULL,
    recensione_id integer
);


ALTER TABLE public.libri_letti OWNER TO "postgresMaster";

--
-- Name: libri_letti_libri_letti_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.libri_letti_libri_letti_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.libri_letti_libri_letti_id_seq OWNER TO "postgresMaster";

--
-- Name: libri_letti_libri_letti_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.libri_letti_libri_letti_id_seq OWNED BY public.libri_letti.libro_letto_id;


--
-- Name: libri_libro_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.libri_libro_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.libri_libro_id_seq OWNER TO "postgresMaster";

--
-- Name: libri_libro_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.libri_libro_id_seq OWNED BY public.libri.libro_id;


--
-- Name: recensioni; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.recensioni (
    recensione_id integer NOT NULL,
    voto integer NOT NULL,
    recensione text NOT NULL,
    CONSTRAINT "voto valido" CHECK ((voto <= 100))
);


ALTER TABLE public.recensioni OWNER TO "postgresMaster";

--
-- Name: recensioni_recensioni_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.recensioni_recensioni_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.recensioni_recensioni_id_seq OWNER TO "postgresMaster";

--
-- Name: recensioni_recensioni_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.recensioni_recensioni_id_seq OWNED BY public.recensioni.recensione_id;


--
-- Name: ruoli; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.ruoli (
    ruolo_id integer NOT NULL,
    nome text NOT NULL
);


ALTER TABLE public.ruoli OWNER TO "postgresMaster";

--
-- Name: ruoli_ruolo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.ruoli_ruolo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.ruoli_ruolo_id_seq OWNER TO "postgresMaster";

--
-- Name: ruoli_ruolo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.ruoli_ruolo_id_seq OWNED BY public.ruoli.ruolo_id;


--
-- Name: scritto_da; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.scritto_da (
    made_by_id integer NOT NULL,
    autore_id integer NOT NULL,
    libro_id integer NOT NULL,
    autore_primario boolean NOT NULL
);


ALTER TABLE public.scritto_da OWNER TO "postgresMaster";

--
-- Name: scritto_da_made_by_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.scritto_da_made_by_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.scritto_da_made_by_id_seq OWNER TO "postgresMaster";

--
-- Name: scritto_da_made_by_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.scritto_da_made_by_id_seq OWNED BY public.scritto_da.made_by_id;


--
-- Name: tag; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.tag (
    tag_id integer NOT NULL,
    tag_name text NOT NULL,
    genere boolean DEFAULT false NOT NULL
);


ALTER TABLE public.tag OWNER TO "postgresMaster";

--
-- Name: tag_tag_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.tag_tag_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tag_tag_id_seq OWNER TO "postgresMaster";

--
-- Name: tag_tag_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.tag_tag_id_seq OWNED BY public.tag.tag_id;


--
-- Name: utenti; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.utenti (
    utente_id integer NOT NULL,
    nome text NOT NULL,
    password text NOT NULL,
    email text NOT NULL
);


ALTER TABLE public.utenti OWNER TO "postgresMaster";

--
-- Name: utente_utente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.utente_utente_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.utente_utente_id_seq OWNER TO "postgresMaster";

--
-- Name: utente_utente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.utente_utente_id_seq OWNED BY public.utenti.utente_id;


--
-- Name: videogiochi; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.videogiochi (
    videogioco_id integer NOT NULL,
    titolo text NOT NULL,
    casa_produttrice integer NOT NULL,
    casa_di_pubblicazione integer NOT NULL,
    data_di_publicazione date NOT NULL,
    ore_storia_principale text,
    descrizione text NOT NULL,
    voto integer NOT NULL,
    immagine_di_copertina text NOT NULL
);


ALTER TABLE public.videogiochi OWNER TO "postgresMaster";

--
-- Name: videogiochi_assegnamento_tag; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.videogiochi_assegnamento_tag (
    assegnamento_id integer NOT NULL,
    tag_id integer NOT NULL,
    videogioco_id integer NOT NULL
);


ALTER TABLE public.videogiochi_assegnamento_tag OWNER TO "postgresMaster";

--
-- Name: videogiochi_assegnamento_tag_assegnamento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.videogiochi_assegnamento_tag_assegnamento_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.videogiochi_assegnamento_tag_assegnamento_id_seq OWNER TO "postgresMaster";

--
-- Name: videogiochi_assegnamento_tag_assegnamento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.videogiochi_assegnamento_tag_assegnamento_id_seq OWNED BY public.videogiochi_assegnamento_tag.assegnamento_id;


--
-- Name: videogiochi_giocati; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.videogiochi_giocati (
    "videogioco_giocato_id<" integer NOT NULL,
    videogioco_id integer NOT NULL,
    utente_id integer NOT NULL,
    recensione_id integer
);


ALTER TABLE public.videogiochi_giocati OWNER TO "postgresMaster";

--
-- Name: videogiochi_giocati_videogiochi_giocati_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.videogiochi_giocati_videogiochi_giocati_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.videogiochi_giocati_videogiochi_giocati_id_seq OWNER TO "postgresMaster";

--
-- Name: videogiochi_giocati_videogiochi_giocati_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.videogiochi_giocati_videogiochi_giocati_id_seq OWNED BY public.videogiochi_giocati."videogioco_giocato_id<";


--
-- Name: videogiochi_videogioco_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.videogiochi_videogioco_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.videogiochi_videogioco_id_seq OWNER TO "postgresMaster";

--
-- Name: videogiochi_videogioco_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.videogiochi_videogioco_id_seq OWNED BY public.videogiochi.videogioco_id;


--
-- Name: videogioco_prodotto_da_prodotto_da_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.videogioco_prodotto_da_prodotto_da_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.videogioco_prodotto_da_prodotto_da_id_seq OWNER TO "postgresMaster";

--
-- Name: videogioco_prodotto_da_prodotto_da_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgresMaster
--

ALTER SEQUENCE public.videogioco_prodotto_da_prodotto_da_id_seq OWNED BY public.crew_videogioco.crew_id;


--
-- Name: autori autore_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.autori ALTER COLUMN autore_id SET DEFAULT nextval('public.autori_autore_id_seq'::regclass);


--
-- Name: case casa_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public."case" ALTER COLUMN casa_id SET DEFAULT nextval('public.casa_casa_id_seq'::regclass);


--
-- Name: crew_film crew_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.crew_film ALTER COLUMN crew_id SET DEFAULT nextval('public.film_prodotto_da_prodotto_da_id_seq'::regclass);


--
-- Name: crew_videogioco crew_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.crew_videogioco ALTER COLUMN crew_id SET DEFAULT nextval('public.videogioco_prodotto_da_prodotto_da_id_seq'::regclass);


--
-- Name: film film_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film ALTER COLUMN film_id SET DEFAULT nextval('public.film_film_id_seq'::regclass);


--
-- Name: film_assegnamento_tag assegnamento_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film_assegnamento_tag ALTER COLUMN assegnamento_id SET DEFAULT nextval('public.film_assegnamento_tag_assegnamento_id_seq'::regclass);


--
-- Name: film_visti film_visto_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film_visti ALTER COLUMN film_visto_id SET DEFAULT nextval('public.film_visti_film_visti_id_seq'::regclass);


--
-- Name: libri libro_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.libri ALTER COLUMN libro_id SET DEFAULT nextval('public.libri_libro_id_seq'::regclass);


--
-- Name: libri_assegnamento_tag assegnamento_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.libri_assegnamento_tag ALTER COLUMN assegnamento_id SET DEFAULT nextval('public.libri_assegnamento_tag_assegnamento_id_seq'::regclass);


--
-- Name: libri_letti libro_letto_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.libri_letti ALTER COLUMN libro_letto_id SET DEFAULT nextval('public.libri_letti_libri_letti_id_seq'::regclass);


--
-- Name: recensioni recensione_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.recensioni ALTER COLUMN recensione_id SET DEFAULT nextval('public.recensioni_recensioni_id_seq'::regclass);


--
-- Name: ruoli ruolo_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.ruoli ALTER COLUMN ruolo_id SET DEFAULT nextval('public.ruoli_ruolo_id_seq'::regclass);


--
-- Name: scritto_da made_by_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.scritto_da ALTER COLUMN made_by_id SET DEFAULT nextval('public.scritto_da_made_by_id_seq'::regclass);


--
-- Name: tag tag_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.tag ALTER COLUMN tag_id SET DEFAULT nextval('public.tag_tag_id_seq'::regclass);


--
-- Name: utenti utente_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.utenti ALTER COLUMN utente_id SET DEFAULT nextval('public.utente_utente_id_seq'::regclass);


--
-- Name: videogiochi videogioco_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.videogiochi ALTER COLUMN videogioco_id SET DEFAULT nextval('public.videogiochi_videogioco_id_seq'::regclass);


--
-- Name: videogiochi_assegnamento_tag assegnamento_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.videogiochi_assegnamento_tag ALTER COLUMN assegnamento_id SET DEFAULT nextval('public.videogiochi_assegnamento_tag_assegnamento_id_seq'::regclass);


--
-- Name: videogiochi_giocati videogioco_giocato_id<; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.videogiochi_giocati ALTER COLUMN "videogioco_giocato_id<" SET DEFAULT nextval('public.videogiochi_giocati_videogiochi_giocati_id_seq'::regclass);


--
-- Data for Name: autori; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

COPY public.autori (autore_id, nome, secondo_nome, cognome, data_di_nascita, nazionalita) FROM stdin;
1	J.K.	\N	Rowling	1965-07-31	Regno Unito
2	Stephen	\N	King	1947-09-21	USA
3	George	R.R.	Martin	1948-09-20	USA
4	Agatha	\N	Christie	1890-09-15	Regno Unito
5	Isaac	\N	Asimov	1920-01-02	USA
6	Neil	\N	Gaiman	1960-11-10	Regno Unito
7	Margaret	\N	Atwood	1939-11-18	Canada
8	Haruki	\N	Murakami	1949-01-12	Giappone
9	J.R.R.	\N	Tolkien	1892-01-03	Regno Unito
10	Jane	\N	Austen	1775-12-16	Regno Unito
\.


--
-- Data for Name: case; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

COPY public."case" (casa_id, nome, nazionalita) FROM stdin;
1	Warner Bros.	USA
2	Penguin Books	Regno Unito
3	Electronic Arts	USA
4	Sony Pictures	USA
5	Random House	USA
6	Nintendo	Giappone
7	20th Century Fox	USA
8	HarperCollins	USA
9	Ubisoft	Francia
10	Columbia Pictures	USA
\.


--
-- Data for Name: crew_film; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

COPY public.crew_film (crew_id, film_id, autore_id, ruolo_id) FROM stdin;
1	1	1	1
2	2	2	1
3	3	3	1
4	4	4	1
5	5	5	1
6	6	6	1
7	7	7	1
8	8	8	1
9	9	9	1
10	10	10	1
\.


--
-- Data for Name: crew_videogioco; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

COPY public.crew_videogioco (crew_id, videogioco_id, autore_id, ruolo_id) FROM stdin;
1	1	1	9
2	2	2	8
3	3	3	9
4	4	4	9
5	5	5	8
6	6	6	9
7	7	7	8
8	8	8	9
9	9	9	9
10	10	10	8
\.


--
-- Data for Name: film; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

COPY public.film (film_id, titolo, durata, data_di_pubblicazione, descrizione, casa_di_produzione, anno_di_pubblicazione, voto, immagine_di_copertina) FROM stdin;
1	Inception	148	2010-07-16	Un thriller che piega la mente sui sogni dentro i sogni.	1	2010-07-16	92	http://example.com/inception.jpg
2	Il Padrino	175	1972-03-24	Il patriarca di un impero criminale passa il controllo al figlio riluttante.	7	1972-03-24	98	http://example.com/godfather.jpg
3	Pulp Fiction	154	1994-10-14	Le vite di due sicari, un pugile e altri si intrecciano in una serie di eventi violenti.	4	1994-10-14	94	http://example.com/pulpfiction.jpg
4	Il Cavaliere Oscuro	152	2008-07-18	Batman alza la posta nella sua guerra contro il crimine.	1	2008-07-18	94	http://example.com/darkknight.jpg
5	Fight Club	139	1999-10-15	Un impiegato insonne forma un club di combattimento sotterraneo.	4	1999-10-15	89	http://example.com/fightclub.jpg
6	Forrest Gump	142	1994-07-06	Le presidenze di Kennedy e Johnson raccontate attraverso gli occhi di un uomo con un QI basso.	7	1994-07-06	88	http://example.com/forrestgump.jpg
7	Matrix	136	1999-03-31	Un hacker scopre che la realtà è una simulazione.	1	1999-03-31	92	http://example.com/matrix.jpg
8	Interstellar	169	2014-11-07	Esploratori attraversano un wormhole nello spazio per salvare l’umanità.	1	2014-11-07	91	http://example.com/interstellar.jpg
9	Il Gladiatore	155	2000-05-05	Un ex generale romano cerca vendetta contro l’imperatore corrotto.	7	2000-05-05	87	http://example.com/gladiator.jpg
10	Le Ali della Libertà	142	1994-09-23	Due prigionieri stringono un legame nel corso di molti anni.	7	1994-09-23	98	http://example.com/shawshank.jpg
\.


--
-- Data for Name: film_assegnamento_tag; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

COPY public.film_assegnamento_tag (assegnamento_id, tag_id, film_id) FROM stdin;
1	3	1
2	8	1
3	9	2
4	9	3
5	8	4
6	9	5
7	8	6
8	3	7
9	4	8
10	7	9
\.


--
-- Data for Name: film_visti; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

COPY public.film_visti (film_visto_id, film_id, utente_id, recensione_id) FROM stdin;
1	1	1	1
2	2	2	2
3	3	3	3
4	4	4	4
5	5	5	5
6	6	6	6
7	7	7	7
8	8	8	8
9	9	9	9
10	10	10	10
\.


--
-- Data for Name: libri; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

COPY public.libri (libro_id, titolo, numero_di_pagine, casa_produttrice_id, descrizione, anno_di_pubblicazione, voto, immagine_di_copertina) FROM stdin;
1	Harry Potter e la Pietra Filosofale	309	2	Un giovane mago scopre il suo retaggio magico.	1997-06-26	95	http://example.com/hp1.jpg
2	Shining	447	5	Una famiglia si dirige in un hotel isolato per l’inverno dove una presenza sinistra influenza il padre.	1977-01-28	88	http://example.com/shining.jpg
3	Il Trono di Spade	694	8	Famiglie nobili gareggiano per il controllo del Trono di Spade.	1996-08-06	92	http://example.com/got1.jpg
4	Assassinio sull’Orient Express	256	8	L’investigatore Hercule Poirot indaga su un omicidio su un treno bloccato dalla neve.	1934-01-01	85	http://example.com/orientexpress.jpg
5	Fondazione	255	5	Un matematico cerca di preservare la conoscenza mentre l’Impero Galattico crolla.	1951-06-01	90	http://example.com/foundation.jpg
6	American Gods	465	2	Il carcere penale Shadow incontra il misterioso signor Wednesday.	2001-06-19	89	http://example.com/americangods.jpg
7	Il Racconto dell’Ancella	311	8	In un futuro distopico, le donne sono trattate come proprietà.	1985-09-01	87	http://example.com/handmaid.jpg
8	Norwegian Wood	296	8	Uno studente universitario ricorda i suoi giorni a Tokyo negli anni ’60.	1987-09-04	84	http://example.com/norwegianwood.jpg
9	Lo Hobbit	310	5	Bilbo Baggins intraprende un viaggio inaspettato.	1937-09-21	94	http://example.com/hobbit.jpg
10	Orgoglio e Pregiudizio	279	8	Le maniere e le macchinazioni matrimoniali tra la nobiltà inglese.	1813-01-28	96	http://example.com/prideprejudice.jpg
\.


--
-- Data for Name: libri_assegnamento_tag; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

COPY public.libri_assegnamento_tag (assegnamento_id, tag_id, libro_id) FROM stdin;
1	4	1
2	5	2
3	4	3
4	2	4
5	3	5
6	6	6
7	7	7
8	4	8
9	4	9
10	6	10
\.


--
-- Data for Name: libri_letti; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

COPY public.libri_letti (libro_letto_id, libro_id, utente_id, recensione_id) FROM stdin;
1	1	1	1
2	2	2	2
3	3	3	3
4	4	4	4
5	5	5	5
6	6	6	6
7	7	7	7
8	8	8	8
9	9	9	9
10	10	10	10
\.


--
-- Data for Name: recensioni; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

COPY public.recensioni (recensione_id, voto, recensione) FROM stdin;
1	95	Assolutamente fantastico. Un capolavoro.
2	80	Abbastanza solido, ma presenta dei difetti.
3	60	Mah, è stato così così.
4	45	Non è eccezionale, ma neanche pessimo.
5	20	Mi pento di averlo visto/letto/giocato.
6	90	Mozzafiato. Consigliatissimo.
7	70	Sufficientemente buono da guardare/leggere/giocare.
8	50	Nella media, al massimo.
9	30	Al di sotto delle aspettative.
10	10	Terribile. Evitatelo a tutti i costi.
\.


--
-- Data for Name: ruoli; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

COPY public.ruoli (ruolo_id, nome) FROM stdin;
1	Regista
2	Produttore
3	Sceneggiatore
4	Attore
5	Compositore
6	Direttore della fotografia
7	Montatore
8	Designer
9	Programmatore
10	Doppiatore
\.


--
-- Data for Name: scritto_da; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

COPY public.scritto_da (made_by_id, autore_id, libro_id, autore_primario) FROM stdin;
1	1	1	t
2	2	2	t
3	3	3	t
4	4	4	t
5	5	5	t
6	6	6	t
7	7	7	t
8	8	8	t
9	9	9	t
10	10	10	t
\.


--
-- Data for Name: tag; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

COPY public.tag (tag_id, tag_name, genere) FROM stdin;
1	Narrativa	t
2	Mistero	t
3	Fantascienza	t
4	Fantasy	t
5	Horror	t
6	Romantico	t
7	Avventura	t
8	Thriller	t
9	Drammatico	t
10	Commedia	t
\.


--
-- Data for Name: utenti; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

COPY public.utenti (utente_id, nome, password, email) FROM stdin;
1	user_one	pwd123	user1@example.com
2	user_two	pwd123	user2@example.com
3	user_three	pwd123	user3@example.com
4	user_four	pwd123	user4@example.com
5	user_five	pwd123	user5@example.com
6	user_six	pwd123	user6@example.com
7	user_seven	pwd123	user7@example.com
8	user_eight	pwd123	user8@example.com
9	user_nine	pwd123	user9@example.com
10	user_ten	pwd123	user10@example.com
\.


--
-- Data for Name: videogiochi; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

COPY public.videogiochi (videogioco_id, titolo, casa_produttrice, casa_di_pubblicazione, data_di_publicazione, ore_storia_principale, descrizione, voto, immagine_di_copertina) FROM stdin;
1	The Witcher 3: Wild Hunt	9	9	2015-05-19	50+	Geralt di Rivia intraprende un viaggio per trovare sua figlia adottiva.	97	http://example.com/witcher3.jpg
2	Super Mario Bros.	6	6	1985-09-13	10	Mario deve salvare la Principessa Peach da Bowser.	90	http://example.com/mariobros.jpg
3	Assassin’s Creed II	9	9	2009-11-17	20	Ezio Auditore diventa un assassino nel Rinascimento italiano.	89	http://example.com/ac2.jpg
4	The Last of Us	3	3	2013-06-14	15	Joel scorta Ellie attraverso un’America post-apocalittica.	95	http://example.com/lastofus.jpg
5	God of War	6	6	2018-04-20	25	Kratos intraprende un viaggio con suo figlio Atreus.	94	http://example.com/godofwar.jpg
6	Minecraft	3	3	2011-11-18	Infinito	Gioco sandbox di costruzione e sopravvivenza.	88	http://example.com/minecraft.jpg
7	Red Dead Redemption 2	3	3	2018-10-26	60	Un fuorilegge intraprende una missione nel West morente.	96	http://example.com/rdr2.jpg
8	The Legend of Zelda: Breath of the Wild	6	6	2017-03-03	50	Link si risveglia da un lungo sonno per salvare Hyrule.	97	http://example.com/zelda.jpg
9	Cyberpunk 2077	9	9	2020-12-10	30	V si muove nella Night City alla ricerca dell’immortalità.	70	http://example.com/cyberpunk2077.jpg
10	Fortnite	3	3	2017-07-21	∞	Gioco Battle Royale con meccaniche di costruzione.	75	http://example.com/fortnite.jpg
\.


--
-- Data for Name: videogiochi_assegnamento_tag; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

COPY public.videogiochi_assegnamento_tag (assegnamento_id, tag_id, videogioco_id) FROM stdin;
1	7	1
2	7	2
3	7	3
4	8	4
5	7	5
6	7	6
7	7	7
8	4	8
9	3	9
10	7	10
\.


--
-- Data for Name: videogiochi_giocati; Type: TABLE DATA; Schema: public; Owner: postgresMaster
--

COPY public.videogiochi_giocati ("videogioco_giocato_id<", videogioco_id, utente_id, recensione_id) FROM stdin;
1	1	1	1
2	2	2	2
3	3	3	3
4	4	4	4
5	5	5	5
6	6	6	6
7	7	7	7
8	8	8	8
9	9	9	9
10	10	10	10
\.


--
-- Name: autori_autore_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.autori_autore_id_seq', 1, false);


--
-- Name: casa_casa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.casa_casa_id_seq', 1, false);


--
-- Name: film_assegnamento_tag_assegnamento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.film_assegnamento_tag_assegnamento_id_seq', 1, false);


--
-- Name: film_film_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.film_film_id_seq', 1, false);


--
-- Name: film_prodotto_da_prodotto_da_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.film_prodotto_da_prodotto_da_id_seq', 1, false);


--
-- Name: film_visti_film_visti_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.film_visti_film_visti_id_seq', 1, false);


--
-- Name: libri_assegnamento_tag_assegnamento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.libri_assegnamento_tag_assegnamento_id_seq', 1, false);


--
-- Name: libri_letti_libri_letti_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.libri_letti_libri_letti_id_seq', 1, false);


--
-- Name: libri_libro_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.libri_libro_id_seq', 1, false);


--
-- Name: recensioni_recensioni_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.recensioni_recensioni_id_seq', 1, false);


--
-- Name: ruoli_ruolo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.ruoli_ruolo_id_seq', 1, false);


--
-- Name: scritto_da_made_by_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.scritto_da_made_by_id_seq', 1, false);


--
-- Name: tag_tag_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.tag_tag_id_seq', 1, false);


--
-- Name: utente_utente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.utente_utente_id_seq', 1, false);


--
-- Name: videogiochi_assegnamento_tag_assegnamento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.videogiochi_assegnamento_tag_assegnamento_id_seq', 1, false);


--
-- Name: videogiochi_giocati_videogiochi_giocati_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.videogiochi_giocati_videogiochi_giocati_id_seq', 1, false);


--
-- Name: videogiochi_videogioco_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.videogiochi_videogioco_id_seq', 1, false);


--
-- Name: videogioco_prodotto_da_prodotto_da_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgresMaster
--

SELECT pg_catalog.setval('public.videogioco_prodotto_da_prodotto_da_id_seq', 1, false);


--
-- Name: autori autori_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.autori
    ADD CONSTRAINT autori_pkey PRIMARY KEY (autore_id);


--
-- Name: case casa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public."case"
    ADD CONSTRAINT casa_pkey PRIMARY KEY (casa_id);


--
-- Name: film data_di_pubblicazione_valida; Type: CHECK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE public.film
    ADD CONSTRAINT data_di_pubblicazione_valida CHECK ((data_di_pubblicazione > '1900-01-01'::date)) NOT VALID;


--
-- Name: videogiochi data_di_publicazione_dopo_1962; Type: CHECK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE public.videogiochi
    ADD CONSTRAINT data_di_publicazione_dopo_1962 CHECK ((data_di_publicazione > '1962-01-01'::date)) NOT VALID;


--
-- Name: film durata_sopra_0; Type: CHECK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE public.film
    ADD CONSTRAINT durata_sopra_0 CHECK ((durata >= 0)) NOT VALID;


--
-- Name: film_assegnamento_tag film_assegnamento_tag_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film_assegnamento_tag
    ADD CONSTRAINT film_assegnamento_tag_pkey PRIMARY KEY (assegnamento_id);


--
-- Name: film film_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film
    ADD CONSTRAINT film_pkey PRIMARY KEY (film_id);


--
-- Name: crew_film film_prodotto_da_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.crew_film
    ADD CONSTRAINT film_prodotto_da_pkey PRIMARY KEY (crew_id);


--
-- Name: film_visti film_visto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film_visti
    ADD CONSTRAINT film_visto_pkey PRIMARY KEY (film_visto_id);


--
-- Name: libri_assegnamento_tag libri_assegnamento_tag_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.libri_assegnamento_tag
    ADD CONSTRAINT libri_assegnamento_tag_pkey PRIMARY KEY (assegnamento_id);


--
-- Name: libri libri_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.libri
    ADD CONSTRAINT libri_pkey PRIMARY KEY (libro_id);


--
-- Name: libri_letti libro_letto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.libri_letti
    ADD CONSTRAINT libro_letto_pkey PRIMARY KEY (libro_letto_id);


--
-- Name: libri numero_di_pagine_sopra_0; Type: CHECK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE public.libri
    ADD CONSTRAINT numero_di_pagine_sopra_0 CHECK ((numero_di_pagine > 0)) NOT VALID;


--
-- Name: recensioni recensioni_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.recensioni
    ADD CONSTRAINT recensioni_pkey PRIMARY KEY (recensione_id);


--
-- Name: ruoli ruoli_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.ruoli
    ADD CONSTRAINT ruoli_pkey PRIMARY KEY (ruolo_id);


--
-- Name: scritto_da scritto_da_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.scritto_da
    ADD CONSTRAINT scritto_da_pkey PRIMARY KEY (made_by_id);


--
-- Name: tag tag_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (tag_id);


--
-- Name: utenti utente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.utenti
    ADD CONSTRAINT utente_pkey PRIMARY KEY (utente_id);


--
-- Name: videogiochi_assegnamento_tag videogiochi_assegnamento_tag_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.videogiochi_assegnamento_tag
    ADD CONSTRAINT videogiochi_assegnamento_tag_pkey PRIMARY KEY (assegnamento_id);


--
-- Name: videogiochi videogiochi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.videogiochi
    ADD CONSTRAINT videogiochi_pkey PRIMARY KEY (videogioco_id);


--
-- Name: videogiochi_giocati videogioco_giocato_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.videogiochi_giocati
    ADD CONSTRAINT videogioco_giocato_pkey PRIMARY KEY ("videogioco_giocato_id<");


--
-- Name: crew_videogioco videogioco_prodotto_da_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.crew_videogioco
    ADD CONSTRAINT videogioco_prodotto_da_pkey PRIMARY KEY (crew_id);


--
-- Name: libri voto_between_0_100; Type: CHECK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE public.libri
    ADD CONSTRAINT voto_between_0_100 CHECK (((voto >= 0) AND (voto <= 100))) NOT VALID;


--
-- Name: videogiochi voto_between_0_100; Type: CHECK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE public.videogiochi
    ADD CONSTRAINT voto_between_0_100 CHECK (((voto >= 0) AND (voto <= 100))) NOT VALID;


--
-- Name: film voto_under_100; Type: CHECK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE public.film
    ADD CONSTRAINT voto_under_100 CHECK (((voto >= 0) AND (voto <= 100))) NOT VALID;


--
-- Name: film_assegnamento_tag film_assegnamento_tag_film_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film_assegnamento_tag
    ADD CONSTRAINT film_assegnamento_tag_film_id_fkey FOREIGN KEY (film_id) REFERENCES public.film(film_id);


--
-- Name: film_assegnamento_tag film_assegnamento_tag_tag_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film_assegnamento_tag
    ADD CONSTRAINT film_assegnamento_tag_tag_id_fkey FOREIGN KEY (tag_id) REFERENCES public.tag(tag_id);


--
-- Name: film film_casa_di_produzione_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film
    ADD CONSTRAINT film_casa_di_produzione_fkey FOREIGN KEY (casa_di_produzione) REFERENCES public."case"(casa_id);


--
-- Name: crew_film film_prodotto_da_autore_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.crew_film
    ADD CONSTRAINT film_prodotto_da_autore_id_fkey FOREIGN KEY (autore_id) REFERENCES public.autori(autore_id);


--
-- Name: crew_film film_prodotto_da_film_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.crew_film
    ADD CONSTRAINT film_prodotto_da_film_id_fkey FOREIGN KEY (film_id) REFERENCES public.film(film_id);


--
-- Name: crew_film film_prodotto_da_ruolo_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.crew_film
    ADD CONSTRAINT film_prodotto_da_ruolo_id_fkey FOREIGN KEY (ruolo_id) REFERENCES public.ruoli(ruolo_id);


--
-- Name: film_visti film_visti_film_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film_visti
    ADD CONSTRAINT film_visti_film_id_fkey FOREIGN KEY (film_id) REFERENCES public.film(film_id) NOT VALID;


--
-- Name: film_visti film_visti_recensione_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film_visti
    ADD CONSTRAINT film_visti_recensione_id_fkey FOREIGN KEY (recensione_id) REFERENCES public.recensioni(recensione_id) NOT VALID;


--
-- Name: film_visti film_visti_utente_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film_visti
    ADD CONSTRAINT film_visti_utente_id_fkey FOREIGN KEY (utente_id) REFERENCES public.utenti(utente_id) NOT VALID;


--
-- Name: libri_assegnamento_tag libri_assegnamento_tag_libro_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.libri_assegnamento_tag
    ADD CONSTRAINT libri_assegnamento_tag_libro_id_fkey FOREIGN KEY (libro_id) REFERENCES public.libri(libro_id);


--
-- Name: libri_assegnamento_tag libri_assegnamento_tag_tag_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.libri_assegnamento_tag
    ADD CONSTRAINT libri_assegnamento_tag_tag_id_fkey FOREIGN KEY (tag_id) REFERENCES public.tag(tag_id);


--
-- Name: libri libri_casa_produttrice_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.libri
    ADD CONSTRAINT libri_casa_produttrice_id_fkey FOREIGN KEY (casa_produttrice_id) REFERENCES public."case"(casa_id);


--
-- Name: libri_letti libri_letti_libro_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.libri_letti
    ADD CONSTRAINT libri_letti_libro_id_fkey FOREIGN KEY (libro_id) REFERENCES public.libri(libro_id);


--
-- Name: libri_letti libri_letti_recensione_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.libri_letti
    ADD CONSTRAINT libri_letti_recensione_id_fkey FOREIGN KEY (recensione_id) REFERENCES public.recensioni(recensione_id) NOT VALID;


--
-- Name: libri_letti libri_letti_utente_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.libri_letti
    ADD CONSTRAINT libri_letti_utente_id_fkey FOREIGN KEY (utente_id) REFERENCES public.utenti(utente_id) NOT VALID;


--
-- Name: scritto_da scritto_da_autore_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.scritto_da
    ADD CONSTRAINT scritto_da_autore_id_fkey FOREIGN KEY (autore_id) REFERENCES public.autori(autore_id);


--
-- Name: scritto_da scritto_da_libro_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.scritto_da
    ADD CONSTRAINT scritto_da_libro_id_fkey FOREIGN KEY (libro_id) REFERENCES public.libri(libro_id);


--
-- Name: videogiochi_assegnamento_tag videogiochi_assegnamento_tag_tag_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.videogiochi_assegnamento_tag
    ADD CONSTRAINT videogiochi_assegnamento_tag_tag_id_fkey FOREIGN KEY (tag_id) REFERENCES public.tag(tag_id);


--
-- Name: videogiochi_assegnamento_tag videogiochi_assegnamento_tag_videogioco_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.videogiochi_assegnamento_tag
    ADD CONSTRAINT videogiochi_assegnamento_tag_videogioco_id_fkey FOREIGN KEY (videogioco_id) REFERENCES public.videogiochi(videogioco_id);


--
-- Name: videogiochi videogiochi_casa_produttrice_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.videogiochi
    ADD CONSTRAINT videogiochi_casa_produttrice_fkey FOREIGN KEY (casa_produttrice) REFERENCES public."case"(casa_id);


--
-- Name: videogiochi_giocati videogiochi_giocati_recensione_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.videogiochi_giocati
    ADD CONSTRAINT videogiochi_giocati_recensione_id_fkey FOREIGN KEY (recensione_id) REFERENCES public.recensioni(recensione_id) NOT VALID;


--
-- Name: videogiochi_giocati videogiochi_giocati_utente_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.videogiochi_giocati
    ADD CONSTRAINT videogiochi_giocati_utente_id_fkey FOREIGN KEY (utente_id) REFERENCES public.utenti(utente_id) NOT VALID;


--
-- Name: videogiochi_giocati videogiochi_giocati_videogioco_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.videogiochi_giocati
    ADD CONSTRAINT videogiochi_giocati_videogioco_id_fkey FOREIGN KEY (videogioco_id) REFERENCES public.videogiochi(videogioco_id) NOT VALID;


--
-- Name: crew_videogioco videogioco_prodotto_da_autore_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.crew_videogioco
    ADD CONSTRAINT videogioco_prodotto_da_autore_id_fkey FOREIGN KEY (autore_id) REFERENCES public.autori(autore_id);


--
-- Name: crew_videogioco videogioco_prodotto_da_ruolo_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.crew_videogioco
    ADD CONSTRAINT videogioco_prodotto_da_ruolo_id_fkey FOREIGN KEY (ruolo_id) REFERENCES public.ruoli(ruolo_id);


--
-- Name: crew_videogioco videogioco_prodotto_da_videogioco_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.crew_videogioco
    ADD CONSTRAINT videogioco_prodotto_da_videogioco_id_fkey FOREIGN KEY (videogioco_id) REFERENCES public.videogiochi(videogioco_id);


--
-- PostgreSQL database dump complete
--

