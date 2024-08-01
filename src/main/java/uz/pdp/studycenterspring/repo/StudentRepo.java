package uz.pdp.studycenterspring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.studycenterspring.entity.Student;
import uz.pdp.studycenterspring.entity.StudentAttendance;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Integer> {


    @Query(value = "select * from student s where s.first_name like :search or s.last_name like :search", nativeQuery = true)
    List<Student> findBySearch(@Param("search") String search);
}
