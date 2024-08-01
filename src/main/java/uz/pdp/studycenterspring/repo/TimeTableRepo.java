package uz.pdp.studycenterspring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.studycenterspring.entity.Student;
import uz.pdp.studycenterspring.entity.TimeTable;
import uz.pdp.studycenterspring.projection.TimeTableData;

import java.util.List;

public interface TimeTableRepo extends JpaRepository<TimeTable, Integer> {

    List<TimeTable> findAllByGroupId(Integer groupId);

    @Query(value = """
            select s.first_name || ' ' || s.last_name as fullName, array_agg(sa.attendance order by sa.lesson_order) as attendances
            from time_table_student tts
                     join time_table tt on tts.time_table_id = tt.id
                     join student s on tts.student_id = s.id
                     join student_attendance sa on tts.id = sa.time_table_student_id
            where tt.id = :timeTableId
            group by s.id
                        """, nativeQuery = true)
    List<TimeTableData> getTimeTableData(Integer timeTableId);

}
