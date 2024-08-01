package uz.pdp.studycenterspring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.studycenterspring.entity.Student;
import uz.pdp.studycenterspring.entity.TimeTableStudent;

public interface TimeTableStudentRepo extends JpaRepository<TimeTableStudent,Integer> {
}
