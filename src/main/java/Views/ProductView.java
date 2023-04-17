package Views;

import Model.Product;
import Model.Status;
import Service.ProductService;
import Utilities.DateUtils;
import Utilities.PriceSortASC;
import Utilities.PriceSortDESC;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ProductView {
    DecimalFormat format = new DecimalFormat("###,###,###" + "đ");
    Scanner scanner  = new Scanner(System.in);
    Menu menu = new Menu();
    ProductService productService = new ProductService();
    public ProductView() {

    }

    public void add() {
        int id = 0;
        boolean checkID = false;
        do{
            System.out.println("Nhập ID sản phẩm:");
            try {
                id = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("ID phải là 1 số");
                checkID = false;
            }
            if (id > 0){
                if (productService.exitsProductId(id)){
                    System.out.println("ID đã tồn tại");
                    checkID = false;
                } else {
                    checkID = true;
                }
            } else {
                System.out.println("ID phải lớn hơn 0");
                checkID = false;
            }
        }while (!checkID);
        String name;
        while (true) {
            System.out.println("Nhập tên sản phẩm: ");
            System.out.print("\t=> ");
            String check = scanner.nextLine();
            if (productService.existProductName(check)) {
                System.out.println(" == Tên này đã tồn tại xin nhập tên khác ==");
            } else {
                name = check;
                break;
            }
        }
        System.out.println("Nhập size");
        String size = scanner.nextLine();

        System.out.println("Nhập màu");
        String color = scanner.nextLine();

        System.out.println("Nhập mô tả");
        String description = scanner.nextLine();

        int quantity;
        while (true) {
            System.out.println("Nhập số lượng sản phẩm: ");
            System.out.print("\t=> ");
            try {
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity > 0) {
                    break;
                }
                System.out.println("\t == Số lượng phải lớn hơn 0 ==");
                System.out.println();
            } catch (Exception e) {
                System.out.println("\t == Số lượng phải là 1 số ==");
                System.out.println();
            }
        }
        double price;
        while (true) {
            System.out.println("Nhập giá sản phẩm: ");
            System.out.print("\t=> ");
            try {
                price = Double.parseDouble(scanner.nextLine());
                if (price > 0) {
                    break;
                }
                System.out.println("\t== Giá phải lớn hơn 0 ==");
                System.out.println();
            } catch (Exception e) {
                System.out.println("\t== Giá phải là 1 số ==");
                System.out.println();
            }
        }
        Status status = Status.INSTOCK;
        Product product = new Product(id, name,size,color, description, quantity, DateUtils.formatDate(new Date()), price, status);
        productService.add(product);
        System.out.println("> Bạn đã thêm sản phẩm thành công <\n");
        System.out.println("\t\t\t\t==========================================================================================================\n\n");
        menu.boss();
    }
    public void showProduct() {
        List<Product> products = productService.getProduct();
        System.out.println("Danh sách sản phẩm");
        System.out.println("\\t\\t\\t\\t======================================================================================================");
        System.out.printf("\t\t\t\t%-10s %-25s %-25s %-15s %-25s %-25s %-25s %-25s %-25s\n", "ID", "Tên Sản Phẩm", "Size",
                    "Màu sắc","Mô Tả", "Số Lượng", "Ngày Nhập", "Giá", "Trạng Thái");
        for (Product product : products) {
            System.out.printf("\t\t\t\t%-10s %-25s %-25s %-15s %-25s %-25s %-25s %-25s %-25s\n",
                    product.getId(), product.getNameProduct(), product.getSize(), product.getColor(),
                    product.getDescription(),product.getQuantity(),product.getEntryDate(),format.format(product.getPrice()), product.getStatus());
        }
        System.out.println("\t\t\t\t===========================================================================================================\n\n");
      menu.boss();
    }

    public void showProductBoss(){
        List<Product> products = productService.getProduct();
        System.out.println("Danh sách sản phẩm");
        System.out.println("\t\t\t\t===========================================================================================================");
        System.out.printf("\t\t\t\t%-10s %-25s %-30s %-15s %-25s \n", "ID", "Tên sản phẩm","Size", "Số lượng", "Giá");
        for (Product product :products) {
            System.out.printf("\t\t\t\t%-10s %-25s %-30s %-15s %-25s \n", product.getId(), product.getNameProduct(),product.getSize(), product.getQuantity(), product.getPrice());

        }

        System.out.println("\t\t\t\t===========================================================================================================\n\n");
        menu.menuBoss();
    }

    public void showProductGuest() {
        List<Product> products = productService.getProduct();
        System.out.println("Danh sách sản phẩm");
        System.out.println("\t\t\t\t===========================================================================================================");
        System.out.printf("\t\t\t\t%-10s %-25s %-30s %-15s %-25s \n", "ID", "Tên sản phẩm","Size", "Số lượng", "Giá");
        for (Product product :products) {
            System.out.printf("\t\t\t\t%-10s %-25s %-30s %-15s %-25s \n", product.getId(), product.getNameProduct(),product.getSize(), product.getQuantity(), product.getPrice());

        }
        System.out.println("\t\t\t\t===========================================================================================================\n\n");
        menu.guest();
    }

    public void sortASC() {
        List<Product> p = productService.getProduct();
        p.sort(new PriceSortASC());
        System.out.println("Dan sách sản phẩm");
        System.out.println("\t\t\t\t=========================================================================================================================");
        System.out.printf("\t\t\t\t%-10s %-25s %-25s %-15s %-25s\n","ID", "Tên Sản Phẩm", "Size", "Màu Sắc", "Giá");
        for (Product product : p) {
            System.out.printf("\t\t\t\t%-10s %-25s %-25s %-15s %-25s\n", product.getId(), product.getNameProduct(), product.getSize(), product.getColor(), product.getPrice());
        }
        System.out.println("\t\t\t\t=========================================================================================================================");
        menu.guest();
    }

    public void sortDESC() {
        List<Product> p = productService.getProduct();
        p.sort(new PriceSortDESC());
        System.out.println("Danh sách sản phẩm");
        System.out.println("\t\t\t\t=========================================================================================================================");
        System.out.printf("\t\t\t\t%-10s %-25s %-25s %-15s %-25s\n","ID", "Tên Sản Phẩm", "Size", "Màu Sắc", "Giá");
        for (Product product : p) {
            System.out.printf("\t\t\t\t%-10s %-25s %-25s %-15s %-25s\n", product.getId(), product.getNameProduct(), product.getSize(), product.getColor(), product.getPrice());
        }
    }
    public void findProductByNameBoss() {
        System.out.println("Nhập tên sản phẩm cần tìm");
        String name = scanner.nextLine().toUpperCase();
        boolean found = false;
        List<Product> p = productService.getProduct();
        System.out.println("Danh sách sản phẩm");
        System.out.println("\t\t\t\t=========================================================================================================================\n");
        System.out.printf("\t\t\t\t%-10s %-25s %-25s %-15s \n","ID", "Tên Sản Phẩm", "Size","Màu Sắc");
        for (Product product :p) {
            if (product.getNameProduct().toUpperCase().contains(name)){
                System.out.printf("\t\t\t\t%-10s %-25s %-25s %-15s \n", product.getId(), product.getNameProduct(),product.getSize(),product.getColor());
                found = true;
            }
        }
        if (!found) {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t ✖ Không có sản phẩm này ✖");
        }
        System.out.println("\t\t\t\t=========================================================================================================================\n");
        menu.boss();
    }

    public void findProductByNameGuest() {
        System.out.println("Nhập tên sản phẩm cần tìm");
        String name = scanner.nextLine().toUpperCase();
        boolean found = false;
        List<Product> p = productService.getProduct();
        System.out.println("Danh sách sản phẩm");
        System.out.printf("\t\t\t\t%-10s %-25s %-25s %-15s %-25s\n","ID", "Tên sản phẩm", "Size","Color", "Giá");
        for (Product product : p) {
            if (product.getNameProduct().toUpperCase().contains(name)){
                System.out.println("\t\t\t\t=================================================================================================================\n");
                System.out.printf("\t\t\t\t%-10s %-25s %-25s %-15s %-25s\n", product.getId(),product.getNameProduct(),product.getSize(),product.getColor(),product.getPrice());
                found = true;
            }
        }
        if (!found){
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t ✖ Không có sản phẩm này ✖");
        }
                System.out.println("\t\t\t\t=================================================================================================================\n");
        menu.guest();
    }

    public void updateProduct(){
        int id = 0;
        Product update = null;
        while (true) {
            System.out.println("Nhập ID sản phẩm cần sửa");
            System.out.print("\t=> ");
            try {
                id = Integer.parseInt(scanner.nextLine());
                if (id > 0) {
                    if (productService.existProduct(id)) {
                        update = productService.findProductbyID(id);
                        break;
                    } else {
                        System.out.println(" == ID này không tồn tại ==");
                    }
                } else {
                    System.out.println("\t == ID phải lớn hơn 0 ==");
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println("\t == ID phải là 1 số ==");
                System.out.println();
            }
        }


        while (true) {
            System.out.println("Nhập tên cần sửa");
            System.out.print("\t=> ");
            String name = scanner.nextLine();
            if (!name.equals("-1")) {
                if (productService.existProductName(name)) {
                    System.out.println(" == Tên này đã tồn tại xin nhập tên khác ==");
                } else {
                    update.setNameProduct(name);
                    break;
                }
            } else {
                break;
            }
        }
        noChange();
        while (true) {
            System.out.println("Nhập số lượng sản phẩm: ");
            System.out.print("\t=> ");
            try {
                int quantity = Integer.parseInt(scanner.nextLine());
                if (!(quantity == -1)) {
                    if (quantity >= 0) {
                        update.setQuantity(quantity);
                        break;
                    }
                    System.out.println("\t * Số lượng phải lớn hơn 0 *");
                    System.out.println();
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("\t * Số lượng phải là 1 số *");
                System.out.println();
            }
        }
        noChange();
        while (true) {
            System.out.println("Nhập giá sản phẩm: ");
            System.out.print("\t=> ");
            try {
                long price = Long.parseLong(scanner.nextLine());
                if (!(price == -1)) {
                    if (price >= 0) {
                        update.setPrice(price);
                        break;
                    }
                    System.out.println("\t == Giá phải lớn hơn 0 ==");
                    System.out.println();
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("\t == Giá phải là 1 số ==");
                System.out.println();
            }
        }
        noChange();
        while (true) {
            System.out.println("Nhập mô tả sản phấm: ");
            System.out.print(" \t=> ");
            String description = scanner.nextLine();
            if (!(description.equals("-1"))) {
                update.setDescription(description);
                break;
            } else {
                break;
            }
        }
        productService.update(id, update);
        System.out.println("> Bạn đã cập nhật sản phẩm thành công <\n");
        System.out.println("\t\t\t\t=========================================================================================================================");
        productService.checkExist();
        menu.boss();
    }


    public void noChange() {
        System.out.println(" ⦿ Nếu không thay đổi gì thì nhập: -1 ⦿ ");
    }
    public void setStatus(Product product){
        System.out.println("=       = SET STATUS =     =");
        System.out.println("∥   1. IN STOCK            ∥");
        System.out.println("∥   2. OUT OF STOCK        ∥");
        System.out.println("= = = = = = = = = = = = = = ");
        System.out.println("Chọn Status: ");
        System.out.print(" ⭆ ");
        int options;
        try {
            options = Integer.parseInt(scanner.nextLine());
            switch (options){
                case -1:
                    break;
                case 1:
                    product.setStatus(Status.INSTOCK);
                case 2:
                    product.setStatus(Status.OUTOFSTOCK);
                default:
                    System.out.println("Nhập sai, vui lòng nhập lại");
                    setStatus(product);
            }
        } catch (Exception e) {
            System.out.println("Lựa chon phải là 1 số");
            System.out.println("\t=========================");
            setStatus(product);
        }
    }


    public  void deleteProductByID() {
        boolean check_id = false;
        int id = 0;
        do {
            System.out.println("Nhập ID mà bạn muốn xoá");
            try {
                id = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e ) {
                System.out.println("Id phải là một số, vui lòng nhập lại");
                id = 0;
                continue;
            }

        boolean checkIdAvailable = productService.existProduct(id);
        if (checkIdAvailable) {
            productService.deleteBookByID(id);
            showProduct();
            check_id = true;
        }else {
            System.out.println("Không tìm thấy Id, vui lòng nhập lại");
            check_id = false;
        }
        }while (!check_id);
    }
}
