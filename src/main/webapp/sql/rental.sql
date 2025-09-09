create database Rental_space_management;
use rental_space_management;

create table MatBang(
	id int auto_increment primary key,
    ma_mat_bang varchar(20) not null unique,
    trang_thai varchar(20) not null,
    dien_tich decimal(10, 2) not null,
    tang int not null,
    loai varchar(30) not null,
    gia_tien bigint not null,
    ngay_bat_dau date not null,
    ngay_ket_thuc date not null,
	created_at timestamp default current_timestamp
    
);