package fit.se.haxuanphu_jpa.repository;

import fit.se.haxuanphu_jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // 1. Tìm kiếm theo tên (không phân biệt hoa thường, chứa chuỗi ký tự)
    List<Employee> findByNameContainingIgnoreCase(String name);

    // 2. Tìm kiếm theo khoảng lương
    List<Employee> findBySalaryBetween(Double minSalary, Double maxSalary);

    // 3. Query nâng cao kết hợp nhiều điều kiện (dùng JPQL)
    @Query("SELECT e FROM Employee e WHERE " +
            "(:name IS NULL OR lower(e.name) LIKE lower(concat('%', :name, '%'))) AND " +
            "(:minSalary IS NULL OR e.salary >= :minSalary) AND " +
            "(:maxSalary IS NULL OR e.salary <= :maxSalary)")
    List<Employee> searchEmployees(
            @Param("name") String name,
            @Param("minSalary") Double minSalary,
            @Param("maxSalary") Double maxSalary);
}