insert into permission (permission_id, permission_name) values (1,'read'), (2, 'write');

insert into role (role_id, role_name) values (1,'ADMIN'), (2, 'ADMINTRAINEE');

insert into role_permission (role_id, permission_id) values (1,1), (1,2), (2,1);

insert into user_t (username, password, first_name, last_name, email, locked, disabled) values ('linda', '$2a$10$eRfSRbe1iHM37FX93nr9z.hLJ.R6LkZGGcoTCoq11zZiRHKymd3K2', 'Linda', 'Jones', 'linda@gmail.com', false, false);

insert into user_t (username, password, first_name, last_name, email, locked, disabled) values ('tom', '$2a$10$eRfSRbe1iHM37FX93nr9z.hLJ.R6LkZGGcoTCoq11zZiRHKymd3K2', 'Tom', 'Banderas', 'tom@gmail.com', false, false);

insert into user_role (username, role_id) values ('linda', 1);

insert into user_role (username, role_id) values ('tom', 2);