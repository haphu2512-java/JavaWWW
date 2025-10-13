package fit.se.haxuanphu_jpa.service;

import fit.se.haxuanphu_jpa.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> findAll();
    Optional<Department> findById(Integer id);
    Department save(Department department);
    void deleteById(Integer id);
}