package Model;

import Utilities.CSVUtils;
import Utilities.CurrencyFormat;

import java.util.ArrayList;
import java.util.List;

public class Payment {

    private int Id;
    private String Name;
    private int Quantity;
    private String PhoneNumber;
    private String Address;
    private double Total;

    public Payment(){}
    public int getId(){
        return Id;
    }

    public Payment(int id, int quantity, double Total) {
        this.Id = id;
        this.Quantity = quantity;
        this.Total = Total;
    }


    public Payment(int id, String name, int quantity, String phoneNumber, String address, double total) {
        this.Id = id;
        this.Name = name;
        this.Quantity = quantity;
        this.PhoneNumber = phoneNumber;
        this.Address = address;
        this.Total = total;
    }

    public Payment(String rawPay) {
        String[] strings = rawPay.split(",");
        this.Id = Integer.parseInt(strings[0]);
        this.Name= strings[1];
        this.Quantity = Integer.parseInt(strings[2]);
        this.PhoneNumber= strings[3];
        this.Address = strings[4];
        this.Total= CurrencyFormat.parseDouble(strings[5]);
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    @Override
    public String toString() {
        //1,Quốc Pháp,2,0336056504,Huế,3200000.0
        return String.format("%s,%s,%s,%s,%s,%s", this.Id, this.Name, this.Quantity, this.PhoneNumber, this.Address, CurrencyFormat.parseInteger(this.Total));
    }



}
