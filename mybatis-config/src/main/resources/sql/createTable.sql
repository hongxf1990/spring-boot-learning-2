CREATE TABLE demo (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(20) DEFAULT NULL,
  telephone_number VARCHAR(11) DEFAULT NULL
);

CREATE TABLE demo2 (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(20) DEFAULT NULL,
  email VARCHAR(50) DEFAULT NULL,
  update_time DATETIME,
  sex_enum ENUM('MAN', 'WOMAN') DEFAULT NULL
)