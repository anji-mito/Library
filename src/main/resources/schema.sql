DROP TABLE IF EXISTS public.loans;
DROP TABLE IF EXISTS public.loan;
DROP TABLE IF EXISTS public.readers;
DROP TABLE IF EXISTS public.books;
DROP TABLE IF EXISTS public.book_descriptions;
DROP TABLE IF EXISTS public.compositions;
DROP TABLE IF EXISTS public.genres;
DROP TABLE IF EXISTS public.authors;

CREATE TABLE IF NOT EXISTS public.authors (
    id          SERIAL PRIMARY KEY,
    name        CHARACTER VARYING(500),
    surname     CHARACTER VARYING(300),
    patronymic  CHARACTER VARYING(300)
);

CREATE TABLE IF NOT EXISTS public.genres (
    id          SERIAL PRIMARY KEY,
    name        CHARACTER VARYING(500)
);

CREATE TABLE IF NOT EXISTS public.compositions (
    id          SERIAL PRIMARY KEY,
    title       CHARACTER VARYING(500),
    author_id   INTEGER,
    genre_id    INTEGER
);
ALTER TABLE public.compositions
    ADD FOREIGN KEY (author_id) REFERENCES public.authors(id);
ALTER TABLE public.compositions
    ADD FOREIGN KEY (genre_id) REFERENCES public.genres(id);

CREATE TABLE IF NOT EXISTS public.book_descriptions (
    id             SERIAL PRIMARY KEY,
    composition_id INTEGER,
    description    TEXT,
    isbn           CHARACTER VARYING(60),
    page_count     INTEGER
);
ALTER TABLE public.book_descriptions
    ADD FOREIGN KEY (composition_id) REFERENCES public.compositions(id);

CREATE TABLE IF NOT EXISTS public.books (
    id                   SERIAL PRIMARY KEY,
    composition_id       INTEGER,
    status               CHARACTER VARYING(20),
    book_description_id INTEGER
);
ALTER TABLE public.books
    ADD FOREIGN KEY (composition_id) REFERENCES public.compositions(id);
ALTER TABLE public.books
    ADD FOREIGN KEY (book_description_id) REFERENCES public.book_descriptions(id);

CREATE TABLE IF NOT EXISTS public.readers (
    id          SERIAL PRIMARY KEY,
    name        CHARACTER VARYING(500),
    surname     CHARACTER VARYING(300),
    email       CHARACTER VARYING(60),
    phone       CHARACTER VARYING(60)
);

CREATE TABLE IF NOT EXISTS public.loans (
    id          SERIAL PRIMARY KEY,
    book_id     INTEGER,
    reader_id   INTEGER,
    loan_date   DATE,
    loan_days   INTEGER
);
ALTER TABLE public.loans
    ADD FOREIGN KEY (book_id) REFERENCES public.books(id);
ALTER TABLE public.loans
    ADD FOREIGN KEY (reader_id) REFERENCES public.readers(id);

