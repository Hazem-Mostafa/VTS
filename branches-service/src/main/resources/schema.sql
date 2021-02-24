create sequence BRN_SEQ start with 10 increment by 1;
create table VTS_BRANCH (id bigint not null, name varchar(255), address varchar(255), phone varchar(255) , OPEN_TIME time, CLOSE_TIME time, primary key (id));

create sequence BST_SEQ start with 10 increment by 1;
create table VTS_BRANCH_STORE (id bigint not null, branch_id bigint not null, vaccine_id bigint not null,
                                quantity int, name varchar(255), address varchar(255), production_date date, expiration_date date, primary key (id));

create sequence BVS_SEQ start with 10 increment by 1;
create table VTS_BRANCH_VISITS(id bigint not null, branch_id bigint not null, vaccine_id bigint not null, client_id bigint not null,
                                visit_date timestamp, status varchar(255), payment_method varchar(255), primary key (id));