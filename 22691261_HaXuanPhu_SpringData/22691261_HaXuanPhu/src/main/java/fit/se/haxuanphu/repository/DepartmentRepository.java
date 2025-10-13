package fit.se.haxuanphu.repository;

import fit.se.haxuanphu.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {
    Optional<Department> findByName(String name);
    List<Department> findAll();

}
