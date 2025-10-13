package fit.se.haxuanphu_mongodb.service;

import fit.se.haxuanphu_mongodb.entity.Department;
import fit.se.haxuanphu_mongodb.entity.Employee;
import fit.se.haxuanphu_mongodb.repository.DepartmentRepository;
import fit.se.haxuanphu_mongodb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // CRUD
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(String id) {
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(String id, Employee employeeDetails) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            Employee existingEmployee = employee.get();
            existingEmployee.setName(employeeDetails.getName());
            existingEmployee.setAge(employeeDetails.getAge());
            existingEmployee.setSalary(employeeDetails.getSalary());
            existingEmployee.setEmail(employeeDetails.getEmail());
            existingEmployee.setPosition(employeeDetails.getPosition());
            existingEmployee.setDepartment(employeeDetails.getDepartment());
            return employeeRepository.save(existingEmployee);
        }
        return null;
    }

    public boolean deleteEmployee(String id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Quan hệ 1-n: Lấy tất cả nhân viên theo phòng ban
    public List<Employee> getEmployeesByDepartmentId(String departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

    public List<Employee> getEmployeesByDepartment(Department department) {
        return employeeRepository.findByDepartment(department);
    }

    // Tìm kiếm theo tên
    public List<Employee> searchEmployeesByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    // Tìm kiếm theo tuổi
    public List<Employee> searchEmployeesByAge(int age) {
        return employeeRepository.findByAge(age);
    }

    public List<Employee> searchEmployeesByAgeRange(int minAge, int maxAge) {
        return employeeRepository.findByAgeBetween(minAge, maxAge);
    }

    // Tìm kiếm theo lương
    public List<Employee> searchEmployeesBySalaryGreaterThan(double salary) {
        return employeeRepository.findBySalaryGreaterThanEqual(salary);
    }

    public List<Employee> searchEmployeesBySalaryLessThan(double salary) {
        return employeeRepository.findBySalaryLessThanEqual(salary);
    }

    public List<Employee> searchEmployeesBySalaryRange(double minSalary, double maxSalary) {
        return employeeRepository.findBySalaryBetween(minSalary, maxSalary);
    }

    // Gán phòng ban cho nhân viên
    public Employee assignDepartment(String employeeId, String departmentId) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
        Optional<Department> departmentOpt = departmentRepository.findById(departmentId);

        if (employeeOpt.isPresent() && departmentOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            employee.setDepartment(departmentOpt.get());
            return employeeRepository.save(employee);
        }
        return null;
    }
}