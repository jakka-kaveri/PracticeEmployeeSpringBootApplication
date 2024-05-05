package com.kaveri.Employee.Repository;

import com.kaveri.Employee.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
    @Query(value = "SELECT e FROM Employee e WHERE e.name LIKE CONCAT('%',:name ,'%')")
    List<Employee> findByName( String name);
    @Query(value = "SELECT e FROM Employee e WHERE e.salary=:salary")
    List<Employee> findBySalary(double salary);

    @Query(value = "SELECT e FROM Employee e WHERE e.salary>=:minSalary AND e.salary<=:maxSalary")
    List<Employee> FindBySalaryRange(double minSalary , double maxSalary);
}
