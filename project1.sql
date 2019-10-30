-- Database: project1
-- DROP DATABASE project1;
CREATE DATABASE project1 WITH OWNER = postgres ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252' TABLESPACE = pg_default CONNECTION
LIMIT
	= -1;

CREATE TABLE ers_reimbursement_status (
	reimb_status_id serial primary key,
	reimb_status varchar(10) not null
);

create table ers_reimbursement_type (
	reimb_type_id serial primary key,
	reimb_type varchar(10) not null
);

create table ers_user_roles (
	ers_user_role_id serial primary key,
	user_role varchar(10) not null
);

create table ers_users (
	ers_users_id serial primary key,
	ers_username varchar(50) not null,
	ers_password text not null,
	password_salt text not null,
	user_first_name varchar(100) not null,
	user_last_name varchar(100) not null,
	user_email varchar(150) not null,
	user_role_id integer references ers_user_roles(id)
);

create table ers_reimbursement (
	reimb_id serial primary key,
	reimb_amount numeric not null,
	reimb_submitted timestamp default(current_timestamp),
	reimb_resolved timestamp default(current_timestamp),
	reimb_description varchar(250),
	reimb_receipt varchar(250),
	reimb_author integer references ers_users(ers_users_id),
	reimb_resolver integer references ers_users(ers_users_id),
	reimb_status_id integer references ers_reimbursement_status(reimb_status_id),
	reimb_type_id integer references ers_reimbursement_type(reimb_type_id)
);

INSERT INTO
	ers_reimbursement_type
VALUES
	(1, 'LODGING'),
	(2, 'TRAVEL'),
	(3, 'FOOD'),
	(4, 'OTHER');

insert into
	ers_reimbursement_status
values
	(1, 'PENDING'),
	(2, 'APPROVED'),
	(3, 'DENIED');