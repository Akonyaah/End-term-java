create table book(
	isbn serial Primary Key,
	name varchar(255),
	author varchar(255),
	count int
)

create table library(
id serial Primary key,
name varchar(255),
	surname varchar(255),
	username varchar(255),
	password varchar(255));
)

create table users(
id serial Primary key,
name varchar(255),
	surname varchar(255),
	username varchar(255),
	password varchar(255),
);

create table booking(
    id serial PRIMARY KEY,
	date Timestamp,
	book_id int,
	user_id int,
	book_name varchar(255),
	user_name varchar(255)
)