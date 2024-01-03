CREATE TABLE IF NOT EXISTS PUBLIC."ORDER" (
                                              "ORDER_ID" INTEGER NOT NULL
                                                  GENERATED ALWAYS AS IDENTITY (
                                                      INCREMENT 1
                                                      START 1
                                                      MINVALUE 1
                                                      MAXVALUE 2147483647
                                                      CACHE 1 ),
                                              "DATE" DATE NOT NULL,
                                              "COST" DOUBLE PRECISION NOT NULL,
                                              CONSTRAINT ORDER_PKEY
                                                  PRIMARY KEY ("ORDER_ID")
);


CREATE TABLE IF NOT EXISTS PUBLIC."PRODUCT" (
                                                "PRODUCT_ID" INTEGER NOT NULL
                                                    GENERATED ALWAYS AS IDENTITY (
                                                        INCREMENT 1
                                                        START 1
                                                        MINVALUE 1
                                                        MAXVALUE 2147483647
                                                        CACHE 1 ),
                                                "NAME" CHARACTER VARYING(16) NOT NULL,
                                                "COST" double precision NOT NULL,
                                                CONSTRAINT PRODUCT_PKEY
                                                    PRIMARY KEY ("PRODUCT_ID")
);


CREATE TABLE IF NOT EXISTS PUBLIC."ORDER_PRODUCT" (
                                                      "ORDER_ID" INTEGER NOT NULL,
                                                      "PRODUCT_ID" INTEGER NOT NULL,
                                                      CONSTRAINT ORDER_PRODUCT_PKEY
                                                          PRIMARY KEY ("ORDER_ID", "PRODUCT_ID"),
                                                      CONSTRAINT ORDER_FK
                                                          FOREIGN KEY ("ORDER_ID")
                                                              REFERENCES PUBLIC."ORDER" ("ORDER_ID")
                                                              ON UPDATE CASCADE
                                                              ON DELETE CASCADE,
                                                      CONSTRAINT PRODUCT_FK
                                                          FOREIGN KEY ("PRODUCT_ID")
                                                              REFERENCES PUBLIC."PRODUCT" ("PRODUCT_ID")
                                                              ON UPDATE CASCADE
                                                              ON DELETE CASCADE
);



CREATE TABLE IF NOT EXISTS PUBLIC."USER"
(
    "USERNAME" CHARACTER VARYING(32) NOT NULL,
    "PASSWORD" CHARACTER VARYING(64) NOT NULL,
    "FIRST_NAME" CHARACTER VARYING(32) NOT NULL,
    "LAST_NAME" CHARACTER VARYING(32) NOT NULL,
    "EMAIL" CHARACTER VARYING(32) NOT NULL,
    CONSTRAINT "USER_PKEY"
        PRIMARY KEY ("USERNAME")
);



CREATE TABLE IF NOT EXISTS PUBLIC."ROLE"
(
    "ROLE_ID" INTEGER NOT NULL
        GENERATED ALWAYS AS IDENTITY (
            INCREMENT 1
            START 1
            MINVALUE 1
            MAXVALUE 2147483647
            CACHE 1 ),
    "ROLE_NAME" CHARACTER VARYING(32) NOT NULL,
    CONSTRAINT "ROLE_PKEY"
        PRIMARY KEY ("ROLE_ID")
);



CREATE TABLE IF NOT EXISTS PUBLIC."PERMISSION"
(
    "PERMISSION_ID" INTEGER NOT NULL
        GENERATED ALWAYS AS IDENTITY (
            INCREMENT 1
            START 1
            MINVALUE 1
            MAXVALUE 2147483647
            CACHE 1 ),
    "PERMISSION_NAME" CHARACTER VARYING(32) NOT NULL,
    CONSTRAINT "PERMISSION_PKEY"
        PRIMARY KEY ("PERMISSION_ID")
);



CREATE TABLE IF NOT EXISTS PUBLIC."USER_ROLE"
(
    "USERNAME" CHARACTER VARYING(32) NOT NULL,
    "ROLE_ID" INTEGER NOT NULL,
    CONSTRAINT "USER_ROLE_PKEY"
        PRIMARY KEY ("USERNAME", "ROLE_ID"),
    CONSTRAINT "UR_ROLE_FK"
        FOREIGN KEY ("ROLE_ID")
            REFERENCES PUBLIC."ROLE" ("ROLE_ID")
            ON UPDATE NO ACTION
            ON DELETE NO ACTION,
    CONSTRAINT "UR_USER_FK"
        FOREIGN KEY ("USERNAME")
            REFERENCES PUBLIC."USER" ("USERNAME")
            ON UPDATE CASCADE
            ON DELETE CASCADE
);



CREATE TABLE IF NOT EXISTS PUBLIC."ROLE_PERMISSION"
(
    "ROLE_ID" INTEGER NOT NULL,
    "PERMISSION_ID" INTEGER NOT NULL,
    CONSTRAINT "ROLE_PERMISSION_PKEY"
        PRIMARY KEY ("ROLE_ID", "PERMISSION_ID"),
    CONSTRAINT "RP_PERMISSION_FK"
        FOREIGN KEY ("PERMISSION_ID")
            REFERENCES PUBLIC."PERMISSION" ("PERMISSION_ID")
            ON UPDATE CASCADE
            ON DELETE CASCADE,
    CONSTRAINT "RP_ROLE_FK"
        FOREIGN KEY ("ROLE_ID")
            REFERENCES public."ROLE" ("ROLE_ID")
            ON UPDATE CASCADE
            ON DELETE CASCADE
);