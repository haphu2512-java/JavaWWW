package fit.se.haxuanphu_tuan07.repository;

import fit.se.haxuanphu_tuan07.entity.Category;
import fit.se.haxuanphu_tuan07.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCategory(Category category);
}
