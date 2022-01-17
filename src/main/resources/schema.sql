CREATE TABLE IF NOT EXISTS documents(
    document_id         serial primary key,
    type                varchar(10) not null,
    document            varchar(25) not null,
    residence_country   varchar(100),
    unique      (type, document, residence_country)
);

CREATE TABLE IF NOT EXISTS persons(
    person_id         serial primary key,
    name              varchar(100),
    birthdate         date,
    document_id       integer,
    foreign key (document_id) references documents
);

CREATE TABLE IF NOT EXISTS parents(
    parent_id integer,
    child_id integer,
    foreign key (parent_id) references persons,
    foreign key (child_id) references persons,
    unique (parent_id, child_id)
);