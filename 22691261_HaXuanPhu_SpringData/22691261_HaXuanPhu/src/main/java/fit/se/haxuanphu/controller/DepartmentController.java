package fit.se.haxuanphu.controller;

import fit.se.haxuanphu.entity.Department;
import fit.se.haxuanphu.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // Create Department
    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department created = departmentService.createDepartment(department);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Get All Departments
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    // Get Department by ID
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Integer id) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        return department.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update Department
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Integer id,
                                                       @RequestBody Department department) {
        Department updated = departmentService.updateDepartment(id, department);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete Department
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Integer id) {
        boolean deleted = departmentService.deleteDepartment(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}