package uz.pdp.studycenterspring.services.interfaces;

import uz.pdp.studycenterspring.entity.Group;
import uz.pdp.studycenterspring.entity.Payment;

import java.util.List;


public interface PaymentService {
    List<Payment> findAll();

}
