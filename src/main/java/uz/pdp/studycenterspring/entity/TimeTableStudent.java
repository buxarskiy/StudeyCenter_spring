package uz.pdp.studycenterspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class TimeTableStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private TimeTable timeTable;

    private Integer paid;

    @OneToMany(mappedBy = "timeTableStudent", fetch = FetchType.EAGER)
    private List<StudentAttendance> studentAttendances;

}
