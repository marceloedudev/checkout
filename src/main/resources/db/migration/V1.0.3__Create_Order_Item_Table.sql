create table public.order_item (
    id serial not null,
    created_at timestamp,
    price float8,
    quantity int4,
    updated_at timestamp,
    uuid varchar(255) unique not null,
    item_id int8,
    order_id int8,
    primary key (id),
    foreign key (order_id) references order_tbl,
    foreign key (item_id) references item
);
