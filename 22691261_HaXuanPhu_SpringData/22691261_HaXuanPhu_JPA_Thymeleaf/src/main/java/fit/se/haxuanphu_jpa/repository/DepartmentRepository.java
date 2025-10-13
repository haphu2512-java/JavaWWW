package fit.se.haxuanphu_jpa.repository;

import fit.se.haxuanphu_jpa.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}