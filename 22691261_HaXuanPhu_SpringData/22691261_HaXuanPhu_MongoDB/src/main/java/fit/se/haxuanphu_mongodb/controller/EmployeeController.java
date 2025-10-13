package fit.se.haxuanphu_mongodb.controller;

import fit.se.haxuanphu_mongodb.entity.Employee;
import fit.se.haxuanphu_mongodb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // CRUD Endpoints
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee created = employeeService.createEmployee(employee);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id,
                                                   @RequestBody Employee employee) {
        Employee updated = employeeService.updateEmployee(id, employee);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        if (employeeService.deleteEmployee(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Quan hệ 1-n: Lấy nhân viên theo phòng ban
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable String departmentId) {
        List<Employee> employees = employeeService.getEmployeesByDepartmentId(departmentId);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Gán phòng ban cho nhân viên
    @PutMapping("/{employeeId}/department/{departmentId}")
    public ResponseEntity<Employee> assignDepartment(@PathVariable String employeeId,
                                                     @PathVariable String departmentId) {
        Employee updated = employeeService.assignDepartment(employeeId, departmentId);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Tìm kiếm theo tên
    @GetMapping("/search/name")
    public ResponseEntity<List<Employee>> searchByName(@RequestParam String name) {
        List<Employee> employees = employeeService.searchEmployeesByName(name);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Tìm kiếm theo tuổi
    @GetMapping("/search/age")
    public ResponseEntity<List<Employee>> searchByAge(@RequestParam int age) {
        List<Employee> employees = employeeService.searchEmployeesByAge(age);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/search/age-range")
    public ResponseEntity<List<Employee>> searchByAgeRange(@RequestParam int minAge,
                                                           @RequestParam int maxAge) {
        List<Employee> employees = employeeService.searchEmployeesByAgeRange(minAge, maxAge);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Tìm kiếm theo lương
    @GetMapping("/search/salary-greater")
    public ResponseEntity<List<Employee>> searchBySalaryGreater(@RequestParam double salary) {
        List<Employee> employees = employeeService.searchEmployeesBySalaryGreaterThan(salary);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/search/salary-less")
    public ResponseEntity<List<Employee>> searchBySalaryLess(@RequestParam double salary) {
        List<Employee> employees = employeeService.searchEmployeesBySalaryLessThan(salary);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/search/salary-range")
    public ResponseEntity<List<Employee>> searchBySalaryRange(@RequestParam double minSalary,
                                                              @RequestParam double maxSalary) {
        List<Employee> employees = employeeService.searchEmployeesBySalaryRange(minSalary, maxSalary);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}