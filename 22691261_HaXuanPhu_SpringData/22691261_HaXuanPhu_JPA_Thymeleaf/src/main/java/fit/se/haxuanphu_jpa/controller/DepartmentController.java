package fit.se.haxuanphu_jpa.controller;

import fit.se.haxuanphu_jpa.entity.Department;
import fit.se.haxuanphu_jpa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("department", new Department());
        return "department/list";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("department") Department department) {
        departmentService.save(department);
        return "redirect:/departments";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Department department = departmentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid department Id:" + id));
        model.addAttribute("department", department);
        model.addAttribute("departments", departmentService.findAll());
        return "department/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        departmentService.deleteById(id);
        return "redirect:/departments";
    }

    @GetMapping("/{id}")
    public String viewDepartment(@PathVariable("id") Integer id, Model model) {
        Department department = departmentService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid department Id:" + id));
        model.addAttribute("department", department);
        return "department/detail";
    }
}