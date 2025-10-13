package fit.se.haxuanphu_mongodb.repository;

import fit.se.haxuanphu_mongodb.entity.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, String> {

    // Tìm kiếm theo tên
    Optional<Department> findByName(String name);

    List<Department> findByNameContainingIgnoreCase(String name);

    // Tìm kiếm theo địa điểm
    List<Department> findByLocation(String location);
}