
create table 'user_auth'(
    auth_no int not null auto_increment,
    user_id varchar(100) not null,
    auth varchar(100) not null
)

insert into user_auth( user_id, auth)
values ('user', 'ROLE_USER')

insert into user_auth( user_id, auth)
values ('admin', 'ROLE_USER')


insert into user_auth( user_id, auth)
values ('admin', 'ROLE_ADMIN')

