package Service;

import Model.Product;

import java.util.List;

public interface IProductService {
 void add(Product newProduct);
 void update(int id, Product updateProduct);
 List<Product> getProduct();
 void updateQuantity(int id, int quantity);


}
