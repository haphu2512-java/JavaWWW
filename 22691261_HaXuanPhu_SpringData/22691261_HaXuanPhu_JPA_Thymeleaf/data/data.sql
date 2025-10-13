

-- Xóa bảng nếu đã tồn tại (để tránh lỗi khi restart)
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;

-- Tạo bảng Department
CREATE TABLE department (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100) NOT NULL
);

-- Tạo bảng Employee
CREATE TABLE employee (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          department_id INT,
                          salary DOUBLE NOT NULL,
                          FOREIGN KEY (department_id) REFERENCES department(id) ON DELETE SET NULL
);


-- Insert sample Departments
INSERT INTO department (name) VALUES ('IT');
INSERT INTO department (name) VALUES ('HR');
INSERT INTO department (name) VALUES ('Sales');
INSERT INTO department (name) VALUES ('Marketing');

-- Insert sample Employees
INSERT INTO employee (name, department_id, salary) VALUES ('Nguyen Van A', 1, 15000000);
INSERT INTO employee (name, department_id, salary) VALUES ('Tran Thi B', 1, 18000000);
INSERT INTO employee (name, department_id, salary) VALUES ('Le Van C', 2, 12000000);
INSERT INTO employee (name, department_id, salary) VALUES ('Pham Thi D', 2, 13000000);
INSERT INTO employee (name, department_id, salary) VALUES ('Hoang Van E', 3, 20000000);
INSERT INTO employee (name, department_id, salary) VALUES ('Vo Thi F', 3, 17000000);
INSERT INTO employee (name, department_id, salary) VALUES ('Do Van G', 4, 16000000);
INSERT INTO employee (name, department_id, salary) VALUES ('Bui Thi H', 4, 14000000);