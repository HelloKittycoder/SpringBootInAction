create table if not exists student(
    sid int primary key auto_increment,
    sname varchar(50),
    sage int,
    sbirthday varchar(50)
);

-- h2语法需要加table关键字
truncate table student;
