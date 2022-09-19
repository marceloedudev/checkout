create table public.order_tbl (
    id serial not null,
    cpf varchar(255),
    created_at timestamp,
    status int4,
    updated_at timestamp,
    user_id int8,
    uuid varchar(255) unique not null,
    primary key (id)
);
