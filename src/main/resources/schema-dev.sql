CREATE TABLE "product"
(
    product_id SERIAL           NOT NULL,
    name       VARCHAR(45)      NOT NULL,
    cost       DOUBLE PRECISION NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (product_id)
);

CREATE TABLE "order"
(
    order_id SERIAL           NOT NULL,
    date     DATE             NOT NULL,
    cost     DOUBLE PRECISION NOT NULL,
    CONSTRAINT order_pkey PRIMARY KEY (order_id)
);

CREATE TABLE "order_product"
(
    order_id   INT NOT NULL,
    product_id INT NOT NULL,
    CONSTRAINT order_product_pkey
        PRIMARY KEY (order_id, product_id),
    CONSTRAINT order_fk
        FOREIGN KEY (order_id)
            REFERENCES "order" (order_id)
            ON UPDATE CASCADE
            ON DELETE CASCADE,
    CONSTRAINT product_fk
        FOREIGN KEY (product_id)
            REFERENCES "product" (product_id)
            ON UPDATE CASCADE
            ON DELETE CASCADE
);