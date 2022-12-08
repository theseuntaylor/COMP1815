create table if not exists BOOKS (
 id integer primary key autoincrement,
BOOK_NAME text not null,
BOOK_AUTHOR text not null,
BOOK_STATUS text,
BOOK_TYPE text,
YEAR text,
PUBLISHER text
);

create table if not exists AUTHORS (
 id integer primary key autoincrement,
AUTHOR_NAME text not null
);

create table if not exists PUBLISHERS(
 id integer primary key autoincrement ,
PUBLISHER_NAME text not null
);

drop table BOOKS;
drop table PUBLISHERSdrop table AUTHORS;