package uz.pdp.studycenterspring.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.studycenterspring.entity.Group;
import uz.pdp.studycenterspring.entity.Payment;
import uz.pdp.studycenterspring.entity.Student;
import uz.pdp.studycenterspring.entity.enums.PayType;
import uz.pdp.studycenterspring.repo.GroupRepo;
import uz.pdp.studycenterspring.repo.PaymentRepo;
import uz.pdp.studycenterspring.repo.StudentRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentRepo paymentRepo;
    private final StudentRepo studentRepo;
    private final HttpSession httpSession;

    @GetMapping()
    private String showGroupPage(Model model) {
        List<Payment> payments = paymentRepo.findAll();
        model.addAttribute("payments", payments);
        return "payments";
    }

    @GetMapping("/addPaymentPage/{id}")
    private String addPaymentPage(@PathVariable Integer id) {
        Optional<Student> optionalStudent = studentRepo.findById(id);
        if (optionalStudent.isPresent()) {
            httpSession.setAttribute("currentStudent", optionalStudent.get());
        }
        return "addPayment";
    }

    @PostMapping("/addPayment")
    public String addPayment(@RequestParam Integer amount, @RequestParam String payType, Model model) {
        Student currentStudent = (Student) httpSession.getAttribute("currentStudent");
        Optional<Student> studentOptional = studentRepo.findById(currentStudent.getId());
        studentOptional.get().setBalance(studentOptional.get().getBalance() - amount);
        studentRepo.save(studentOptional.get());
        Payment payment = Payment.builder()
                .amount(amount)
                .payType(PayType.valueOf(payType))
                .student((Student) httpSession.getAttribute("currentStudent"))
                .dateTime(LocalDateTime.now())
                .build();
        paymentRepo.save(payment);
        return "redirect:/students";
    }

    @PostMapping("/search")
    private String searchPayment(HttpServletRequest req, Model model) {
        String from = req.getParameter("from");
        String to = req.getParameter("to");

        LocalDate parseFrom = LocalDate.parse(from);
        LocalDate parseTo = LocalDate.parse(to);

        LocalDateTime formDateTime = parseFrom.atStartOfDay();
        LocalDateTime toDateTime = parseTo.atStartOfDay();
        List<Payment> payments = paymentRepo.findBetWeenDates(formDateTime, toDateTime);
        model.addAttribute("payments", payments);
        return "payments";
    }
}
