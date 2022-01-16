CREATE TABLE IF NOT EXISTS persons(
    person_id         serial primary key,
    name              varchar(100),
    birthdate         date,
    residence_country varchar(100),
    document_id       integer,
    foreign key (document_id) references documents
);

CREATE TABLE IF NOT EXISTS documents(
    document_id serial primary key,
    type        varchar(10) not null,
    document    varchar(25) not null,
    unique      (type, document)
);