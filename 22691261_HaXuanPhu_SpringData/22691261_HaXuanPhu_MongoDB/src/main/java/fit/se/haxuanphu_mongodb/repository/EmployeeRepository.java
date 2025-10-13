package fit.se.haxuanphu_mongodb.repository;

import fit.se.haxuanphu_mongodb.entity.Department;
import fit.se.haxuanphu_mongodb.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    // Tìm kiếm theo tên (chứa chuỗi, không phân biệt hoa thường)
    List<Employee> findByNameContainingIgnoreCase(String name);

    // Tìm kiếm theo tuổi
    List<Employee> findByAge(int age);

    // Tìm kiếm theo khoảng tuổi
    List<Employee> findByAgeBetween(int minAge, int maxAge);

    // Tìm kiếm theo lương
    List<Employee> findBySalaryGreaterThanEqual(double salary);

    List<Employee> findBySalaryLessThanEqual(double salary);

    List<Employee> findBySalaryBetween(double minSalary, double maxSalary);

    // Tìm tất cả nhân viên theo Department (quan hệ 1-n)
    List<Employee> findByDepartment(Department department);

    List<Employee> findByDepartmentId(String departmentId);

    // Query tùy chỉnh
    @Query("{ 'name': { $regex: ?0, $options: 'i' }, 'age': { $gte: ?1, $lte: ?2 } }")
    List<Employee> findByNameAndAgeRange(String name, int minAge, int maxAge);

    @Query("{ 'salary': { $gte: ?0 }, 'department.$id': ?1 }")
    List<Employee> findBySalaryAndDepartment(double minSalary, String departmentId);
}