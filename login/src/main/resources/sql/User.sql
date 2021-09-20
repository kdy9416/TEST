Create table login_user(
	id varchar(100) primary key,
    password varchar(100) NOT NULL,
    name varchar(20) NOT NULL,
    join_date Timestamp default now()
	
);
