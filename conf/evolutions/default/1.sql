# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table edge (
  id                        integer not null,
  source_id                 integer,
  destination_id            integer,
  constraint pk_edge primary key (id))
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
  vareid                    bigint not null,
  navn                      varchar(255),
  kategori                  varchar(255),
  x                         integer,
  y                         integer,
  z                         integer,
  pris                      double,
  pic                       varchar(255),
  vertex_id                 integer,
  constraint pk_vare primary key (vareid))
;

create table vertex (
  id                        integer not null,
  x_pos                     integer,
  y_pos                     integer,
  key                       integer,
  infinite                  integer,
  beskrivelse               varchar(255),
  constraint pk_vertex primary key (id))
;

create sequence edge_seq;

create sequence kategori_seq;

create sequence user_seq;

create sequence vare_seq;

create sequence vertex_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists edge;

drop table if exists kategori;

drop table if exists user;

drop table if exists vare;

drop table if exists vertex;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists edge_seq;

drop sequence if exists kategori_seq;

drop sequence if exists user_seq;

drop sequence if exists vare_seq;

drop sequence if exists vertex_seq;

