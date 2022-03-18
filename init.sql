
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


insert into tblCar(name, color, licensePlate, seatNumber, image, status, categoryId, branchId) 
values ('VINFAST LUX  A2.0', 'Brahminy White', '30A-686.86', 4 , 
'https://shop.vinfastauto.com/on/demandware.static/-/Sites-app_vinfast_vn-Library/default/dw218d05cc/images/Lux-A/hinh-anh-gia-VinFast-Lux-a2.0-price-mau-trang-brahminy-white.png',
 'A', 3 , 5);







