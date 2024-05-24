/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     22/05/2024 17:22:29                          */
/*==============================================================*/


drop table if exists customers;

drop table if exists items;

drop table if exists orders;

/*==============================================================*/
/* Table: customers                                             */
/*==============================================================*/
create table customers (
   customer_id          SERIAL not null,
   customer_name        VARCHAR(64)          null,
   customer_address     VARCHAR(255)         null,
   customer_code        VARCHAR(32)          null,
   customer_phone       VARCHAR(32)          null,
   is_active            INT2                 null,
   last_order_date      DATE                 null,
   pic                  VARCHAR(255)         null,
   constraint PK_CUSTOMERS primary key (customer_id)
);

/*==============================================================*/
/* Table: items                                                 */
/*==============================================================*/
create table items (
   items_id             SERIAL not null,
   items_name           VARCHAR(64)          null,
   items_code           VARCHAR(32)          null,
   stock                INT4                 null,
   price                INT4                 null,
   is_available         INT2                 null,
   last_re_stock        DATE                 null,
   constraint PK_ITEMS primary key (items_id)
);

/*==============================================================*/
/* Table: orders                                                */
/*==============================================================*/
create table orders (
   order_id             SERIAL not null,
   order_code           VARCHAR(32)          null,
   order_date          DATE                 null,
   total_price          INT4                 null,
   customer_id          INT4                 null,
   items_id             INT4                 null,
   quantity             INT4                 null,
   constraint PK_ORDERS primary key (order_id)
);

alter table orders
   add constraint FK_ORDERS_REFERENCE_CUSTOMER foreign key (customer_id)
      references customers (customer_id)
      on delete restrict on update cascade;

alter table orders
   add constraint FK_ORDERS_REFERENCE_ITEMS foreign key (items_id)
      references items (items_id)
      on delete restrict on update cascade;

