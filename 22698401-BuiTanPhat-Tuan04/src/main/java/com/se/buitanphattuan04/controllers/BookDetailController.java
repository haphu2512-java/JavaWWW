package com.se.buitanphattuan04.controllers;

import java.io.IOException;

import javax.sql.DataSource;

import com.se.buitanphattuan04.beans.Book;
import com.se.buitanphattuan04.dao.BookDAO;
import com.se.buitanphattuan04.dao.impl.BookDAOImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookDetailController
 */
@WebServlet("/book-detail")
public class BookDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Resource(name = "jdbc/bookdb")
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

        bookDAO = new BookDAOImpl(this.dataSource);
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
        request.getRequestDispatcher("book-detail.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}