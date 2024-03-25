create table book(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    title VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    description VARCHAR(200) NOT NULL,
    quantity INT
    price DOUBLE NOT NULL
);