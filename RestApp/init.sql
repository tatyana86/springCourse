CREATE TABLE Person(
		id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
		name varchar(100) NOT NULL,
		age int,
		email varchar(100)
);

INSERT INTO Person(name, age, email) VALUES ('Tom', 25, 'tom@mail.com');
INSERT INTO Person(name, age, email) VALUES ('Bob', 51, 'bob@mail.com');
INSERT INTO Person(name, age, email) VALUES ('Katy', 38, 'katy@mail.com');