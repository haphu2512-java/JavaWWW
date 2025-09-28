package fit.se.haxuanphu_tuan4_bai4.dao;

import fit.se.haxuanphu_tuan4_bai4.beans.Book;


import java.util.List;

public interface BookDAO {

    public void addBook(Book book);

    public Book getBook(String id);

    public List<Book> getAllBooks();

}
