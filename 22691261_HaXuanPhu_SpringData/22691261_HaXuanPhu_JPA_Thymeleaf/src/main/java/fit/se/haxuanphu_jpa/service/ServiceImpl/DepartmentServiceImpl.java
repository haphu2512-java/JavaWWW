package fit.se.haxuanphu_jpa.service.ServiceImpl;

import fit.se.haxuanphu_jpa.entity.Department;
import fit.se.haxuanphu_jpa.repository.DepartmentRepository;
import fit.se.haxuanphu_jpa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> findById(Integer id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void deleteById(Integer id) {
        departmentRepository.deleteById(id);
    }
}