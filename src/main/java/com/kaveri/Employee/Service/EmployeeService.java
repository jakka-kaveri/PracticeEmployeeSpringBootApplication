package com.kaveri.Employee.Service;

import com.kaveri.Employee.Entity.Employee;
import com.kaveri.Employee.Exception.EmployeeNotFoundException;
import com.kaveri.Employee.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }
    public List<Employee> findByName(String name){
        List<Employee> employees = employeeRepo.findByName(name);
        if(employees==null){
            throw new EmployeeNotFoundException("No results found");
        }
        return employees;
    }
    public List<Employee> getBySalary(double salary){
        List<Employee> employees = employeeRepo.findBySalary(salary);
        if(employees==null){
            throw new EmployeeNotFoundException("No results found");
        }
        return employees;
    }
    public List<Employee> getBySalaryRange(double minSalary , double maxSalary){
        List<Employee> employees = employeeRepo.FindBySalaryRange(minSalary,maxSalary);
        if(employees==null){
            throw new EmployeeNotFoundException("No results found");
        }
        return employees;
    }
    public  List<Employee> getByDesignationAndSalary(String designation , double salary){
        List<Employee> employees = employeeRepo.findByDesignationAndSalary(designation,salary);
        if(employees==null){
            throw new EmployeeNotFoundException("No results found");
        }
        return employees;
    }

    public Optional<Employee> getEmployeeById(int id){
        return employeeRepo.findById(id);
    }

    public Employee saveEmployee(Employee employee){
        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(Employee employee){
        Employee e =employee;
        e.setName(employee.getName());
        e.setDesignation(employee.getDesignation());
        e.setSalary(employee.getSalary());
        return e;
    }
    public void deleteEmployee(int id){
        Optional<Employee> employee = employeeRepo.findById(id);
        if(!employee.isPresent()){
            throw new RuntimeException("Employee with Id : "+id+" not found");
        }
         employeeRepo.deleteById(id);

    }

}
