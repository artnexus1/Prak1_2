package com.example.untitled1.controller;

import com.example.untitled1.model.Employee;
import com.example.untitled1.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public String list(@RequestParam(required = false) String name,
                       @RequestParam(required = false) Boolean active,
                       Model model) {
        model.addAttribute("employees", service.search(name, active));
        model.addAttribute("name", name);
        model.addAttribute("active", active);
        return "employees-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("employee") Employee employee,
                      BindingResult result) {
        if (service.emailExists(employee.getEmail()))
            result.rejectValue("email", "duplicate", "Такой email уже есть");

        if (result.hasErrors()) return "employee-form";

        service.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", service.getById(id));
        return "employee-form";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       @Valid @ModelAttribute("employee") Employee employee,
                       BindingResult result) {
        if (result.hasErrors()) {
            employee.setId(id);
            return "employee-form";
        }
        employee.setId(id);
        service.save(employee);
        return "redirect:/employees";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/employees";
    }
}