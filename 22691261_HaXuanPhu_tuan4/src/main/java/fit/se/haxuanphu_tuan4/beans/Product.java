package fit.se.haxuanphu_tuan4.beans;

public class Product {
    private int id;
    private String model;
    private String desscription;
    private int quantity;
    private double price;
    private String image;

    public Product(int id, String model, String desscription, int quantity, double price, String image) {
        this.id = id;
        this.model = model;
        this.desscription = desscription;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getDesscription() {
        return desscription;
    }
    public void setDesscription(String desscription) {
        this.desscription = desscription;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

}
