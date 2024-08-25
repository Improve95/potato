drop table if exists users cascade ;
create table users (
                       id uuid primary key not null ,
                       created_at timestamp without time zone not null ,
                       modified_at timestamp without time zone ,
                       first_name varchar(50) not null ,
                       second_name varchar(50) not null ,
                       phone varchar(11) not null unique ,
                       email varchar(50) not null unique ,
                       birthdate date not null ,
                       password varchar(255) not null
);

drop table if exists sessions;
create table sessions (
                         id uuid primary key not null ,
                         created_at timestamp without time zone not null ,
                         modified_at timestamp without time zone ,
                         access_token varchar(400) not null ,
                         refresh_token varchar(400) not null ,
                         enabled boolean not null,
                         user_id uuid not null references users("id") on delete cascade
);
truncate table sessions;

drop table if exists wallets;
create table wallets (
                         id uuid primary key not null ,
                         created_at timestamp without time zone not null ,
                         modified_at timestamp without time zone ,
                         balance int not null ,
                         user_id uuid not null references users("id") on delete cascade
);