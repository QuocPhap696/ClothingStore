package Model;

public class OderItem {
    private long id;
    private int ProductId;
    private String ProductName;
    private int quantity;
    private double price;
    private double total;

    public OderItem() {
    }

    public OderItem(long id, int productId, String productName, int quantity, double price, double total) {
        this.id = id;
        this.ProductId = productId;
        this.ProductName = productName;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OderItem{" +
                "id=" + id +
                ", ProductId=" + ProductId +
                ", ProductName='" + ProductName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", total=" + total +
                '}';
    }
}
