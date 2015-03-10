# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table handleliste (
  email                     varchar(255) not null,
  constraint pk_handleliste primary key (email))
;

create table kategori (
  kategori_navn             varchar(255) not null,
  closest_node              integer,
  constraint pk_kategori primary key (kategori_navn))
;

create table noder (
  nodenummer                integer not null,
  position_x                integer,
  position_y                integer,
  constraint pk_noder primary key (nodenummer))
;

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

create sequence handleliste_seq;

create sequence kategori_seq;

create sequence noder_seq;

create sequence user_seq;

create sequence vare_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists handleliste;

drop table if exists kategori;

drop table if exists noder;

drop table if exists user;

drop table if exists vare;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists handleliste_seq;

drop sequence if exists kategori_seq;

drop sequence if exists noder_seq;

drop sequence if exists user_seq;

drop sequence if exists vare_seq;

