package Service;

import Model.Product;
import Model.Status;
import Utilities.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    List<Product> productList = new ArrayList<>();
    public static String path = "/Users/QuocPhap/Desktop/ClothingStore/src/main/Data/Product.csv";
    @Override
    public void add(Product newProduct) {
    productList.add(newProduct);
        CSVUtils.write(path, productList);
    }

    @Override
    public void update(int id, Product updateProduct) {
        getProduct();
        for (int i = 0; i < productList.size(); i++){
            if (productList.get(i).getId()== id){
                productList.set(i,updateProduct);
                CSVUtils.write(path,productList);
            }
        }
    }

    @Override
    public List<Product> getProduct() {
        List<Product> newProductList = new ArrayList<>();
        List<String> reads = CSVUtils.read(path);
        for (String read: reads){
            newProductList.add(new Product(read));
        }
        return productList = newProductList;
    }

    @Override
    public void updateQuantity(int id, int quantity) {
    getProduct();
    for (int i = 0; i < productList.size(); i++) {
        if (productList.get(i).getId()==id){
            productList.get(i).setQuantity(quantity);
            CSVUtils.write(path,productList);
        }
     }
    }
    public int getQuantity(int id){
        getProduct();
        int soluong = 0;
        for (Product product : productList){
            if (product.getId() == id){
                soluong = product.getQuantity();
            }
        }
        return soluong;
    }

    public boolean exitsProductName(String name){
        getProduct();
        for (Product product : productList) {
            if (product.getNameProduct().equals(name)){
                return true;
            }
        }
        return false;
    }
    public boolean exitsProductId(int id){
        getProduct();
        for (Product product : productList) {
            if (product.getId()==id){
                return true;
            }
        }
        return false;
    }
    public Product findProductById(int id){
        getProduct();
        for (Product product:productList){
            if (product.getId()== id){
                return product;
            }
        }
        System.out.println("Không có sản phẩm này");
        return null;
    }

    public void checkExist(){
        getProduct();
        for (Product p : productList) {
            if (p.getQuantity() == 0){
                p.setStatus(Status.OUTOFSTOCK);
                CSVUtils.write(path,productList);
            } else {
                p.setStatus(Status.INSTOCK);
                CSVUtils.write(path, productList);
            }
        }
    }
    public boolean existProduct(int id){
        getProduct();
        for (Product product: productList) {
            if (product.getId() == id){
                return true;
            }
        }
        return false;
    }
    public Product findProductbyID(int id){
        getProduct();
        for (Product product : productList) {
            if (product.getId() == id){
                return product;
            }
        }
        System.out.println("\t\t\t\tKhông có sản phẩm này");
        return null;
    }
    public boolean existProductName(String name){
        getProduct();
        for (Product product: productList) {
            if (product.getNameProduct().equals(name)){
                return true;
            }
        }
        return false;
    }
}
