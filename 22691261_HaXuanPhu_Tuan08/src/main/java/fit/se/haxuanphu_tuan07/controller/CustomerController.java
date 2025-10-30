package fit.se.haxuanphu_tuan07.controller;


import fit.se.haxuanphu_tuan07.entity.Customer;
import fit.se.haxuanphu_tuan07.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String list(@RequestParam(required = false) String search, Model model) {
        List<Customer> customers;
        if (search != null && !search.isEmpty()) {
            customers = customerService.searchByName(search);
        } else {
            customers = customerService.findAll();
        }
        model.addAttribute("customers", customers);
        model.addAttribute("search", search);
        return "customer/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Customer customer = customerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        model.addAttribute("customer", customer);
        return "customer/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Customer customer) {
        if (customer.getCustomerSince() == null) {
            customer.setCustomerSince(new Date());
        }
        customerService.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        customerService.deleteById(id);
        return "redirect:/customers";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Integer id, Model model) {
        Customer customer = customerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        model.addAttribute("customer", customer);
        return "customer/view";
    }
}