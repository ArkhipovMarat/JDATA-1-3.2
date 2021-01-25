create table CUSTOMERS
(
    id           serial primary key,
    name         varchar(30) not null,
    surname      varchar(30) not null,
    age          int         not null,
    phone_number varchar(30) unique
        check ((age > 0 and age < 100)
            and (phone_number != '')
            and (name != '')
            and (surname != ''))
);

create table ORDERS
(
    id           serial primary key,
    date         timestamp default now(),
    customer_id  int references CUSTOMERS (id),
    product_name varchar(30) not null unique,
    amount       int         not null,
    check (product_name != '')
    );

insert into customers
values (1, 'Alexey', 'Ivanov', 28, '8987-28-28-28'),
       (2, 'Ivan', 'Ivanov', 30, '8987-30-30-30'),
       (3, 'Sergei', 'Ivanov', 45, '8987-45-45-45'),
       (4, 'Vasily', 'Vasiliev', 29, '8987-29-29-29'),
       (5, 'alexey', 'Alexeev', 41, '8987-41-41-41');

insert into orders
values (1, now(), 1, 'milk', 1),
       (2, now(), 1, 'water', 2),
       (3, now(), 2, 'beer', 3),
       (4, now(), 3, 'juice', 4),
       (5, now(), 5, 'cola', 5);