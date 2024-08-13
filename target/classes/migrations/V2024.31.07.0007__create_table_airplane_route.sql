CREATE TABLE airplane_route (
    route_id int unique,
    airplane_id int unique,
    foreign key (route_id) references route(id),
    foreign key (airplane_id) references airplane(id)
)