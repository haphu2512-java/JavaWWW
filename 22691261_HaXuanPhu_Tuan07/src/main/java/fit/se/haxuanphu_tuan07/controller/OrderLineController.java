package fit.se.haxuanphu_tuan07.controller;

import fit.se.haxuanphu_tuan07.entity.OrderLine;
import fit.se.haxuanphu_tuan07.service.OrderLineService;
import fit.se.haxuanphu_tuan07.service.OrderService;
import fit.se.haxuanphu_tuan07.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orderlines")
public class OrderLineController {

    @Autowired
    private OrderLineService orderLineService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("/new/{orderId}")
    public String showCreateForm(@PathVariable Integer orderId, Model model) {
        OrderLine orderLine = new OrderLine();
        orderService.findById(orderId).ifPresent(orderLine::setOrder);
        model.addAttribute("orderLine", orderLine);
        model.addAttribute("products", productService.findAll());
        return "orderline/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        OrderLine orderLine = orderLineService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid orderline Id:" + id));
        model.addAttribute("orderLine", orderLine);
        model.addAttribute("products", productService.findAll());
        return "orderline/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute OrderLine orderLine,
                       @RequestParam Integer orderId,
                       @RequestParam Integer productId) {
        orderService.findById(orderId).ifPresent(orderLine::setOrder);
        productService.findById(productId).ifPresent(product -> {
            orderLine.setProduct(product);
            if (orderLine.getPurchasePrice() == null) {
                orderLine.setPurchasePrice(product.getPrice());
            }
        });
        orderLineService.save(orderLine);
        return "redirect:/orders/view/" + orderId;
    }

    @GetMapping("/delete/{id}/{orderId}")
    public String delete(@PathVariable Integer id, @PathVariable Integer orderId) {
        orderLineService.deleteById(id);
        return "redirect:/orders/view/" + orderId;
    }
}