CREATE TABLE IF NOT EXISTS public."orderDTO"
(
    order_id integer          NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    date     date             NOT NULL,
    cost     double precision NOT NULL,
    CONSTRAINT order_pkey PRIMARY KEY (order_id)
);



CREATE TABLE IF NOT EXISTS public.productDTO
(
    product_id integer                                            NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name       character varying(16) COLLATE pg_catalog."default" NOT NULL,
    cost       double precision                                   NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (product_id)
);


CREATE TABLE IF NOT EXISTS public.order_product
(
    order_id   integer NOT NULL,
    product_id integer NOT NULL,
    CONSTRAINT order_product_pkey PRIMARY KEY (order_id, product_id),
    CONSTRAINT order_fk FOREIGN KEY (order_id)
        REFERENCES public."orderDTO" (order_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,

    CONSTRAINT product_fk FOREIGN KEY (product_id)
        REFERENCES public.productDTO (product_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE

);