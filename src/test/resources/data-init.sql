-- CREATE TABLE IF NOT EXISTS Movie (id int8 not null, name varchar(255), primary key (id));
-- CREATE SEQUENCE movie_id_seq START 1;
-- CREATE SEQUENCE hibernate_sequence START 1;
--
-- SET REFERENTIAL_INTEGRITY FALSE;
-- truncate table Movie_Genre;
-- -- truncate table Movie_Details;
-- truncate table Movie;
-- truncate table Review;
-- truncate table Genre;
-- SET REFERENTIAL_INTEGRITY TRUE;

insert into Movie (name,certification, id) values ('Inception', 1,-1L);
insert into Movie (name,certification, id) values ('Memento', 2,-2L);

insert into Review (author, content, movie_id, id) values ('max', 'au top !', -1L, -1L);
insert into Review (author, content, movie_id, id) values ('ernest', 'bof bof', -1L, -2L);

insert into Genre (name, id) values ('Action', -1L);