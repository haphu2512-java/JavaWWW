package fit.se.haxuanphu_tuan07.repository;

import fit.se.haxuanphu_tuan07.entity.Order;
import fit.se.haxuanphu_tuan07.entity.OrderLine;
import fit.se.haxuanphu_tuan07.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findByOrder(Order order);
    List<OrderLine> findByProduct(Product product);
}
