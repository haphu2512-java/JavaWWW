package fit.se.haxuanphu_springai.repository;


import fit.se.haxuanphu_springai.entity.Order;
import fit.se.haxuanphu_springai.entity.OrderLine;
import fit.se.haxuanphu_springai.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findByOrder(Order order);
    List<OrderLine> findByProduct(Product product);
}
