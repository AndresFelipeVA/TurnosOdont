create table users(
	username varchar_ignorecase(50) not null primary key,
	password varchar_ignorecase(100) not null,
	enabled boolean not null
);

create table authorities (
	username varchar_ignorecase(50) not null,
	authority varchar_ignorecase(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

INSERT INTO users (username, password, enabled)
  values ('user1', '$2a$10$2./wFyiPIFoTdxkM.eEPZuYTqnRqjt5j8PSzAKogSqtquuUJGkJ8S', true);

INSERT INTO authorities (username, authority)
  values ('user1', 'ROLE_USER');

INSERT INTO users (username, password, enabled)
  values ('user2', '$2a$10$2WirKL8hLmoBqle.F6xSiuyqCFlI7fXOlFeiw93p1dm4OtNP6zoJq', true);

INSERT INTO authorities (username, authority)
  values ('user2', 'ROLE_ADMIN');