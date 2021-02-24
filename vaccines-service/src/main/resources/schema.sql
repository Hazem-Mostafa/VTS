create sequence VCC_SEQ start with 10 increment by 1;
create table VTS_VACCINE (id bigint not null, name varchar(255), dose varchar(255), repetition_count int, primary key (id));