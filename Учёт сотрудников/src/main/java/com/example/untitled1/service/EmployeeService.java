package com.example.untitled1.service;

import com.example.untitled1.model.Employee;
import com.example.untitled1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List<Employee> search(String name, Boolean active) {
        boolean hasName = name != null && !name.isBlank();
        if (hasName && active != null)
            return repository.findByFullNameContainingIgnoreCaseAndActive(name, active);
        if (hasName)
            return repository.findByFullNameContainingIgnoreCase(name);
        if (active != null)
            return repository.findByActive(active);
        return repository.findAll();
    }

    public Employee getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Сотрудник не найден"));
    }

    public boolean emailExists(String email) {
        return repository.existsByEmail(email);
    }

    public void save(Employee employee) {
        repository.save(employee);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}