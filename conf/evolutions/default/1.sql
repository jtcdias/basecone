# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table invoice_data_set (
  id                            bigint auto_increment not null,
  distance                      double,
  value                         varchar(255),
  tag                           tinyint(1) default 0,
  file_name                     varchar(255),
  constraint pk_invoice_data_set primary key (id)
);

create table invoice_training_set (
  id                            bigint auto_increment not null,
  pos_left                      bigint,
  width                         bigint,
  height                        bigint,
  pos_top                       bigint,
  closest_synonymous            varchar(255),
  distance                      double,
  key_word                      varchar(255),
  value                         varchar(255),
  tag                           tinyint(1) default 0,
  file_name                     varchar(255),
  constraint pk_invoice_training_set primary key (id)
);


# --- !Downs

drop table if exists invoice_data_set;

drop table if exists invoice_training_set;

