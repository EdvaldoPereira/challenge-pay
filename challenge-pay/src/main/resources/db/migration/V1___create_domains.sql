CREATE TABLE public.clients (
	id int8 NOT NULL,
	first_name varchar(50) NOT NULL,
	last_name varchar(80) NOT NULL,
	"document" varchar(15) NULL,
	document_type varchar(4) NULL,
	email varchar(100) NOT NULL,
	"password" varchar(100) NULL,
	creation_date timestamp NOT NULL,
	CONSTRAINT clients_pk PRIMARY KEY (id),
	CONSTRAINT clients_un UNIQUE (document, email)
);

CREATE TABLE public.wallets (
	id int8 NOT NULL,
	creation_date timestamp NULL,
	balance numeric(14, 2) NULL,
	client_id int8 NOT NULL,
	CONSTRAINT wallets_pk PRIMARY KEY (id),
	CONSTRAINT wallets_fk FOREIGN KEY (client_id) REFERENCES public.clients(id)
);

CREATE TABLE public.transactions (
	id int8 NOT NULL,
	creation_date timestamp NOT NULL,
	amount numeric(14, 2) NOT NULL,
	sender int8 NOT NULL,
	receiver int8 NOT NULL,
	CONSTRAINT transactions_pk PRIMARY KEY (id),
	CONSTRAINT transactions_fk FOREIGN KEY (sender) REFERENCES public.clients(id),
	CONSTRAINT transactions_fk_1 FOREIGN KEY (receiver) REFERENCES public.clients(id)
);