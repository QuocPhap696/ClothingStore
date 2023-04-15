package Comparator;

import Model.Product;
import java.util.Comparator;


public class ComparatorById implements Comparator<Product>{
   @Override
    public int compare(Product o1, Product o2) {
        if (o1.getId() > o2.getId()) {
            return 1;
        } else if (o1.getId() == o2.getId()) {
            return 0;
        }
        else {
            return -1;
        }
    }
}
