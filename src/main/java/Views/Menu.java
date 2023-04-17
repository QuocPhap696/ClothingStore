package Views;

import Model.User;
import Model.Product;
import Model.User;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    static ProductView productView = new ProductView();
    static PaymentView paymentView = new PaymentView();
    static Scanner scanner = new Scanner(System.in);

    private static User curentUser = null;
    private static boolean isFinished = false;


    public static void boss() {
        boolean isLoggedIn = checkLogin();
        if (!isLoggedIn){
            login();
            return;
        }
        boolean isFinished = false;
        menuBoss();
        int number = 0;
        while (!isFinished){
            try {
                System.out.println("Chọn chức năng");
                System.out.println("\t➺ ");
                number = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Nhập sai, xin nhập lại");
                number = 0;
                continue;
            }
                switch (number) {
                    case 1 :
                        productView.showProductBoss();
                        break;
                    case 2:
                        productView.add();
                        break;
                    case 3:
                        productView.updateProduct();
                        break;
                    case 4:
                        productView.findProductByNameBoss();
                        break;
                    case 5:
                        paymentView.showAllIncome();
                        break;
                    case 6:
                        productView.deleteProductByID();
                        break;
                    case 7:
                        chon();
                        break;
                    case 8:
                        exit();
                        break;

                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        break;
                }
        }
    }

    public static User login() {
        Loginview loginview = new Loginview();
        boolean isLoggedIn = false;
        User user = null;
        while (!isLoggedIn){
            try {
               user = loginview.login();
               isLoggedIn = true;
               curentUser = user;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e ){
                System.out.println("Lỗi, vui lòng nhập lại");
                return null;
            }
            }
        return user;
        }
        public static boolean checkLogin() {
        if (curentUser == null){
            System.out.println("Vui lòng đăng nhập trước khi sử dụng chức năng này!");
            return false;
        }
            return true;
    }

        public static void guest(){
        int number = 0;
        boolean checkAction = false;
        do {
            menuGuest();
            try {
                System.out.println("\nChọn chức năng ");
                System.out.print("\t➺ ");
                number = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Nhập sai, Vui lòng nhập lại");
                number = 0;
                continue;
            }
            switch (number) {
                case 1:
                    productView.showProductGuest();
                    checkAction = true;
                    break;
                case 2:
                    productView.findProductByNameGuest();
                    checkAction = true;
                    break;
                case 3:
                    productView.sortASC();
                    checkAction = true;
                    break;
                case 4:
                    productView.sortDESC();
                    checkAction = true;
                    break;
                case 5:
                    paymentView.buy();
                    checkAction = true;
                    break;
                case 6:
                    chon();
                    checkAction = true;
                    break;
                case 7:
                    exit();
                    checkAction = true;
                    break;
                default:
                    System.out.println("Chọn chức năng saim vui lòng nhập lại");
                    checkAction = false;
                    break;
            }
        }while (!checkAction);

        }

    public static void chon(){
        int number = 0;
        do {
            menuMain();
            try {

                System.out.println("\n Chọn chức năng");
                number = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e) {
                System.out.println("Nhập sai, vui lòng nhập lại");
                number = 0;
                continue;
            }
            switch (number){
                case 1:
                    boss();
                    break;
                case 2:
                    guest();
                    break;
                case 3:
                    exit();
                    break;
                default:
                    System.out.println("Sai chức năng, vui lòng nhập lại");
                    chon();
                    break;
            }

        } while (true);
    }

    public static void exit() {
        System.out.println("\t\t\t\t\t\tCám ơn quý khách");
        System.out.println("\t\t\t\t\t\t ✌ Hẹn gặp lại ✌");

        System.exit(0);
    }

    public static void menuMain() {
        System.out.println();
        System.out.println("* * * *   Giao diện   * * * * * *");
        System.out.println("*                               *");
        System.out.println("*       1.    Chủ               *");
        System.out.println("*       2.    Khách             *");
        System.out.println("*       3.    Thoát             *");
        System.out.println("*                               *");
        System.out.println("* * * * * * * * * * * * * * * * *");
    }
    public static void menuBoss() {
        System.out.println("===============   Giao diện chủ   ===============");
        System.out.println("|                                               |");
        System.out.println("|      1. Hiển thị danh sách sản phẩm           |");
        System.out.println("|      2. Thêm sản phẩm vào danh sách           |");
        System.out.println("|      3. Sửa thông tin sản phẩm                |");
        System.out.println("|      4. Tìm kiếm sản phẩm theo tên            |");
        System.out.println("|      5. Xem tổng doanh thu                    |");
        System.out.println("|      6. Xoá                                   |");
        System.out.println("|      7. Quay lại                              |");
        System.out.println("|      8. Thoát                                 |");
        System.out.println("=================================================");
    }

    public static void menuGuest() {
        System.out.println("===============   Giao diện Khách  ===============");
        System.out.println("|                                                |");
        System.out.println("|        1. Hiển thị danh sách sản phẩm          |");
        System.out.println("|        2. Tìm kiếm sản phẩm theo tên           |");
        System.out.println("|        3. Sắp xếp sản phẩm theo giá tăng dần   |");
        System.out.println("|        4. Sắp xếp sản phẩm theo giá giảm dần   |");
        System.out.println("|        5. Mua sản phẩm                         |");
        System.out.println("|        6. Quay lại                             |");
        System.out.println("|        7. Thoát                                |");
        System.out.println("|                                                |");
        System.out.println("==================================================");
    }
}
