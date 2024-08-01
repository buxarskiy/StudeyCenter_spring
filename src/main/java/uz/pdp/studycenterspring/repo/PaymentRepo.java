package uz.pdp.studycenterspring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.studycenterspring.entity.Payment;
import uz.pdp.studycenterspring.entity.Student;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {

    @Query(value = "select * from payment where date_time >= :formDateTime and date_time <= :toDateTime", nativeQuery = true)
    List<Payment> findBetWeenDates(LocalDateTime formDateTime, LocalDateTime toDateTime);
}
