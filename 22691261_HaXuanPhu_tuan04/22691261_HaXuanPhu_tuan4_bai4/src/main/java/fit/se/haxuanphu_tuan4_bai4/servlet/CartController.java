package fit.se.haxuanphu_tuan4_bai4.servlet;

import fit.se.haxuanphu_tuan4_bai4.beans.Book;
import fit.se.haxuanphu_tuan4_bai4.beans.Cart;
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

@WebServlet("/cart")
public class CartController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Resource(name = "jdbc/bookstoredb")
    private DataSource dataSource;
    private BookDAO bookDAO;

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
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);

        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
        } else {
            request.getSession().setAttribute("cart", cart);
        }

        if (action == null) {
            request.getRequestDispatcher("/WEB-INF/views/giohang.jsp").forward(request, response);
        } else if (action.equals("add")) {
            int bookId = Integer.parseInt(request.getParameter("id"));
            System.out.println(bookId);
            Book book = bookDAO.getBook(Integer.toString(bookId));

            cart.addCartItem(book);
            request.getSession().setAttribute("cart", cart);
            request.getRequestDispatcher("giohang.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            int bookId = Integer.parseInt(request.getParameter("id"));
            // Get the index of the item in the cart
            int index = -1;
            for (int i = 0; i < cart.getCartItems().size(); i++) {
                if (cart.getCartItems().get(i).getBook().getId() == bookId) {
                    index = i;
                    break;
                }
            }
            // If the item is not in the cart, do nothing
            if (index == -1) {
                request.getRequestDispatcher("giohang.jsp").forward(request, response);
                return;
            }
            // If the item is in the cart, delete it
            cart.deleteCartItem(index);
            request.getSession().setAttribute("cart", cart);
            request.getRequestDispatcher("giohang.jsp").forward(request, response);
        } else if (action.equals("increase")) {
            int bookId = Integer.parseInt(request.getParameter("id"));
            cart.increaseQuantity(bookId);
            request.getSession().setAttribute("cart", cart);
            request.getRequestDispatcher("giohang.jsp").forward(request, response);
        } else if (action.equals("decrease")) {
            int bookId = Integer.parseInt(request.getParameter("id"));
            cart.decreaseQuantity(bookId);
            request.getSession().setAttribute("cart", cart);
            request.getRequestDispatcher("giohang.jsp").forward(request, response);
        } else if (action.equals("checkout")) {
            // Clear the cart
            cart.getCartItems().clear();
            cart.setOrderTotal(0);
            request.getSession().setAttribute("cart", cart);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}