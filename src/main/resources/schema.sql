CREATE TABLE IF NOT EXISTS public.books (
    id          SERIAL PRIMARY KEY,
    name        CHARACTER VARYING(100),
    author      CHARACTER VARYING(100),
    status      CHARACTER VARYING(20)
);
