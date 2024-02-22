create table 'user' (
    'NO' int not null auto_increment,
    'USER_ID' varchar(100) not null,
    'USER_PW' varchar(200) not null,
    'NAME' varchar(100) not null,
    'EMAIL' varchar(100) not null,
    'REG_DATE' timestamp not null default CURRENT_TIMESTAMP,
    'UPD_DATE' timestamp not null default CURRENT_TIMESTAMP,
    'ENABLED' int default 1,
    PRIMARY KEY('NO')
) COMMENT ='회원';

insert into user(user_id, user_pw, name, email)
values ('user' , 'password','사용자','user@email.com');

insert into user(user_id , user_pw,name, email)
values ('admin', 'password','관리자','admin@email.com');