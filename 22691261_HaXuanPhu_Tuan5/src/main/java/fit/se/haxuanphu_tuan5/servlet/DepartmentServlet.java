package fit.se.haxuanphu_tuan5.servlet;

import fit.se.haxuanphu_tuan5.dao.DepartmentDAO;
import fit.se.haxuanphu_tuan5.model.Department;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/department-controller")
public class DepartmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Resource(name = "jdbc/employeedb")
    private DataSource dataSource;
    private DepartmentDAO departmentDAO;

    @Override
    public void init(ServletConfig conf) throws ServletException {
        // Initialize dataSource and departmentDAO here
        super.init(conf);
        try {
            System.out.println("=== DEBUG: Testing DB connection ===");
            System.out.println(this.dataSource.getConnection());
        }catch (Exception e) {
            throw new ServletException("Cant connect to DB", e);
        }

        try {
            departmentDAO = new DepartmentDAO(dataSource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       String action = request.getParameter("action");
       if (action == null) {
           action = "list";
       }
       switch (action) {
           case "new":
               request.getRequestDispatcher("department-form.jsp").forward(request, response);
               break;

           case "delete":
               int id = Integer.parseInt(request.getParameter("id"));
               this.departmentDAO.deleteDepartment(id);
               response.sendRedirect("department-controller");
               break;

           default:
               List<Department> departments = this.departmentDAO.getAllDepartments();
               request.setAttribute("departments", departments);
               request.getRequestDispatcher("department-list.jsp").forward(request, response);
               break;
       }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");

        Department d = new Department();
        d.setName(name);

        if (idStr != null && !idStr.isEmpty()) {
            d.setId(Integer.parseInt(idStr));
            this.departmentDAO.updateDepartment(d);
        } else {
            this.departmentDAO.addDepartment(d);
        }

        // Redirect về danh sách departments
        response.sendRedirect("department-controller");


    }
}
