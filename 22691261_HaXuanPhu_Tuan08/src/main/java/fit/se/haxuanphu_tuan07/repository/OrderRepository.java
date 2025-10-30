package fit.se.haxuanphu_tuan07.repository;

import fit.se.haxuanphu_tuan07.entity.Customer;
import fit.se.haxuanphu_tuan07.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomer(Customer customer);

    @Query("SELECT o FROM Order o WHERE o.customer.name LIKE %:customerName%")
    List<Order> findByCustomerNameContaining(@Param("customerName") String customerName);
}
