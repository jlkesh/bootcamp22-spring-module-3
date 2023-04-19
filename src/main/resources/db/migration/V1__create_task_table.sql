create table if not exists task
(
    id          uuid primary key      default gen_random_uuid(),
    name        varchar(100) not null,
    description varchar,
    label       varchar,
    created_at  timestamp    not null default current_timestamp
);