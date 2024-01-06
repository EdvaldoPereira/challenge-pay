ALTER TABLE public.transactions ADD transaction_state varchar(50) NOT NULL;
COMMENT ON COLUMN public.transactions.transaction_state IS 'Indica o estado da transacao (Aprovado, negado)';
ALTER TABLE public.transactions ADD message_response varchar(200) NULL;
