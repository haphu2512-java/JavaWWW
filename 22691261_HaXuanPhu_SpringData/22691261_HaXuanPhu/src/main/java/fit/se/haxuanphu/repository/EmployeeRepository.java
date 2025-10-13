package fit.se.haxuanphu.repository;

import fit.se.haxuanphu.entity.Employee;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    List<Employee> findAll();
    @Query("SELECT * FROM employee WHERE LOWER(name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Employee> findByNameContaining(@Param("name") String name);

    @Query("SELECT * FROM employee WHERE salary >= :minSalary")
    List<Employee> findBySalaryGreaterThanEqual(@Param("minSalary") Double minSalary);

    @Query("SELECT * FROM employee WHERE salary <= :maxSalary")
    List<Employee> findBySalaryLessThanEqual(@Param("maxSalary") Double maxSalary);

    @Query("SELECT * FROM employee WHERE salary BETWEEN :minSalary AND :maxSalary")
    List<Employee> findBySalaryBetween(@Param("minSalary") Double minSalary,
                                       @Param("maxSalary") Double maxSalary);
    @Query("SELECT * FROM employee WHERE department_id = :departmentId")
    List<Employee> findByDepartmentId(@Param("departmentId") Integer departmentId);
}
