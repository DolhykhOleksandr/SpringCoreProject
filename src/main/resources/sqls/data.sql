insert into "PERMISSION" ("PERMISSION_NAME")
values ('read'), ('write');

insert into "ROLE" ("ROLE_NAME")
values ('ADMIN'), ('ADMINTRAINEE');

insert into "ROLE_PERMISSION" ("ROLE_ID", "PERMISSION_ID")
values (1,1), (1,2), (2,1);

insert into "USER" ("USERNAME", "PASSWORD", "FIRST_NAME", "LAST_NAME", "EMAIL")
values ('linda', '$2a$10$eRfSRbe1iHM37FX93nr9z.hLJ.R6LkZGGcoTCoq11zZiRHKymd3K2', 'Linda', 'Jones', 'linda@gmail.com');

insert into "USER" ("USERNAME", "PASSWORD", "FIRST_NAME", "LAST_NAME", "EMAIL")
values ('tom', '$2a$10$eRfSRbe1iHM37FX93nr9z.hLJ.R6LkZGGcoTCoq11zZiRHKymd3K2', 'Tom', 'Banderas', 'tom@gmail.com');

insert into "USER_ROLE" ("USERNAME", "ROLE_ID")
values ('linda', 1);

insert into "USER_ROLE" ("USERNAME", "ROLE_ID")
values ('tom', 2);