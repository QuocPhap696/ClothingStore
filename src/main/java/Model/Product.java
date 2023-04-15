package Model;

import Utilities.CurrencyFormat;

import java.util.List;

public class Product {
    private int Id;
    private String NameProduct;
    private String Size;
    private String Color;
    private String Description;
    private int Quantity;
    private String EntryDate;
    private double Price;
    private Status Status;

    public List<Product> products;


    public Product(int id, String name, int quantity, double price, String description, Model.Status status) {
    }

    public Product(Integer id, String nameProduct, String size, String color, String description,Integer quantity, String entryDate, double price, Status Status) {
        this.Id = id;
        this.NameProduct = nameProduct;
        this.Size = size;
        this.Color = color;
        this.Description = description;
        this.Quantity= quantity;
        this.EntryDate = entryDate;
        this.Price = price;
        this.Status = Status;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNameProduct() {
        return NameProduct;
    }

    public void setNameProduct(String nameProduct) {
        NameProduct = nameProduct;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getEntryDate() {
        return EntryDate;
    }

    public void setEntryDate(String entryDate) {
        EntryDate = entryDate;
    }

    public double getPrice() {
        return this.Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public Model.Status getStatus() {
        return Status;
    }

    public void setStatus(Model.Status status) {
        Status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product(String raw) {
        String[] strings = raw.split(",");
        this.Id = Integer.parseInt(strings[0]);
        this.NameProduct = strings[1];
        this.Size = strings[2];
        this.Color = strings[3];
        this.Description = strings[4];
        this.Quantity = Integer.parseInt(strings[5]);
        this.EntryDate =  strings[6];
        this.Price = CurrencyFormat.parseDouble(strings[7]);
//        this.Price = Double.parseDouble(strings[7]);
        this.Status = Model.Status.getStatus(strings[8]);
    }



    public String toViewer() {
        return String.format("\t\t\t\t%-10s %-25s %-25s %-15s %-25s %-25s %-25s %-25% %-25s\n", this.getId(), this.getNameProduct(), this.getSize(), this.getColor(),
                this.getDescription(),this.getQuantity(),this.getEntryDate(), this.getPrice(), this.getStatus().getValue());
    }

    @Override
    public String toString() {
        //7,Hoodie,XL,Black,Form OverSize,10,20-02-2023 19:09:20,3765000,Còn
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",
                this.Id, this.NameProduct, this.Size, this.Color, this.Description, this.Quantity, this.EntryDate,CurrencyFormat.parseInteger(this.Price), this.Status
                );
    }


}
