package fit.se.haxuanphu_jpa.controller;

import fit.se.haxuanphu_jpa.entity.Employee;
import fit.se.haxuanphu_jpa.service.DepartmentService;
import fit.se.haxuanphu_jpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String list(Model model,
                       @RequestParam(required = false) String name,
                       @RequestParam(required = false) Double minSalary,
                       @RequestParam(required = false) Double maxSalary) {
        model.addAttribute("employees", employeeService.searchEmployees(name, minSalary, maxSalary));
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.findAll());
        return "employee/list";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("departments", departmentService.findAll());
        return "employee/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }
}