create sequence CLN_SEQ start with 10 increment by 1;
create table VTS_CLIENT (id bigint not null, name varchar(255), mobile varchar(255), email varchar(255), primary key (id));