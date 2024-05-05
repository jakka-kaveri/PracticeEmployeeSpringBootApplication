package com.kaveri.Employee.Controller;

import com.kaveri.Employee.Entity.Employee;
import com.kaveri.Employee.Exception.EmployeeNotFoundException;
import com.kaveri.Employee.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public List<Employee> displayAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if(!employee.isPresent()){
            throw new EmployeeNotFoundException("Employee with ID : "+id+ " not found");
        }
        return employee.get();
    }
    @GetMapping("search/{name}")
    public List<Employee> getEmployeesByName(@PathVariable String name) throws EmployeeNotFoundException{
        return employeeService.findByName(name);
    }
    @GetMapping("/salary/{salary}")
    public List<Employee> getEmployeesBySalary(@PathVariable double salary) throws EmployeeNotFoundException{
        return employeeService.getBySalary(salary);
    }
    @GetMapping("/salaryrange/{minSalary}/maxSalary")
    public List<Employee> getEmployeesBySalary(@PathVariable double minSalary, @PathVariable double maxSalary) throws EmployeeNotFoundException{
        return employeeService.getBySalaryRange(minSalary,maxSalary);
    }
    @PostMapping("/")
    public Employee saveEmployee(@RequestBody @Validated Employee employee){
        return employeeService.saveEmployee(employee);

    }
    @PutMapping("/")
    public Employee updateEmployee(@RequestBody  Employee employee) throws EmployeeNotFoundException{
        Optional<Employee> employee1 = employeeService.getEmployeeById(employee.getId());
        if(!employee1.isPresent()){
            throw new EmployeeNotFoundException("Employee with ID : "+employee.getId()+ " not found");
        }
        return employeeService.updateEmployee(employee);
    }
    @DeleteMapping("/delete/{id}")
    public String removeEmployeeById(@PathVariable int id) throws EmployeeNotFoundException{
        Optional<Employee> employee1 = employeeService.getEmployeeById(id);
        if(!employee1.isPresent()){
            throw new EmployeeNotFoundException("Employee with ID : "+id+ " not found");
        }
        employeeService.deleteEmployee(id);
        return "Employee ID : "+id+ " deleted successfully";
    }
}
