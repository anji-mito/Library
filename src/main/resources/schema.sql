drop table if exists public.books;
CREATE TABLE IF NOT EXISTS public.books (
    id          SERIAL PRIMARY KEY,
    title       CHARACTER VARYING(255),
    author      CHARACTER VARYING(100),
    status      CHARACTER VARYING(20)
);
