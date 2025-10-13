package fit.se.haxuanphu_tuan07.repository;

import fit.se.haxuanphu_tuan07.entity.Comment;
import fit.se.haxuanphu_tuan07.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByProduct(Product product);
}