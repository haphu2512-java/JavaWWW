package fit.se.haxuanphu_tuan07.service;

import fit.se.haxuanphu_tuan07.entity.Customer;
import fit.se.haxuanphu_tuan07.entity.Order;
import fit.se.haxuanphu_tuan07.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void deleteById(Integer id) {
        orderRepository.deleteById(id);
    }

    public List<Order> findByCustomer(Customer customer) {
        return orderRepository.findByCustomer(customer);
    }

    public List<Order> searchByCustomerName(String customerName) {
        return orderRepository.findByCustomerNameContaining(customerName);
    }
}