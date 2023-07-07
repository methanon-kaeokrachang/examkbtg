package tech.kbtg.fullcrudrestapiwithhibernatejpa.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.kbtg.fullcrudrestapiwithhibernatejpa.entity.Employee;
import tech.kbtg.fullcrudrestapiwithhibernatejpa.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllEmployee() {
        List<Employee> employees= employeeService.getAllEmployee();

        if(employees.size()==0){
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( employees );
        }
        return ResponseEntity.status( HttpStatus.OK ).body( employees );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable(name = "id") int empId){
        Employee employee= employeeService.getEmployeeById(empId);
        if(employee==null){
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( employee );
        }
        return ResponseEntity.status( HttpStatus.OK ).body( employee );
    }

    @PostMapping("/")
    public ResponseEntity<?> createNewEmployee(@RequestBody Employee employee ) {
        Employee newEmployee = employeeService.createNewEmployee( employee );
        newEmployee.setStatus("current");
        return ResponseEntity.status( HttpStatus.OK ).body( newEmployee );
    }


    @DeleteMapping("/{id}") //http://localhost:8082/api/employees/3
    public ResponseEntity<?> deleteEmployeeById(@PathVariable(name = "id") int empId) {
        //employeeService.deleteEmployeeById( empId );
        Employee employee= employeeService.getEmployeeById(empId);
        employee.setStatus("deleted");
        employeeService.updateEmployee(employee);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID:" + empId +"has been deleted.");
    }
    @PutMapping("/{id}") //http://localhost:8082/api/employees/3
    public ResponseEntity<?> updateEmployee(@PathVariable(name = "id") int empId, @RequestBody Employee employee) {

        Employee employeeToUpdate= employeeService.getEmployeeById(empId);

        employeeToUpdate.setFirst_name(employee.getFirst_name());
        employeeToUpdate.setLast_name(employee.getLast_name());
        employeeToUpdate.setNickname(employee.getNickname());
        employeeToUpdate.setAddress(employee.getAddress());

        employeeService.updateEmployee( employeeToUpdate );

        return ResponseEntity.status( HttpStatus.OK ).body( employeeToUpdate );
    }
    @PutMapping("/salary/{id}") //http://localhost:8082/api/employees/salary/4 ::::; {"salary":50}
    public ResponseEntity<?> updateSalaryEmployee(@PathVariable(name = "id") int empId, @RequestBody Employee employee) {

        Employee employeeToUpdateSalary= employeeService.getEmployeeById(empId);

        //current salary
        String currentSalary = employeeToUpdateSalary.getSalary();
        float current_Salary =Float.parseFloat(currentSalary);


        // new salary
        String newSalary = employee.getSalary();

        //convert to float
        float new_Salary =Float.parseFloat(newSalary);

        //check
        if(new_Salary>100 || new_Salary<0){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Can not update salary to :" + new_Salary +"%");
        }

        double upDateSalary = current_Salary+current_Salary*(new_Salary/100.0);
        String upDate_Salary = Double.toString(upDateSalary);

        employeeToUpdateSalary.setSalary(upDate_Salary);
        Employee updateEmployee = employeeService.updateEmployee( employeeToUpdateSalary );

        return ResponseEntity.status( HttpStatus.OK ).body( updateEmployee );
    }

    @PutMapping("/position/{id}/{old}/{new}") //http://localhost:8082/api/employees/position/4/se/sa
    public ResponseEntity<?> updatePositionEmployee(@PathVariable(name = "id") int empId,@PathVariable(name = "old") String oldPos,@PathVariable(name = "new") String newPos) {

        Employee employeeToUpdatePosition= employeeService.getEmployeeById(empId);

        String oldPosition = oldPos;
        String newPosition = newPos;

        if(!oldPosition.equals(employeeToUpdatePosition.getPosition())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Current position is incorrect");
        }

        employeeToUpdatePosition.setPosition(newPosition);
        Employee updateEmployee = employeeService.updateEmployee( employeeToUpdatePosition );

        return ResponseEntity.status( HttpStatus.OK ).body( updateEmployee );
    }
    @GetMapping("/name/{names}")
    public ResponseEntity<?> getAllEmployeeByName(@PathVariable(name = "names") String name) {
        //return employeeService.getAllEmployee();
        List<Employee> employeesContainGivingName = new ArrayList<>();
        List<Employee> employees= employeeService.getAllEmployee();

        for( Employee employee : employees){
            if(employee.getFirst_name().contains(name)){
                employeesContainGivingName.add(employee);
            }
        }
        if(employeesContainGivingName.size()==0){
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( employeesContainGivingName );
        }
        return ResponseEntity.status( HttpStatus.OK ).body( employeesContainGivingName );
    }

    @DeleteMapping("/") //http://localhost:8082/api/employees/?id=1&id=2&id=3
    public ResponseEntity<?> deleteMultiEmployeeById(@RequestParam("id") List<Integer> ids) {
        //System.out.println(ids);
        boolean isSuccessAll = true;
        List<Integer> notFoundId = new ArrayList<>();
            for (Integer id : ids) {
                try{employeeService.deleteEmployeeById(id);}
                catch (Exception e){notFoundId.add(id); isSuccessAll=false;}
            }
        if(isSuccessAll){return ResponseEntity.status( HttpStatus.OK ).body( "Delete All Success" );}
        return ResponseEntity.status(HttpStatus.MULTI_STATUS).body("not_found_ids: "+notFoundId);
    }
}