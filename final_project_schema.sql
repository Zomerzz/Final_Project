DROP TABLE IF EXISTS public.case_p CASCADE;
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
DROP TABLE IF EXISTS public.utenti_authorities CASCADE;

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
    nome character varying(255) NOT NULL,
    secondo_nome character varying(255),
    cognome character varying(255) NOT NULL,
    data_di_nascita date NOT NULL,
    nazionalita character varying(255) NOT NULL
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
-- Name: case_p; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.case_p (
    casa_id integer NOT NULL,
    nome character varying(255) NOT NULL,
    nazionalita character varying(255) NOT NULL
);


ALTER TABLE public.case_p OWNER TO "postgresMaster";

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

ALTER SEQUENCE public.casa_casa_id_seq OWNED BY public.case_p.casa_id;


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
    titolo character varying(255) NOT NULL,
    durata integer NOT NULL,
    data_di_pubblicazione date NOT NULL,
    descrizione character varying(255) NOT NULL,
    casa_di_produzione_id integer NOT NULL,
    voto integer NOT NULL,
    immagine_di_copertina character varying(255) NOT NULL,
    casa_di_pubblicazione_id integer
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
-- Name: film_visti_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.film_visti_seq
    START WITH 50
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.film_visti_seq OWNER TO "postgresMaster";

--
-- Name: film_visti; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.film_visti (
    film_visto_id integer DEFAULT nextval('public.film_visti_seq'::regclass) NOT NULL,
    film_id integer NOT NULL,
    utente_id integer NOT NULL,
    recensione_id integer
);


ALTER TABLE public.film_visti OWNER TO "postgresMaster";

--
-- Name: libri; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.libri (
    libro_id integer NOT NULL,
    titolo character varying(255) NOT NULL,
    numero_di_pagine integer DEFAULT 0 NOT NULL,
    casa_editrice_id integer NOT NULL,
    descrizione character varying(255) NOT NULL,
    anno_di_pubblicazione date NOT NULL,
    voto integer DEFAULT 0 NOT NULL,
    immagine_di_copertina character varying(255) NOT NULL
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
-- Name: libri_letti_id_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.libri_letti_id_seq
    START WITH 50
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.libri_letti_id_seq OWNER TO "postgresMaster";

--
-- Name: libri_letti; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.libri_letti (
    libro_letto_id integer DEFAULT nextval('public.libri_letti_id_seq'::regclass) NOT NULL,
    libro_id integer NOT NULL,
    utente_id integer NOT NULL,
    recensione_id integer
);


ALTER TABLE public.libri_letti OWNER TO "postgresMaster";

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
    recensione character varying(255),
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
    nome character varying(255) NOT NULL
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
    libro_id integer NOT NULL
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
    tag_name character varying(255) NOT NULL,
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
    nome character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    email character varying(255) NOT NULL
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
-- Name: utenti_authorities; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.utenti_authorities (
    utente_id integer NOT NULL,
    authority character varying(255)
);


ALTER TABLE public.utenti_authorities OWNER TO "postgresMaster";

--
-- Name: videogiochi; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.videogiochi (
    videogioco_id integer NOT NULL,
    titolo character varying(255) NOT NULL,
    casa_di_produzione_id integer NOT NULL,
    casa_di_pubblicazione_id integer NOT NULL,
    data_di_pubblicazione date NOT NULL,
    ore_storia_principale integer,
    descrizione character varying(255) NOT NULL,
    voto integer NOT NULL,
    immagine_di_copertina character varying(255) NOT NULL
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
-- Name: videogiochi_giocati_seq; Type: SEQUENCE; Schema: public; Owner: postgresMaster
--

CREATE SEQUENCE public.videogiochi_giocati_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.videogiochi_giocati_seq OWNER TO "postgresMaster";

--
-- Name: videogiochi_giocati; Type: TABLE; Schema: public; Owner: postgresMaster
--

CREATE TABLE public.videogiochi_giocati (
    videogioco_giocato_id integer DEFAULT nextval('public.videogiochi_giocati_seq'::regclass) NOT NULL,
    videogioco_id integer NOT NULL,
    utente_id integer NOT NULL,
    recensione_id integer
);


ALTER TABLE public.videogiochi_giocati OWNER TO "postgresMaster";

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
-- Name: case_p casa_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.case_p ALTER COLUMN casa_id SET DEFAULT nextval('public.casa_casa_id_seq'::regclass);


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
-- Name: libri libro_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.libri ALTER COLUMN libro_id SET DEFAULT nextval('public.libri_libro_id_seq'::regclass);


--
-- Name: libri_assegnamento_tag assegnamento_id; Type: DEFAULT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.libri_assegnamento_tag ALTER COLUMN assegnamento_id SET DEFAULT nextval('public.libri_assegnamento_tag_assegnamento_id_seq'::regclass);


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
-- Name: autori autori_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.autori
    ADD CONSTRAINT autori_pkey PRIMARY KEY (autore_id);


--
-- Name: case_p casa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.case_p
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
    ADD CONSTRAINT data_di_publicazione_dopo_1962 CHECK ((data_di_pubblicazione > '1962-01-01'::date)) NOT VALID;


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
-- Name: film_visti film_visti_unique; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film_visti
    ADD CONSTRAINT film_visti_unique UNIQUE (film_id, utente_id);


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
-- Name: libri_letti libri_letti_unique; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.libri_letti
    ADD CONSTRAINT libri_letti_unique UNIQUE (libro_id, utente_id);


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
    ADD CONSTRAINT videogioco_giocato_pkey PRIMARY KEY (videogioco_giocato_id);


--
-- Name: videogiochi_giocati videogioco_giocato_unique; Type: CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.videogiochi_giocati
    ADD CONSTRAINT videogioco_giocato_unique UNIQUE (videogioco_id, utente_id);


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
-- Name: film film_casa_di_produzione_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film
    ADD CONSTRAINT film_casa_di_produzione_id_fkey FOREIGN KEY (casa_di_produzione_id) REFERENCES public.case_p(casa_id);


--
-- Name: film film_casa_di_pubblicazione_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film
    ADD CONSTRAINT film_casa_di_pubblicazione_id_fkey FOREIGN KEY (casa_di_pubblicazione_id) REFERENCES public.case_p(casa_id) NOT VALID;


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
    ADD CONSTRAINT film_visti_film_id_fkey FOREIGN KEY (film_id) REFERENCES public.film(film_id);


--
-- Name: film_visti film_visti_recensione_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film_visti
    ADD CONSTRAINT film_visti_recensione_id_fkey FOREIGN KEY (recensione_id) REFERENCES public.recensioni(recensione_id);


--
-- Name: film_visti film_visti_utente_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.film_visti
    ADD CONSTRAINT film_visti_utente_id_fkey FOREIGN KEY (utente_id) REFERENCES public.utenti(utente_id);


--
-- Name: utenti_authorities fkkf1gf6ulq57n4w7wgaptctikq; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.utenti_authorities
    ADD CONSTRAINT fkkf1gf6ulq57n4w7wgaptctikq FOREIGN KEY (utente_id) REFERENCES public.utenti(utente_id);


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
-- Name: libri libri_casa_editrice_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.libri
    ADD CONSTRAINT libri_casa_editrice_id_fkey FOREIGN KEY (casa_editrice_id) REFERENCES public.case_p(casa_id);


--
-- Name: libri_letti libri_letti_libro_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.libri_letti
    ADD CONSTRAINT libri_letti_libro_id_fkey FOREIGN KEY (libro_id) REFERENCES public.libri(libro_id);


--
-- Name: libri_letti libri_letti_recensione_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.libri_letti
    ADD CONSTRAINT libri_letti_recensione_id_fkey FOREIGN KEY (recensione_id) REFERENCES public.recensioni(recensione_id);


--
-- Name: libri_letti libri_letti_utente_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.libri_letti
    ADD CONSTRAINT libri_letti_utente_id_fkey FOREIGN KEY (utente_id) REFERENCES public.utenti(utente_id);


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
-- Name: videogiochi videogiochi_casa_di_produzione_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.videogiochi
    ADD CONSTRAINT videogiochi_casa_di_produzione_id_fkey FOREIGN KEY (casa_di_produzione_id) REFERENCES public.case_p(casa_id);


--
-- Name: videogiochi videogiochi_casa_di_pubblicazione_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.videogiochi
    ADD CONSTRAINT videogiochi_casa_di_pubblicazione_id_fkey FOREIGN KEY (casa_di_pubblicazione_id) REFERENCES public.case_p(casa_id) NOT VALID;


--
-- Name: videogiochi_giocati videogiochi_giocati_recensione_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.videogiochi_giocati
    ADD CONSTRAINT videogiochi_giocati_recensione_id_fkey FOREIGN KEY (recensione_id) REFERENCES public.recensioni(recensione_id);


--
-- Name: videogiochi_giocati videogiochi_giocati_utente_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.videogiochi_giocati
    ADD CONSTRAINT videogiochi_giocati_utente_id_fkey FOREIGN KEY (utente_id) REFERENCES public.utenti(utente_id);


--
-- Name: videogiochi_giocati videogiochi_giocati_videogioco_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgresMaster
--

ALTER TABLE ONLY public.videogiochi_giocati
    ADD CONSTRAINT videogiochi_giocati_videogioco_id_fkey FOREIGN KEY (videogioco_id) REFERENCES public.videogiochi(videogioco_id);


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

