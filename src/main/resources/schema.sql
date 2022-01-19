CREATE TABLE IF NOT EXISTS persons(
    person_id         serial primary key,
    name              varchar(100),
    birthdate         date
);

CREATE TABLE IF NOT EXISTS documents(
    document_id         serial primary key,
    type                varchar(10) not null,
    document            varchar(25) not null,
    residence_country   varchar(100),
    person_id           integer,
    unique      (type, document, residence_country),
    foreign key (person_id) references persons
);

CREATE TABLE IF NOT EXISTS parents(
    parent_id   integer,
    child_id    integer,
    foreign key (parent_id) references persons,
    foreign key (child_id) references persons,
    unique (parent_id, child_id)
);

CREATE TABLE IF NOT EXISTS phones(
    phone_id    serial primary key ,
    number      varchar(25) not null,
    person_id     integer not null,
    foreign key (person_id) references phones,
    unique(number)
);

CREATE TABLE IF NOT EXISTS addresses(
    address_id  serial primary key ,
    street      varchar(100) not null,
    number      varchar(50) not null,
    apartment   bool,
    person_id   integer not null,
    foreign key (person_id) references phones,
    unique(street, number)
);