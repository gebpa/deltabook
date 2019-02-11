CREATE TABLE users (
	id bigint primary key,
	login varchar(100) unique not null,
	role varchar(10) not null,
	password varchar(100) not null,
	first_name varchar(40),
	last_name varchar(40),
	picture text
);

CREATE TABLE messages (
	id bigint primary key,
	sender_id bigint references users(id),
	recipient_id bigint references users(id),
	body text not null,
	created_at timestamp
);

CREATE TABLE contacts (
	id bigint primary key,
	friend_from_id bigint references users(id),
	friend_to_id bigint references users(id),
	isAccepted boolean,
	requestMessage text
);