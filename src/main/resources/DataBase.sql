CREATE TABLE IF NOT EXISTS public."order"
(
    id   serial PRIMARY KEY,
    date timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    cost double precision                    NOT NULL
);


CREATE TABLE IF NOT EXISTS public.product
(
    id   serial PRIMARY KEY,
    name character varying(16) COLLATE pg_catalog."default" NOT NULL,
    cost double precision                                   NOT NULL
);


CREATE TABLE IF NOT EXISTS public.order_product
(
    id         serial PRIMARY KEY,
    order_id   integer NOT NULL,
    product_id integer NOT NULL,
    CONSTRAINT order_product_order_fk FOREIGN KEY (order_id)
        REFERENCES public."order" (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,

    CONSTRAINT order_product_product_fk FOREIGN KEY (product_id)
        REFERENCES public.product (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


INSERT INTO public."order" (date, cost)
VALUES (CURRENT_TIMESTAMP, 100.0);


INSERT INTO public.product (name, cost)
VALUES ('Product 1', 50.0);


INSERT INTO public.order_product (order_id, product_id)
VALUES (currval(pg_get_serial_sequence('"order"', 'id')), currval(pg_get_serial_sequence('product', 'id')));

