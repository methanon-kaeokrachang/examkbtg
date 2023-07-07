package tech.kbtg.fullcrudrestapiwithhibernatejpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.kbtg.fullcrudrestapiwithhibernatejpa.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer > {
}
