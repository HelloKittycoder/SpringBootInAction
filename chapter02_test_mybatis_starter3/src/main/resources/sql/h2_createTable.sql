create table if not exists student(
    sid int primary key auto_increment,
    sname varchar(50),
    sage int,
    sbirthday varchar(50)
);

truncate table student;
