package fit.se.haxuanphu.controller;

import fit.se.haxuanphu.dto.EmployeeDTO;
import fit.se.haxuanphu.entity.Employee;
import fit.se.haxuanphu.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // CRUD
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee created = employeeService.createEmployee(employee);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Get All Employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // Get Employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update Employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id,
                                                   @RequestBody Employee employee) {
        Employee updated = employeeService.updateEmployee(id, employee);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        boolean deleted = employeeService.deleteEmployee(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // ============ Hiển thị với Department (1-n) ============

    // Get Employee với Department
    @GetMapping("/{id}/with-department")
    public ResponseEntity<EmployeeDTO> getEmployeeWithDepartment(@PathVariable Integer id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeWithDepartment(id);
        if (employeeDTO != null) {
            return ResponseEntity.ok(employeeDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // Get All Employees với Department
    @GetMapping("/with-department")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeesWithDepartment() {
        List<EmployeeDTO> employees = employeeService.getAllEmployeesWithDepartment();
        return ResponseEntity.ok(employees);
    }

    // Get Employees by Department ID
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByDepartment(
            @PathVariable Integer departmentId) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByDepartment(departmentId);
        return ResponseEntity.ok(employees);
    }

    // ============ Tìm kiếm ============

    // Tìm kiếm theo tên
    @GetMapping("/search/name")
    public ResponseEntity<List<EmployeeDTO>> searchByName(@RequestParam String name) {
        List<EmployeeDTO> employees = employeeService.searchByName(name);
        return ResponseEntity.ok(employees);
    }

    // Tìm kiếm theo lương tối thiểu
    @GetMapping("/search/salary/min")
    public ResponseEntity<List<EmployeeDTO>> searchBySalaryMin(
            @RequestParam Double minSalary) {
        List<EmployeeDTO> employees = employeeService.searchBySalaryMin(minSalary);
        return ResponseEntity.ok(employees);
    }

    // Tìm kiếm theo khoảng lương
    @GetMapping("/search/salary/range")
    public ResponseEntity<List<EmployeeDTO>> searchBySalaryRange(
            @RequestParam Double minSalary,
            @RequestParam Double maxSalary) {
        List<EmployeeDTO> employees = employeeService.searchBySalaryRange(minSalary, maxSalary);
        return ResponseEntity.ok(employees);
    }
}