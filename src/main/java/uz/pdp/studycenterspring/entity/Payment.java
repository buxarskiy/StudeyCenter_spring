package uz.pdp.studycenterspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.studycenterspring.entity.enums.PayType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    private Student student;
    private Integer amount;
    private LocalDateTime dateTime;
    @Enumerated(EnumType.STRING)
    private PayType payType;
}
