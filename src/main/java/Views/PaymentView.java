package Views;

import Model.Payment;
import Model.Product;
import Service.PaymentService;
import Service.ProductService;
import Utilities.CSVUtils;
import Utilities.DateUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PaymentView {
    DecimalFormat format = new DecimalFormat("###,###,###" + " đ");
    Scanner scanner = new Scanner(System.in);
    PaymentService paymentService = new PaymentService();
    ProductService productService = new ProductService();
    List<Payment> list = new ArrayList<>();
    Menu menu = new Menu();
    int id1 = 0;
    int quantityPurchased = 0;
    String name;
    String phone;
    String address;
    double revenue;
    int realQuantity;
    int quantity;

    int currentQuantity;


    Product product = null;
    public PaymentView() {
    }

    public void buy() {
        List<Product> products = productService.getProduct();
        int id1;
        double price = 0;
        while (true) {
            System.out.println("Nhập ID sản phẩm cần mua");
            Product update = null;
            while (true) {
                try {
                    id1 = Integer.parseInt(scanner.nextLine());
                    if (id1 > 0) {
                        if (productService.exitsProductId(id1)){
                            update = productService.findProductById(id1);
                            break;
                        } else {
                            System.out.println("ID này không tồn tại");
                        }
                    } else {
                        System.out.println("ID phải lớn 0");
                        System.out.println();
                    }
                } catch (Exception e) {
                    System.out.println("ID phải là 1 số");
                    System.out.println();
                }
            }
//            int id = Integer.parseInt(scanner.nextLine());

            try {
                System.out.println("Nhập số lượng");
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity >= 0) {
                    for (int i = 0; i < products.size(); i++){
                        if (quantity <= products.get(i).getQuantity() && products.get(i).getId()==id1) {
                            currentQuantity = products.get(i).getQuantity() - quantity;
                            quantityPurchased = quantity;
                            price = products.get(i).getPrice();
                            break;
                        } else {
                            System.out.println("Số lương vượt qua hàng có sẵn");
                        }
                    }
                    revenue = quantityPurchased * price;
                    Payment p = new Payment(id1, quantityPurchased, revenue);
                    if (list.size()== 0) {
                        list.add(p);
                    } else {
                        boolean isUpdate = false;
                        boolean isError = false;
                        for (Payment t : list) {
                            int x = t.getId();
                            if (x == id1) {
                                if (quantityPurchased + t.getQuantity() > realQuantity) {
                                    isError = true;
                                    break;
                                } else {
                                    t.setTotal((long) (t.getQuantity()*price));
                                    t.setQuantity(quantityPurchased + t.getQuantity());
                                    isUpdate = true;
                                }
                            }
                        }
                        if (isError) {
                            System.out.println("Vượt qua số lượng hàng có sẵn");
                            buy();;
                        } else if (!isUpdate) {
                            list.add(p);
                        }
                    }
                    option();
                } else {
                    System.out.println("Số lượng phải lớn hơn 0");
                }
            } catch (Exception e) {
                System.out.println("Số lượng phải là 1");
            }
        }



    }

    public void showTotal() {
        System.out.println("Danh sách sản phẩm cần mua");
        System.out.println("______________________");
        long sum = 0;
        for (int i = 0; i < list.size(); i++) {
            long total;
            total = (long) (list.get(i).getQuantity() * productService.findProductById(list.get(i).getId()).getPrice());
            System.out.printf("Sản phẩm của bạn là : %s, số lượng %s\n", productService.findProductById(list.get(i).getId()).getNameProduct(), productService.findProductById(list.get(i).getId()).getQuantity());
            sum += total;
        }
        System.out.println("________________________________________");
        System.out.println("Số tiền cần thanh toán :" + format.format(sum));
        System.out.println("________________________________________");
        check();
    }

    public void showInformation(){
        System.out.println("Thông tin đơn hàng");
        System.out.println("__________________");
        System.out.println("Tên khách hàng :" + list.get(0).getName());
        System.out.println("Số điện thoại :" + list.get(0).getPhoneNumber());
        System.out.println("Danh sách sản phẩm mua");
        long sum = 0;
        for (int i = 0; i < list.size(); i++) {
            long total;
            total = (long) (list.get(i).getQuantity() * productService.findProductById(list.get(i).getId()).getPrice());
            System.out.printf("Tổng tiền là :%s\n ", productService.findProductById(list.get(i).getId()).getNameProduct(), format.format(total));
            sum += total;
        }
        System.out.println("________________________________________");
        System.out.println("Số cần cần thanh toán là :" + format.format(sum));
        System.out.println("________________________________________\n\n");
        afterPay();
    }

    public void showAllIncome() {
        System.out.println("===================================");
        System.out.println("Tổng doanh thu : " + format.format(paymentService.showTotal()));
        System.out.println("===================================\n\n");
        menu.boss();
    }

    public void option() {
        System.out.println("\t\t\t\tNhập 1 để mua thêm sản phẩm");
        System.out.println("\t\t\t\tNhập 2 để xem tổng tiền");
        System.out.print("\t➺ ");
        int options;
        try {
            options = Integer.parseInt(scanner.nextLine());
            switch (options){
                case 1 :
                    buy();
                    break;
                case 2 :
                    showTotal();
                    break;
                default:
                    System.out.println("\t\t\tNhập không đúng! Vui lòng nhập lại");
                    option();
            }
        } catch (Exception e) {
            System.out.println("Lựa chọn phải là 1 số");
            System.out.println();
            option();
        }
    }

    public void check(){
        System.out.println("Bạn có muốn thanh toán đơn hàng này không ?");
        System.out.println("Nhập 1 để thanh toán");
        System.out.println("Nhập 2 để mua lại");
        System.out.println("\t➺ ");
        int options;
        try {
            options = Integer.parseInt(scanner.nextLine());
            switch (options){
                case 1:
                    pay();
                    return;
                case 2 :
                    list.clear();
                    buy();
                    break;
                default:
                    System.out.println("Nhập sai, vui lòng nhập lại");
                    check();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lựa chọn phải là 1 số");
            System.out.println();
            check();
        }
    }


    public void pay(){
        System.out.println("Nhập thông tin cá nhân");
        System.out.println("∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘");
        System.out.println("Nhập họ tên");
        System.out.printf("\t➺ ");
        name = scanner.nextLine();
        while (!DateUtils.isNameValid(name)){
            System.out.println("Tên" + name + "không đúng định dạng"+" Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu)");
            System.out.println("Nhập tên");
            System.out.print("\t➺ ");
            name = scanner.nextLine();
        }
        System.out.println("Nhập số điện thoại (vd: 0336056504): ");
        System.out.print("\t➺ ");
        phone = scanner.nextLine();
        while (!DateUtils.isPhoneValid(phone)){
            System.out.println("Số" + phone + "không đúng định dạng. Vui lòng nhập lại! \" + \"(Số điện thoại bao gồm 10 số và bắt đầu là số 0)");
            System.out.println("Nhập số điện thoại");
            System.out.print("\t➺ ");
            phone = scanner.nextLine();
        }
        System.out.println("Nhập địa chỉ :");
        System.out.println("\t➺ ");
        address = scanner.nextLine();
        while (!DateUtils.isAddValid(address)){
            System.out.println("Nhập địa chỉ");
            System.out.println("\t➺ ");
            address = scanner.nextLine();
        } for (Payment p :list) {
            p.setName(name);
            p.setPhoneNumber(phone);
            p.setAddress(address);
        } paymentService.add(list);
        for (Payment u : list) {
            int a = u.getId();
            int c = u.getQuantity();
            int b = productService.getQuantity(a);
            productService.updateQuantity(a, b-c);
        }
        productService.checkExist();
        System.out.println("Thanh toán thành công");
        afterPay();
    }

    public void afterPay(){
        System.out.println("Bạn có muốn kiểm tra thông tin thanh toán không?");
        System.out.println("1. Để xem thông tin thanh toán");
        System.out.println("2. Để quay lại menu");
        System.out.println("3. Để thoát");
        System.out.print("\t➺ ");
        int options;
        try {
            options = Integer.parseInt(scanner.nextLine());
            switch (options) {
                case 1:
                    showInformation();
                    break;
                case 2:
                    menu.guest();
                    break;
                case 3:
                    System.out.println("Thank You!");
                    menu.exit();
                    break;
                default:
                    System.out.println("Nhập sai, vui lòng nhập lại");
                    check();
            }
        } catch (Exception e) {
            System.out.println("Lựa chọn phải là 1 số");
            System.out.println();
            check();
        }
    }
}
