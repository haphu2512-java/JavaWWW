    package fit.se.haxuanphu_tuan5.dao;

    import fit.se.haxuanphu_tuan5.model.Department;
    import fit.se.haxuanphu_tuan5.util.DBUtil;

    import javax.sql.DataSource;
    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;

    public class DepartmentDAO {
        private DBUtil dbUtil;
        public DepartmentDAO(DataSource ds) {
            dbUtil = new DBUtil(ds);
        }

        public List<Department> getAllDepartments() {
            List<Department> departments = new ArrayList<>();
            String sql = "select * from departments";
            System.out.println("Getting all departments...");
            try (Connection conn = dbUtil.getConnection();
                Statement ps = conn.createStatement();

                ResultSet rs = ps.executeQuery(sql)) {
                while (rs.next()) {
                    Department d = new Department();
                    d.setId(rs.getInt("id"));
                    d.setName(rs.getString("name"));
                    departments.add(d);
                    }
                } catch (SQLException e) {
                e.printStackTrace();
                }
            return departments;
        }

        public Department getDepartmentById(int id) {
            Department d = null;
            String sql = "select * from departments where id = ?";
            try (Connection conn = dbUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        d = new Department();
                        d.setId(rs.getInt("id"));
                        d.setName(rs.getString("name"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return d;
        }

        public boolean addDepartment(Department department) {
            String sql = "insert into departments (name) values (?)";
            try (Connection conn = dbUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, department.getName());
                int rowsAffected = ps.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean updateDepartment(Department department) {
            String sql = "update departments set name = ? where id = ?";
            try (Connection conn = dbUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, department.getName());
                ps.setInt(2, department.getId());
                int rowsAffected = ps.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean deleteDepartment(int id) {
            String sql = "delete from departments where id = ?";
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

    }

