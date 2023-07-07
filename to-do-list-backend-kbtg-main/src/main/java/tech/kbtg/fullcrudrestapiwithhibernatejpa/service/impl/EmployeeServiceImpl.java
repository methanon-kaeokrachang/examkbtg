package tech.kbtg.fullcrudrestapiwithhibernatejpa.service.impl;

import org.springframework.stereotype.Service;
import tech.kbtg.fullcrudrestapiwithhibernatejpa.entity.Employee;
import tech.kbtg.fullcrudrestapiwithhibernatejpa.exception.NotFoundResourceException;
import tech.kbtg.fullcrudrestapiwithhibernatejpa.repository.EmployeeRepository;
import tech.kbtg.fullcrudrestapiwithhibernatejpa.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public List<Employee> getAllEmployee()
    {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int id){
        Optional<Employee> employee = employeeRepository.findById( id );
        if ( employee.isPresent() )
        {
            return employee.get();
        }
        throw new NotFoundResourceException( "employee not found ID: " + id );
    }
    @Override
    public Employee createNewEmployee(Employee employee )
    {
        return employeeRepository.save( employee );
    }

    @Override
    public void deleteEmployeeById( Integer id )
    {
        Optional < Employee > employee = employeeRepository.findById( id );
        if ( employee.isEmpty() )
        {
            throw new NotFoundResourceException( "not found ID: " + id );
        }
        employeeRepository.delete( employee.get() );
    }

    @Override
    public Employee updateEmployee( Employee employee )
    {
        return employeeRepository.save( employee );
    }

}
