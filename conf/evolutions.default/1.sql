# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  email                     varchar(255) not null,
  password                  varchar(255),
  constraint pk_user primary key (email))
;

create table vare (
  vareid                    bigint not null,
  navn                      varchar(255),
  kategori                  varchar(255),
  x                         integer,
  y                         integer,
  z                         integer,
  pris                      double,
  constraint pk_vare primary key (vareid))
;

create sequence user_seq;

create sequence vare_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists user;

drop table if exists vare;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists user_seq;

drop sequence if exists vare_seq;

