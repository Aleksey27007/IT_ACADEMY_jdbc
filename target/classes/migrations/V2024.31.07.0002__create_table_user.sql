CREATE TABLE user (
    id int primary key auto_increment,
    name varchar(128),
    phone varchar(64),
    role_id int unique,
    foreign key (role_id) references role(id)
)