package fit.se.haxuanphu_tuan5.servlet;

import fit.se.haxuanphu_tuan5.dao.DepartmentDAO;
import fit.se.haxuanphu_tuan5.dao.EmployeeDAO;
import fit.se.haxuanphu_tuan5.model.Department;
import fit.se.haxuanphu_tuan5.model.Employee;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;

@WebServlet({"/employee-controller"})
public class EmployeeServlet extends HttpServlet {

    @Resource(name = "jdbc/employeedb")
    private DataSource dataSource;
    private EmployeeDAO employeeDAO;
    private DepartmentDAO departmentDAO;

    public EmployeeServlet() {
        // Constructor rỗng
    }

    @Override
    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        try {
            System.out.println("=== DEBUG: Testing DB connection ===");
            System.out.println(this.dataSource.getConnection());
        }catch (Exception e) {
            throw new ServletException("Cant connect to DB", e);
        }

        try {
            departmentDAO = new DepartmentDAO(dataSource);
            employeeDAO = new EmployeeDAO(dataSource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "new":
                // Hiển thị form tạo mới employee
//                req.setAttribute("departments", this.departmentDAO.getAllDepartments());
                req.getRequestDispatcher("employee-form.jsp").forward(req, resp);
                break;

            case "edit":
                // Hiển thị form chỉnh sửa employee
                int id = Integer.parseInt(req.getParameter("id"));
                Employee emp = this.employeeDAO.getEmployee(id);
                req.setAttribute("employee", emp);
                req.setAttribute("departments", this.departmentDAO.getAllDepartments());
                req.getRequestDispatcher("employee-form.jsp").forward(req, resp);
                break;

            case "delete":
                // Xóa employee theo ID
                this.employeeDAO.deleteEmployee(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect("employee-controller");
                break;

            case "listByDept":
                // Hiển thị danh sách employees theo department
                int deptId = Integer.parseInt(req.getParameter("deptId"));
                req.setAttribute("employees", this.employeeDAO.getAllByDepartment(deptId));
                req.getRequestDispatcher("employee-list.jsp").forward(req, resp);
                break;

            default:
                // Hiển thị tất cả employees
                req.setAttribute("employees", this.employeeDAO.getAllEmployees());
                req.getRequestDispatcher("employee-list.jsp").forward(req, resp);
                break;
        }
    }

    /**
     * Xử lý các request POST
     * Tạo mới hoặc cập nhật employee
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Lấy parameters từ request
        String idStr = req.getParameter("id");
        String name = req.getParameter("name");
        int salary = Integer.parseInt(req.getParameter("salary"));
        int deptId = Integer.parseInt(req.getParameter("departmentId"));

        // Tạo employee object
        Employee emp = new Employee();
        emp.setName(name);
        emp.setSalary(salary);
        emp.setDepartment(new Department(deptId));

        if (idStr != null && !idStr.isEmpty()) {
            // Cập nhật employee existing
            emp.setId(Integer.parseInt(idStr));
            this.employeeDAO.updateEmployee(emp);
        } else {
            // Tạo mới employee
            this.employeeDAO.addEmployee(emp);
        }

        // Redirect về danh sách employees
        resp.sendRedirect("employee-controller");
    }
}