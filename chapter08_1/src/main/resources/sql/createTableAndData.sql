--liquibase formatted sql
--changeset stevedonie:create-multiple-tables
create table if not exists student(
    sid int primary key auto_increment,
    sname varchar(50),
    sage int,
    sbirthday varchar(50)
);
--rollback drop table student;

--changeset lsc:2
insert into student values(1, '张三', 10, '1994-01-01');
insert into student values(2, '李四', 20, '1995-01-01');
insert into student values(3, '王五', 30, '1996-01-01');
--rollback truncate student;
