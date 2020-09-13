create table if not exists t_book(
    l_id int primary key auto_increment,
    vc_book_name varchar(50),
    vc_author varchar(50)
);

truncate t_book;
