package Service;

import Model.Payment;

import java.util.List;

public interface IPaymentService {
    List<Payment> getPaymet();
    void add(List<Payment>list);
    void recheck(int id, Payment updatePayment);
}
