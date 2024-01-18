insert into permission (permission_id, permission_name)
values (1, 'read'),
       (2, 'write');

insert into role (role_id, role_name)
values (1, 'ADMIN'),
       (2, 'MANAGER');

insert into role_permission (role_id, permission_id)
values (1, 1),
       (1, 2),
       (2, 1);

insert into user_t (username, password, first_name, last_name, email, locked, disabled)
values ('anton', '$2a$10$eRfSRbe1iHM37FX93nr9z.hLJ.R6LkZGGcoTCoq11zZiRHKymd3K2', 'Anton', 'Shevchenko',
        'anton@gmail.com', false, false);

insert into user_t (username, password, first_name, last_name, email, locked, disabled)
values ('mike', '$$2a$10$eRfHM37FX93yo8zSRbe1i.gKY.R1zZiRHK6LkZGGcoTCoq1nmdJ3P', 'Mike', 'Malen', 'mike@gmail.com',
        false, false);

insert into user_role (username, role_id)
values ('anton', 1);

insert into user_role (username, role_id)
values ('mike', 2);