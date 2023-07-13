create table teacher
(
    id   int(10)     not null
        primary key,
    name varchar(30) null
)
    engine = InnoDB
    charset = utf8;

create table student
(
    id   int(10)     not null
        primary key,
    name varchar(30) null,
    tid  int(10)     null,
    constraint fktid
        foreign key (tid) references teacher (id)
)
    engine = InnoDB
    charset = utf8;

create table user
(
    id       varchar(10) not null
        primary key,
    name     varchar(20) null,
    password varchar(20) null
);


