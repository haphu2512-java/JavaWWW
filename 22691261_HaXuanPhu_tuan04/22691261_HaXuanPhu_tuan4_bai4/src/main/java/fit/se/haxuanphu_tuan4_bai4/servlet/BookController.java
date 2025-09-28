package fit.se.haxuanphu_tuan4_bai4.servlet;

import fit.se.haxuanphu_tuan4_bai4.beans.Book;
import fit.se.haxuanphu_tuan4_bai4.dao.BookDAO;
import fit.se.haxuanphu_tuan4_bai4.dao.impl.BookDAOImpl;
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

@WebServlet("/book-controller")
public class BookController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Resource(name = "jdbc/bookstoredb")
    private DataSource dataSource;
    private BookDAO bookDAO;

    @Override
    public void init(ServletConfig conf) throws ServletException {
        // TODO Auto-generated method stub
        super.init(conf);
        try {

            System.out.println(this.dataSource.getConnection());
        } catch (Exception e) {
            throw new ServletException(e);
        }
        try {
            bookDAO = new BookDAOImpl(dataSource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Book> list = bookDAO.getAllBooks();
        System.out.println("Số lượng sách lấy được: " + list.size());
        request.setAttribute("books", list);

        request.getRequestDispatcher("danhsach.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

}
