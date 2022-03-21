
drop database sqa;
create database sqa;
use sqa;


CREATE TABLE tblCustomer (
	id int NOT NULL AUTO_INCREMENT, 
    fullname varchar(255) NOT NULL, 
    identityCard varchar(255) NOT NULL UNIQUE, 
    telephone varchar(255) NOT NULL UNIQUE, 
    address varchar(255) NOT NULL, 
    username varchar(255) NOT NULL UNIQUE, 
    password varchar(255) NOT NULL, 
    PRIMARY KEY (id));
 
 
CREATE TABLE tblEmployee (
	id int NOT NULL AUTO_INCREMENT, 
    fullname varchar(255) NOT NULL, 
    telephone varchar(255) NOT NULL UNIQUE, 
    position varchar(255) NOT NULL,
    address varchar(255) NOT NULL, 
    salary float NOT NULL, 
    username varchar(255) NOT NULL UNIQUE, 
    password varchar(255) NOT NULL, 
    PRIMARY KEY (id));
    
CREATE TABLE tblCarCategory (
	id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL UNIQUE, 
    PRIMARY KEY (id));
    
CREATE TABLE tblBranch (
	id int NOT NULL AUTO_INCREMENT, 
	name varchar(255) NOT NULL UNIQUE, 
	PRIMARY KEY (id));
    
CREATE TABLE tblCar (
	id int NOT NULL AUTO_INCREMENT, 
    name varchar(255) NOT NULL, 
    color varchar(255) NOT NULL, 
    licensePlate varchar(255) NOT NULL UNIQUE, 
    seatNumber int NOT NULL, 
    price float NOT NULL,
    image varchar(255) NOT NULL,
    status varchar(255) NOT NULL,
    categoryId int NOT NULL,
    branchId int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (branchId) references tblBranch(id),
    FOREIGN KEY (categoryId) references tblCarCategory(id)
    );
    

    
CREATE TABLE tblBill (
	id int NOT NULL AUTO_INCREMENT, 
    createdAt datetime NOT NULL, 
    paymentStatus varchar(255) NOT NULL, 
    confirmStatus varchar(255) NOT NULL, 
    paymentMethod varchar(255) NOT NULL, 
    totalPrice float NOT NULL, 
    startDate date NOT NULL, 
    endDate date NOT NULL,
    employeeId int, 
    carId int NOT NULL, 
    customerId int NOT NULL, 
    PRIMARY KEY (id),
    foreign key (employeeId) references tblEmployee(id),
    foreign key (carId) references tblCar(id),
    foreign key (customerId) references tblCustomer(id)
    );
    


insert into tblCustomer(fullname, identityCard, telephone, address, username, password) values ('Do Xuan Hieu', '038012312313', '0976199402', 'Hoang Mai', 'hieudx1', '123456');
insert into tblCustomer(fullname, identityCard, telephone, address, username, password) values ('Nguyen Bao Long', '0380456456456', '0972903455', 'Cau Giay', 'longnb', '123456');

insert into tblEmployee(fullname, telephone,position,  address, salary, username, password) values ('Vu Duc Duy', '0946836148', 'admin', 'Hoang Mai', '10000', 'duyvd', '123456');
insert into tblEmployee(fullname, telephone, position, address, salary, username, password) values ('Truong Quang Khai', '0913873134', 'admin', 'Ha Dong', '20000', 'khaitq', '123456');

insert into tblCarCategory(name) values ('A');
insert into tblCarCategory(name) values ('B');
insert into tblCarCategory(name) values ('C');
insert into tblCarCategory(name) values ('D');

insert into tblBranch(name) values ('Mec');
insert into tblBranch(name) values ('Audi');
insert into tblBranch(name) values ('Toyota');
insert into tblBranch(name) values ('Honda');
insert into tblBranch(name) values ('Vinfast');


insert into tblCar(name, color, licensePlate, seatNumber, price, image, status, categoryId, branchId) 
values ('VINFAST LUX  A2.0', 'Brahminy White', '30A-686.86', 4, 600000, 
'https://shop.vinfastauto.com/on/demandware.static/-/Sites-app_vinfast_vn-Library/default/dw218d05cc/images/Lux-A/hinh-anh-gia-VinFast-Lux-a2.0-price-mau-trang-brahminy-white.png',
 'A', 3, 5);
 
insert into tblCar(name, color, licensePlate, seatNumber, price, image, status, categoryId, branchId) 
values ('Honda City', 'Red', '30A-555.55', 4, 500000, 
'https://i.xeoto.com.vn/auto/honda/city/honda-city-2021.png',
 'A', 2, 4);
 
insert into tblCar(name, color, licensePlate, seatNumber, price, image, status, categoryId, branchId) 
values ('Toyota Camry', 'White', '30A-666.66', 5, 700000, 
'https://cdn1.otosaigon.com/data-resize/carinfo/20210727/EZ03OyWCbnG8t4d1Su88pZKOZpOgoSpXiFRu3uxP.jpg?w=838',
 'A', 4, 3);
 
 insert into tblCar(name, color, licensePlate, seatNumber, price, image, status, categoryId, branchId) 
values ('Toyota Fortuner', 'White', '30A-777.77', 7, 900000, 
'https://i.xeoto.com.vn/auto/w600/toyota/fortuner/toyota-fortuner-2020-52327.png',
 'A', 4, 3);
 
 insert into tblCar(name, color, licensePlate, seatNumber, price, image, status, categoryId, branchId) 
values ('VINFAST FADIL', 'Luxury Blue', '30A-444.44', 5, 500000, 
'https://shop.vinfastauto.com/on/demandware.static/-/Sites-vinfast_vn_master/default/dw02ef2e69/images/FADIL/SMA/CE18.png',
 'A', 1, 5);
 
 
 
 
 
 
 
 







