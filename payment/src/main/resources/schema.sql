create table accounts(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(40) NOT NULL UNIQUE,
    amount DOUBLE
);