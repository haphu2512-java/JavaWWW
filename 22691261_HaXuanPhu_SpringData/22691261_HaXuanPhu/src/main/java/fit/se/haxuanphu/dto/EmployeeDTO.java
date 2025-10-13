package fit.se.haxuanphu.dto;

import fit.se.haxuanphu.entity.Department;


public class EmployeeDTO {

    private Integer id;
    private String name;
    private Double salary;
    private Department department;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Integer id, String name, Double salary, Department department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }
}