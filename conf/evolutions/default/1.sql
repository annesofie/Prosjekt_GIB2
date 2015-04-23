# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table edge (
  id                        integer auto_increment not null,
  source_id                 integer,
  destination_id            integer,
  constraint pk_edge primary key (id))
;

create table handleliste_vare (
  handleliste_id            bigint auto_increment not null,
  user_email                varchar(255),
  vare_id                   bigint,
  constraint pk_handleliste_vare primary key (handleliste_id))
;

create table kategori (
  kategori_navn             varchar(255) not null,
  closest_node              integer,
  constraint pk_kategori primary key (kategori_navn))
;

create table user (
  email                     varchar(255) not null,
  password                  varchar(255),
  constraint pk_user primary key (email))
;

create table vare (
  vareid                    bigint auto_increment not null,
  navn                      varchar(255),
  kategori                  varchar(255),
  x                         integer,
  y                         integer,
  z                         integer,
  pris                      double,
  pic                       varchar(255),
  hylle_pic                 varchar(255),
  vertex_id                 integer,
  in_shoppinglist           tinyint(1) default 0,
  constraint pk_vare primary key (vareid))
;

create table vertex (
  id                        integer auto_increment not null,
  x_pos                     integer,
  y_pos                     integer,
  key_value                 integer,
  beskrivelse               varchar(255),
  hylle_x                   integer,
  hylle_y                   integer,
  constraint pk_vertex primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table edge;

drop table handleliste_vare;

drop table kategori;

drop table user;

drop table vare;

drop table vertex;

SET FOREIGN_KEY_CHECKS=1;

