package fit.se.haxuanphu_mongodb.service;


import fit.se.haxuanphu_mongodb.entity.Department;
import fit.se.haxuanphu_mongodb.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // CRUD
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(String id) {
        return departmentRepository.findById(id);
    }

    public Department updateDepartment(String id, Department departmentDetails) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            Department existingDepartment = department.get();
            existingDepartment.setName(departmentDetails.getName());
            existingDepartment.setLocation(departmentDetails.getLocation());
            existingDepartment.setDescription(departmentDetails.getDescription());
            return departmentRepository.save(existingDepartment);
        }
        return null;
    }

    public boolean deleteDepartment(String id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Tìm kiếm theo tên
    public List<Department> searchDepartmentsByName(String name) {
        return departmentRepository.findByNameContainingIgnoreCase(name);
    }

    public Optional<Department> getDepartmentByName(String name) {
        return departmentRepository.findByName(name);
    }

    // Tìm kiếm theo địa điểm
    public List<Department> searchDepartmentsByLocation(String location) {
        return departmentRepository.findByLocation(location);
    }
}