package tech.kbtg.fullcrudrestapiwithhibernatejpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column ( name = "id" )
    private int id;

    @Column ( name = "first_name" )
    private String first_name;

    @Column ( name = "last_name" )
    private String last_name;

    @Column ( name = "nickname" )
    private String nickname;

    @Column ( name = "salary" )
    private String salary;

    @Column ( name = "status" )
    private String status;

    @Column ( name = "address" )
    private String address;

    @Column ( name = "position" )
    private String position;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Employee() {
    }

    public Employee(String first_name, String last_name, String nickname, String salary, String status, String address, String position) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.nickname = nickname;
        this.salary = salary;
        this.status = status;
        this.address = address;
        this.position = position;
    }
}

