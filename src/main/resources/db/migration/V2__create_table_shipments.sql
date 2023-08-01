create table shipments
(

    id                   bigint       not null auto_increment,
    user_id              bigint references users (id),
    client_email         varchar(50)  not null,
    weight               bigint(20)   not null,
    public_place         varchar(100) not null,
    neighborhood         varchar(100) not null,
    complement           varchar(100),
    number               varchar(20),
    state                varchar(20)  not null,
    city                 varchar(100) not null,
    zip_code_origin      bigint(10)   not null,
    zip_code_destination bigint(10)   not null,
    price                bigint(100)  not null,
    shipment_status     varchar(20)  not null,


    primary key (id)

);