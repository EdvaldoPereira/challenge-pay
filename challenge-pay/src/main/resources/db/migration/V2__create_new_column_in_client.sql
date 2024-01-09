CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
ALTER TABLE public.clients ADD client_identifier uuid DEFAULT uuid_generate_v4 () NOT NULL;
