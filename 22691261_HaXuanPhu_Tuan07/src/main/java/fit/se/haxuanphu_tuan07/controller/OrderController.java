package fit.se.haxuanphu_tuan07.controller;


import fit.se.haxuanphu_tuan07.entity.Order;
import fit.se.haxuanphu_tuan07.entity.OrderLine;
import fit.se.haxuanphu_tuan07.service.CustomerService;
import fit.se.haxuanphu_tuan07.service.OrderLineService;
import fit.se.haxuanphu_tuan07.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderLineService orderLineService;

    @GetMapping
    public String list(@RequestParam(required = false) String search, Model model) {
        List<Order> orders;
        if (search != null && !search.isEmpty()) {
            orders = orderService.searchByCustomerName(search);
        } else {
            orders = orderService.findAll();
        }
        model.addAttribute("orders", orders);
        model.addAttribute("search", search);
        return "order/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("customers", customerService.findAll());
        return "order/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Order order = orderService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        model.addAttribute("order", order);
        model.addAttribute("customers", customerService.findAll());
        return "order/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Order order, @RequestParam Integer customerId) {
        customerService.findById(customerId).ifPresent(order::setCustomer);
        if (order.getDate() == null) {
            order.setDate(new Date());
        }
        orderService.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        orderService.deleteById(id);
        return "redirect:/orders";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Integer id, Model model) {
        Order order = orderService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        List<OrderLine> orderLines = orderLineService.findByOrder(order);
        model.addAttribute("order", order);
        model.addAttribute("orderLines", orderLines);
        return "order/view";

    }
}