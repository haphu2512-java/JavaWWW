package fit.se.haxuanphu_tuan4_bai4.beans;

public class CartItem {
    private Book book;
    private int quantity;
    private double totalPrice;

    public CartItem() {}

    public CartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
        this.totalPrice = book.getPrice() * quantity;
    }

    // Getters and Setters
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
        updateTotalPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        updateTotalPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    private void updateTotalPrice() {
        if (book != null) {
            this.totalPrice = book.getPrice() * quantity;
        }
    }

    public void increaseQuantity() {
        this.quantity++;
        updateTotalPrice();
    }

    public void decreaseQuantity() {
        if (this.quantity > 1) {
            this.quantity--;
            updateTotalPrice();
        }
    }
}