package fit.se.haxuanphu_jpa.service.ServiceImpl;

import fit.se.haxuanphu_jpa.entity.Employee;
import fit.se.haxuanphu_jpa.repository.EmployeeRepository;
import fit.se.haxuanphu_jpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> searchEmployees(String name, Double minSalary, Double maxSalary) {
        // Gọi thẳng đến phương thức query nâng cao trong repository
        return employeeRepository.searchEmployees(name, minSalary, maxSalary);
    }
}