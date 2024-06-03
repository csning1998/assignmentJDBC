grant all privileges on database postgres to postgres;

drop table users;
drop table medical_record;
alter table users owner to postgres;
alter table medical_record owner to postgres;

create table users
(
    _id            serial       primary key,
    employee_email varchar(50)  not null,
    employee_name  varchar(50)  not null,
    employee_id    varchar(50)  not null
        unique,
    hashedpwd      varchar(255) not null,
    signature      text         not null
);
create table medical_record
(
    _mid               serial        primary key,
    patient_name       varchar(255)  not null,
    patient_identifier varchar(15)   not null,
    patient_birthdate  date          not null,
    patient_gender     varchar(10)   not null,
    phone_number       varchar(20),
    patient_address    text,
    patient_height     numeric(5, 2) not null,
    patient_weight     numeric(5, 2) not null,
    first_visit_date   date          not null,
    family_history     text          not null,
    personal_history   text          not null,
    medical_record     text          not null
);