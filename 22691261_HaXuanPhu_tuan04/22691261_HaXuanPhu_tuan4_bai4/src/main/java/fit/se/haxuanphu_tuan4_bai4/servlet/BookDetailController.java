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

@WebServlet("/book-detail")
public class BookDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Resource(name = "jdbc/bookstoredb")
    private DataSource dataSource;

    private BookDAO bookDAO;

    public BookDetailController() {
        super();
    }

    @Override
    public void init(ServletConfig conf) throws ServletException {
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

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");

        Book book = bookDAO.getBook(id);

        System.out.println(book);

        request.setAttribute("book", book);
        request.getRequestDispatcher("chitietsach.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}