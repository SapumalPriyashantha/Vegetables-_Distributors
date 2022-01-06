DROP DATABASE IF EXISTS Vegetables_Distributors;
CREATE DATABASE IF NOT EXISTS Vegetables_Distributors;
SHOW DATABASES ;
USE Vegetables_Distributors;

#---------------------------------------------------------------------------------------------
DROP TABLE IF EXISTS Supplier;
CREATE TABLE IF NOT EXISTS Supplier(
    SupplierId VARCHAR(6),
    SupplierName VARCHAR(30) NOT NULL DEFAULT 'Unknown',
    SupplierAddress VARCHAR(30),
    SupplierContactNumber INT(10),
    CONSTRAINT PRIMARY KEY (SupplierId)
    );
SHOW TABLES ;
DESCRIBE Supplier;

#---------------------
DROP TABLE IF EXISTS Item;
CREATE TABLE IF NOT EXISTS Item(

    ItemCode VARCHAR(6),
    SupplierId VARCHAR(6),
    Description VARCHAR(50),
    GetUnitPrice DOUBLE DEFAULT 0.00,
    SellUnitPrice DOUBLE DEFAULT 0.00,
    StockQty INT(5),
    QtyOnHand INT(5),
    Discount DOUBLE DEFAULT 0.00,

    CONSTRAINT PRIMARY KEY (ItemCode),
    CONSTRAINT FOREIGN KEY (SupplierId) REFERENCES Supplier(SupplierId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE Item;


#------------------------
DROP TABLE IF EXISTS SupplierOrder ;
CREATE TABLE IF NOT EXISTS SupplierOrder (
    SupplierOrderID VARCHAR(6),
    SupplierOrderDate DATE,
    SupplierId VARCHAR(6),
    TotalCost DOUBLE ,
    CONSTRAINT PRIMARY KEY (SupplierOrderID),
    CONSTRAINT FOREIGN KEY (SupplierId) REFERENCES Supplier(SupplierId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE SupplierOrder;


#-----------------------
DROP TABLE IF EXISTS SupplierOrderDetails;
CREATE TABLE IF NOT EXISTS SupplierOrderDetails(
    SupplierOrderID VARCHAR(6),
    ItemCode VARCHAR(6),
    OrderQTY INT(11),
    cost DOUBLE,
    CONSTRAINT PRIMARY KEY (SupplierOrderID, ItemCode),
    CONSTRAINT FOREIGN KEY (ItemCode) REFERENCES Item(ItemCode) ON DELETE CASCADE ON UPDATE CASCADE ,
    CONSTRAINT FOREIGN KEY (SupplierOrderID) REFERENCES SupplierOrder(SupplierOrderID) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE SupplierOrderDetails;


#-------------------------------
DROP TABLE IF EXISTS Customer;
CREATE TABLE IF NOT EXISTS Customer(
    CustomerId VARCHAR(6),
    CustomerName VARCHAR(30) NOT NULL DEFAULT 'Unknown',
    ShopAddress VARCHAR(30),
    CustomerContactNumber INT (10),

    CONSTRAINT PRIMARY KEY (CustomerId)
    );
SHOW TABLES ;
DESCRIBE Customer;


#---------------------------------
DROP TABLE IF EXISTS Driver;
CREATE TABLE IF NOT EXISTS Driver(
    DriverId VARCHAR(6),
    DriverName VARCHAR(30) NOT NULL DEFAULT 'Unknown',
    DriverLicenceNumber INT (10),
    DriverContactNumber INT (10),

    CONSTRAINT PRIMARY KEY (DriverId)
    );
SHOW TABLES ;
DESCRIBE Driver;


#---------------------
DROP TABLE IF EXISTS Vehical;
CREATE TABLE IF NOT EXISTS Vehical(
    VehicalId VARCHAR(6),
    VehicalNumber VARCHAR(30) NOT NULL DEFAULT 'Unknown',
    VehicalType VARCHAR (10),

    CONSTRAINT PRIMARY KEY (VehicalId)
    );
SHOW TABLES ;
DESCRIBE Vehical;


#---------------------
DROP TABLE IF EXISTS CustomerOrder ;
CREATE TABLE IF NOT EXISTS CustomerOrder (
    CustomerOrderID VARCHAR(6),
    CustomerOrderDate DATE,
    CustomerOrderDeliveryDate DATE,
    CustomerId VARCHAR(6),
    TotalCost DOUBLE ,
    TotalDiscount DOUBLE ,
    Status VARCHAR(6),

    CONSTRAINT PRIMARY KEY (CustomerOrderID),
    CONSTRAINT FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE CustomerOrder;


#-----------------------
DROP TABLE IF EXISTS CustomerOrderDetails;
CREATE TABLE IF NOT EXISTS CustomerOrderDetails(
    CustomerOrderID VARCHAR(6),
    ItemCode VARCHAR(6),
    CustomerOrderQTY INT(10),
    cost DOUBLE,
    Status VARCHAR(6),
    CONSTRAINT PRIMARY KEY (CustomerOrderID, ItemCode),
    CONSTRAINT FOREIGN KEY (ItemCode) REFERENCES Item(ItemCode) ON DELETE CASCADE ON UPDATE CASCADE ,
    CONSTRAINT FOREIGN KEY (CustomerOrderID) REFERENCES CustomerOrder(CustomerOrderID) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE CustomerOrderDetails;


#-------------------------------
DROP TABLE IF EXISTS DeliveryOrder ;
CREATE TABLE IF NOT EXISTS DeliveryOrder (
    DeliveryID VARCHAR(6),
    CustomerOrderID VARCHAR(6),
    DriverId VARCHAR(6),
    VehicalId VARCHAR(6),
    DeliveryCharge INT (6),
    Status VARCHAR(6),

    CONSTRAINT PRIMARY KEY (DeliveryID),
    CONSTRAINT FOREIGN KEY (CustomerOrderID) REFERENCES CustomerOrder(CustomerOrderID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (DriverId) REFERENCES Driver(DriverId) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (VehicalId) REFERENCES Vehical(VehicalId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE DeliveryOrder;


#-----------------------------------
DROP TABLE IF EXISTS Login;
CREATE TABLE IF NOT EXISTS Login(
    UserName VARCHAR(10),
    PassWord VARCHAR(10) ,
    CONSTRAINT PRIMARY KEY (UserName)
    );
SHOW TABLES ;
DESCRIBE Login;

INSERT INTO Login VALUES ('Admin','Admin');

#---------------------------
SELECT * FROM Item;
SELECT * FROM Supplier;
SELECT * FROM SupplierOrder;
SELECT * FROM SupplierOrderDetails;
SELECT * FROM Customer;
SELECT * FROM Driver;
SELECT * FROM Vehical;
SELECT * FROM CustomerOrder;
SELECT * FROM CustomerOrderDetails;
SELECT * FROM DeliveryOrder;
SELECT * FROM Login;

#-------------------------------
