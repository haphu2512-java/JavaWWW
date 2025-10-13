package fit.se.haxuanphu_jpa.service;

import fit.se.haxuanphu_jpa.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAll();
    Optional<Employee> findById(Integer id);
    Employee save(Employee employee);
    void deleteById(Integer id);
    List<Employee> searchEmployees(String name, Double minSalary, Double maxSalary);
}