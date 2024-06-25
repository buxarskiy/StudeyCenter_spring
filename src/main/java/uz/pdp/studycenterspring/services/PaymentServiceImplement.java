package uz.pdp.studycenterspring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.studycenterspring.entity.Group;
import uz.pdp.studycenterspring.entity.Payment;
import uz.pdp.studycenterspring.repo.GroupRepo;
import uz.pdp.studycenterspring.repo.PaymentRepo;
import uz.pdp.studycenterspring.services.interfaces.GroupService;
import uz.pdp.studycenterspring.services.interfaces.PaymentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImplement implements PaymentService {
    private final PaymentRepo paymentRepo;

    @Override
    public List<Payment> findAll() {
        return paymentRepo.findAll();
    }
}
