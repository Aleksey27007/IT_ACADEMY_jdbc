CREATE TABLE ticket (
    id int primary key auto_increment,
    user_id int unique,
    foreign key (user_id) references user(id),
    route_id int unique,
    foreign key (route_id)  references route(id),
    status varchar(64),
    airplane_id int unique,
    foreign key (airplane_id) references airplane(id),
    created_at int
)