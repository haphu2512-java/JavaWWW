package fit.se.haxuanphu.service;

import fit.se.haxuanphu.entity.Department;
import fit.se.haxuanphu.repository.DepartmentRepository;
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
    public Optional<Department> getDepartmentById(Integer id) {
        return departmentRepository.findById(id);
    }
    public Department updateDepartment(Integer id, Department departmentDetails) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            Department dept = department.get();
            dept.setName(departmentDetails.getName());
            return departmentRepository.save(dept);
        }
        return null;
    }
    public boolean deleteDepartment(Integer id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
