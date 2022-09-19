create table item (
    id serial not null,
    created_at timestamp,
    density int4,
    description varchar(255),
    height int4,
    length int4,
    price float8,
    updated_at timestamp,
    uuid varchar(255) unique not null,
    volume int4,
    weight int4,
    width int4,
    primary key (id)
);
