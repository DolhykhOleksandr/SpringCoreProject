CREATE TABLE IF NOT EXISTS public."order"
(
    id   integer          NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    date  timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    cost double precision NOT NULL,
    CONSTRAINT order_pkey PRIMARY KEY (id)
);



CREATE TABLE IF NOT EXISTS public.product
(
    id   integer                                            NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying(16) COLLATE pg_catalog."default" NOT NULL,
    cost double precision                                   NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (id)
);



CREATE TABLE IF NOT EXISTS public.order_product
(
    order_id   integer NOT NULL,
    product_id integer NOT NULL,
    CONSTRAINT order_product_pkey PRIMARY KEY (order_id, product_id),
    CONSTRAINT order_fk FOREIGN KEY (order_id)
        REFERENCES public."order" (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,

    CONSTRAINT product_fk FOREIGN KEY (product_id)
        REFERENCES public.product (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

INSERT INTO public."order" (date,cost) VALUES (CURRENT_TIMESTAMP, 100.0);;
INSERT INTO public.product (name, cost) VALUES ('Product1', 50.00);
VALUES (currval(pg_get_serial_sequence('"order"', 'id')), currval(pg_get_serial_sequence('product', 'id')));