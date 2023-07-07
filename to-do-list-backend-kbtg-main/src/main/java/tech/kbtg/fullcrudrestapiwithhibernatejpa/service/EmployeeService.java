package tech.kbtg.fullcrudrestapiwithhibernatejpa.service;

import tech.kbtg.fullcrudrestapiwithhibernatejpa.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployee();

    Employee getEmployeeById(int id);

    void deleteEmployeeById( Integer id );



    Employee createNewEmployee( Employee employee );

    Employee updateEmployee( Employee updatedEmployee );
}
