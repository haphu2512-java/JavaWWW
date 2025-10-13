package fit.se.haxuanphu.service;

import fit.se.haxuanphu.dto.EmployeeDTO;
import fit.se.haxuanphu.entity.Department;
import fit.se.haxuanphu.repository.DepartmentRepository;
import fit.se.haxuanphu.repository.EmployeeRepository;
import fit.se.haxuanphu.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        public Optional<Employee> getEmployeeById(Integer id) {
            return employeeRepository.findById(id);
        }
        public Employee updateEmployee(Integer id, Employee employeeDetails) {
            Optional<Employee> employee = employeeRepository.findById(id);
            if (employee.isPresent()) {
                Employee emp = employee.get();
                emp.setName(employeeDetails.getName());
                emp.setDepartmentId(employeeDetails.getDepartmentId());
                emp.setSalary(employeeDetails.getSalary());
                return employeeRepository.save(emp);
            }
            return null;
        }
        public boolean deleteEmployee(Integer id) {
            if (employeeRepository.existsById(id)) {
                employeeRepository.deleteById(id);
                return true;
            }
            return false;
        }


        // Lấy Employee với thông tin Department
        public EmployeeDTO getEmployeeWithDepartment(Integer id) {
            Optional<Employee> empOpt = employeeRepository.findById(id);
            if (empOpt.isPresent()) {
                Employee emp = empOpt.get();
                Department dept = departmentRepository.findById(emp.getDepartmentId()).orElse(null);
                return new EmployeeDTO(emp.getId(), emp.getName(), emp.getSalary(), dept);
            }
            return null;
        }

        public List<EmployeeDTO> getAllEmployeesWithDepartment() {
            List<Employee> employees = employeeRepository.findAll();
            return employees.stream()
                    .map(emp -> {
                        Department dept = departmentRepository.findById(emp.getDepartmentId()).orElse(null);
                        return new EmployeeDTO(emp.getId(), emp.getName(), emp.getSalary(), dept);
                    })
                    .collect(Collectors.toList());
        }

        public List<EmployeeDTO> getEmployeesByDepartment(Integer departmentId) {
            List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);
            Department dept = departmentRepository.findById(departmentId).orElse(null);

            return employees.stream()
                    .map(emp -> new EmployeeDTO(emp.getId(), emp.getName(), emp.getSalary(), dept))
                    .collect(Collectors.toList());
        }


        // Tìm kiếm theo tên
        public List<EmployeeDTO> searchByName(String name) {
            List<Employee> employees = employeeRepository.findByNameContaining(name);
            return employees.stream()
                    .map(emp -> {
                        Department dept = departmentRepository.findById(emp.getDepartmentId()).orElse(null);
                        return new EmployeeDTO(emp.getId(), emp.getName(), emp.getSalary(), dept);
                    })
                    .collect(Collectors.toList());
        }

        // Tìm kiếm theo lương (tối thiểu)
        public List<EmployeeDTO> searchBySalaryMin(Double minSalary) {
            List<Employee> employees = employeeRepository.findBySalaryGreaterThanEqual(minSalary);
            return employees.stream()
                    .map(emp -> {
                        Department dept = departmentRepository.findById(emp.getDepartmentId()).orElse(null);
                        return new EmployeeDTO(emp.getId(), emp.getName(), emp.getSalary(), dept);
                    })
                    .collect(Collectors.toList());
        }

        // Tìm kiếm theo khoảng lương
        public List<EmployeeDTO> searchBySalaryRange(Double minSalary, Double maxSalary) {
            List<Employee> employees = employeeRepository.findBySalaryBetween(minSalary, maxSalary);
            return employees.stream()
                    .map(emp -> {
                        Department dept = departmentRepository.findById(emp.getDepartmentId()).orElse(null);
                        return new EmployeeDTO(emp.getId(), emp.getName(), emp.getSalary(), dept);
                    })
                    .collect(Collectors.toList());
        }
}
