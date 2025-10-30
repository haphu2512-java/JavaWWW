package fit.se.haxuanphu_tuan07.repository;

import fit.se.haxuanphu_tuan07.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByNameContainingIgnoreCase(String name);
}
