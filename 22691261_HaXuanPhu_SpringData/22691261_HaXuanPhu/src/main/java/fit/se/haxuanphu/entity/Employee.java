package fit.se.haxuanphu.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("employee")
public class Employee {
    @Id
    private Integer id;
    private String name;
    private Integer departmentId;
    private double salary;

    public Employee() {
    }
    public Employee(Integer id, String name, Integer departmentId, double salary) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.salary = salary;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", departmentId=" + departmentId + ", salary=" + salary + "]";
    }


}
