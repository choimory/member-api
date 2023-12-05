create table if not exists member
(
    id          bigint auto_increment,
    identity    varchar(255) not null,
    password    varchar(500) not null,
    email       text         not null,
    profile     text         null,
    created_at  datetime     not null,
    modified_at datetime     null,
    deleted_at  datetime     null,
    constraint member_pk
        primary key (id)
);

create unique index if not exists member_email_uindex
    on member (email);

create unique index if not exists member_identity_uindex
    on member (identity);




create table if not exists member_authority
(
    id          bigint auto_increment,
    member_id   bigint       not null,
    level       varchar(255) not null,
    created_at  datetime     not null,
    modified_at datetime     null,
    deleted_at  datetime     null,
    constraint member_authority_pk
        primary key (id)
);

create unique index if not exists member_authority_member_id_uindex
    on member_authority (member_id);




create table if not exists member_image
(
    id                   bigint auto_increment,
    member_id            bigint       not null,
    type                 varchar(255) not null,
    original_file_name   text         not null,
    file_name            text         not null,
    file_path            text         not null,
    file_size            bigint       not null,
    thumb_nail_file_name text         not null,
    thumb_nail_file_path text         not null,
    thumb_nail_file_size int          not null,
    created_at           datetime     not null,
    modified_at          datetime     null,
    deleted_at           datetime     null,
    constraint member_image_pk
        primary key (id)
);



create table if not exists member_suspension
(
    id           bigint auto_increment,
    member_id    bigint   not null,
    reason       text     null,
    suspended_at datetime not null,
    suspended_to datetime not null,
    created_at   datetime not null,
    modified_at  datetime null,
    deleted_at   datetime null,
    constraint member_suspension_pk
        primary key (id)
);

