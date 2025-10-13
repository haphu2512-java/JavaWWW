package fit.se.haxuanphu_mongodb.controller;

import fit.se.haxuanphu_mongodb.entity.Department;
import fit.se.haxuanphu_mongodb.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "*")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // CRUD Endpoints
    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department created = departmentService.createDepartment(department);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable String id) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        return department.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable String id,
                                                       @RequestBody Department department) {
        Department updated = departmentService.updateDepartment(id, department);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable String id) {
        if (departmentService.deleteDepartment(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Tìm kiếm
    @GetMapping("/search/name")
    public ResponseEntity<List<Department>> searchByName(@RequestParam String name) {
        List<Department> departments = departmentService.searchDepartmentsByName(name);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/search/location")
    public ResponseEntity<List<Department>> searchByLocation(@RequestParam String location) {
        List<Department> departments = departmentService.searchDepartmentsByLocation(location);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
}