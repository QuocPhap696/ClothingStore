package Views;

import Model.User;
import Model.Role;
import Model.User;
import Utilities.CSVUtils;

import java.util.List;
import java.util.Scanner;

public class Loginview {
    public User login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tên đăng nhập:");
        String username = scanner.nextLine();
        System.out.println("Nhập mật khẩu");
        String password = scanner.nextLine();
        Role role = authenticate(username, password);
        return new User(username, password, role);
    }

    private Role authenticate(String username, String password){
       try {
           List<String> lines = CSVUtils.read("/Users/QuocPhap/Desktop/ClothingStore/src/main/Data/Login.csv");
           for (String line : lines) {
               String[] fields = line.split(",");
               if (fields.length == 3 && fields[0].equals(username) &&fields[1].equals(password)){
                   return Role.fromValue(fields[2]);
               }
           }
           throw new IllegalArgumentException("Tên đăng nhập hoặc mật khẩu không đúng !");
       } catch (IllegalArgumentException e){
           throw e;
       } catch (Exception e) {
           throw new RuntimeException("Sai đường dẫn đến file CSV");
       }
       }

}
