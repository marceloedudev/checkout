CREATE TABLE public.users (
  id SERIAL NOT NULL,
  username varchar(100) UNIQUE NOT NULL,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) DEFAULT NULL,
  uuid varchar(255) not null,
  created_at timestamp,
  updated_at timestamp,
  PRIMARY KEY (id)
);
