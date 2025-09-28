package fit.se.haxuanphu_tuan5.dao;

import fit.se.haxuanphu_tuan5.model.Department;
import fit.se.haxuanphu_tuan5.model.Employee;
import fit.se.haxuanphu_tuan5.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private DBUtil dbUtil;

    public EmployeeDAO(DataSource ds) {
        dbUtil = new DBUtil(ds);
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = null;
        String sql = "select * from employees";
        try (Connection conn = dbUtil.getConnection();
             Statement ps = conn.createStatement();
             ResultSet rs = ps.executeQuery(sql)) {
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("name"));
                int deptId = rs.getInt("department_id");
                e.setDepartment(new Department(deptId));
                e.setSalary(rs.getDouble("salary"));


                employees.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public Employee getEmployee(int id) {
        Employee employee = null;
        String sql = "select * from employees where id = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    employee = new Employee();
                    employee.setId(rs.getInt("id"));
                    employee.setName(rs.getString("name"));
                    int deptId = rs.getInt("department_id");
                    employee.setDepartment(new Department(deptId));
                    employee.setSalary(rs.getDouble("salary"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }



    /**
     * Lưu employee mới vào database
     * @param emp Employee object cần lưu
     */
    public void addEmployee(Employee emp) {
        String sql = "INSERT INTO employees(name, salary, department_id) VALUES (?, ?, ?)";

        try (Connection conn = this.dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emp.getName());
            ps.setDouble(2, emp.getSalary()); // Sử dụng setInt thay vì setDouble
            ps.setInt(3, emp.getDepartment().getId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            // Có thể throw runtime exception để caller handle
            // throw new RuntimeException("Error saving employee", e);
        }
    }

    /**
     * Cập nhật thông tin employee trong database
     * @param emp Employee object chứa thông tin cập nhật
     */
    public void updateEmployee(Employee emp) {
        String sql = "UPDATE employees SET name = ?, salary = ?, department_id = ? WHERE id = ?";

        try (Connection conn = this.dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emp.getName());
            ps.setDouble(2, emp.getSalary()); // Sử dụng setInt thay vì setDouble
            ps.setInt(3, emp.getDepartment().getId());
            ps.setInt(4, emp.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            // Có thể throw runtime exception để caller handle
            // throw new RuntimeException("Error updating employee", e);
        }
    }
    public boolean deleteEmployee(int id) {

            String sql = "delete from employees where id = ?";
            try (Connection conn = dbUtil.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                int rowsAffected = ps.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

    }

    public List<Employee> getAllByDepartment(int deptId) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE department_id = ?";

        try (Connection conn = this.dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, deptId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Employee employee = new Employee(
                            rs.getInt("id"),
                            rs.getString("name"),
                            new Department(rs.getInt("department_id")),
                            rs.getInt("salary")
                    );
                    list.add(employee);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
