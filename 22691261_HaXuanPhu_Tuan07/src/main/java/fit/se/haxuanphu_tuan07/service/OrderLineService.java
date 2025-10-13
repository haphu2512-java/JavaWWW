package fit.se.haxuanphu_tuan07.service;


import fit.se.haxuanphu_tuan07.entity.Order;
import fit.se.haxuanphu_tuan07.entity.OrderLine;
import fit.se.haxuanphu_tuan07.repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderLineService {

    @Autowired
    private OrderLineRepository orderLineRepository;

    public List<OrderLine> findAll() {
        return orderLineRepository.findAll();
    }

    public Optional<OrderLine> findById(Integer id) {
        return orderLineRepository.findById(id);
    }

    public OrderLine save(OrderLine orderLine) {
        return orderLineRepository.save(orderLine);
    }

    public void deleteById(Integer id) {
        orderLineRepository.deleteById(id);
    }

    public List<OrderLine> findByOrder(Order order) {
        return orderLineRepository.findByOrder(order);
    }
}