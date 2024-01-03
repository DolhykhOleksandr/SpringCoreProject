CREATE TABLE "PRODUCT" (
                           PRODUCT_ID SERIAL PRIMARY KEY,
                           NAME VARCHAR(45) NOT NULL,
                           COST DOUBLE PRECISION NOT NULL

);

CREATE TABLE "ORDER" (
                         ORDER_ID SERIAL PRIMARY KEY,
                         DATE DATE NOT NULL,
                         COST DOUBLE PRECISION NOT NULL

);

CREATE TABLE "ORDER_PRODUCT" (
                                 ORDER_ID INT NOT NULL,
                                 PRODUCT_ID INT NOT NULL,
                                 CONSTRAINT ORDER_PRODUCT_PKEY
                                     PRIMARY KEY (ORDER_ID, PRODUCT_ID),
                                 CONSTRAINT ORDER_FK
                                     FOREIGN KEY (ORDER_ID)
                                         REFERENCES "ORDER" (ORDER_ID)
                                         ON UPDATE CASCADE
                                         ON DELETE CASCADE,
                                 CONSTRAINT PRODUCT_FK
                                     FOREIGN KEY (PRODUCT_ID)
                                         REFERENCES "PRODUCT" (PRODUCT_ID)
                                         ON UPDATE CASCADE
                                         ON DELETE CASCADE
);

CREATE TABLE "USER"
(
    "USERNAME" VARCHAR(32) NOT NULL,
    "PASSWORD" VARCHAR(64) NOT NULL,
    "FIRST_NAME" VARCHAR(32) NOT NULL,
    "LAST_NAME" VARCHAR(32) NOT NULL,
    "EMAIL" VARCHAR(32) NOT NULL,
    CONSTRAINT "USER_PKEY"
        PRIMARY KEY ("USERNAME")
);



CREATE TABLE "ROLE"
(
    "ROLE_ID" SERIAL PRIMARY KEY,
    "ROLE_NAME" VARCHAR(32) NOT NULL

);



CREATE TABLE "PERMISSION"
(
    "PERMISSION_ID" SERIAL PRIMARY KEY,
    "PERMISSION_NAME" VARCHAR(32) NOT NULL

);



CREATE TABLE "USER_ROLE"
(
    "USERNAME" VARCHAR(32) NOT NULL,
    "ROLE_ID" INT NOT NULL,
    CONSTRAINT "USER_ROLE_PKEY"
        PRIMARY KEY ("USERNAME", "ROLE_ID"),
    CONSTRAINT "UR_ROLE_FK"
        FOREIGN KEY ("ROLE_ID")
            REFERENCES "ROLE" ("ROLE_ID")
            ON UPDATE NO ACTION
            ON DELETE NO ACTION,
    CONSTRAINT "UR_USER_FK"
        FOREIGN KEY ("USERNAME")
            REFERENCES "USER" ("USERNAME")
            ON UPDATE CASCADE
            ON DELETE CASCADE
);



CREATE TABLE "ROLE_PERMISSION"
(
    "ROLE_ID" INT NOT NULL,
    "PERMISSION_ID" INT NOT NULL,
    CONSTRAINT "ROLE_PERMISSION_PKEY"
        PRIMARY KEY ("ROLE_ID", "PERMISSION_ID"),
    CONSTRAINT "RP_PERMISSION_FK"
        FOREIGN KEY ("PERMISSION_ID")
            REFERENCES "PERMISSION" ("PERMISSION_ID")
            ON UPDATE CASCADE
            ON DELETE CASCADE,
    CONSTRAINT "RP_ROLE_FK"
        FOREIGN KEY ("ROLE_ID")
            REFERENCES "ROLE" ("ROLE_ID")
            ON UPDATE CASCADE
            ON DELETE CASCADE
);