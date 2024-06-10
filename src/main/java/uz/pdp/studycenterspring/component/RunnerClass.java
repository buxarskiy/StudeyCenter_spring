package uz.pdp.studycenterspring.component;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.studycenterspring.entity.*;
import uz.pdp.studycenterspring.repo.*;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RunnerClass implements CommandLineRunner {

    private final StudentRepo studentRepo;
    private final GroupRepo groupRepo;
    private final TimeTableRepo timeTableRepo;
    private final PaymentRepo paymentRepo;
    private final TimeTableStudentRepo timeTableStudentRepo;
    private final StudentAttendanceRepo studentAttendanceRepo;

    @Override
    public void run(String... args) throws Exception {

//        generatedata();
    }

    private void generatedata() {
        Group group1 = Group.builder().name("G35").build();
        Group group2 = Group.builder().name("F90").build();
        Group group3 = Group.builder().name("F91").build();
        Group group4 = Group.builder().name("G36").build();
        groupRepo.save(group1);
        groupRepo.save(group2);
        groupRepo.save(group3);
        groupRepo.save(group4);

        List<Student> students = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            Student student = Student.builder()
                    .firstName("studentF" + i)
                    .lastName("studentL" + i)
                    .balance(500000 + i)
                    .build();
            studentRepo.save(student);
            students.add(student);
        }


        TimeTable timeTable1 = TimeTable.builder().name("TimeTale-1").group(group1).currentLesson(1).build();
        TimeTable timeTable2 = TimeTable.builder().name("TimeTale-2").group(group2).currentLesson(1).build();
        TimeTable timeTable3 = TimeTable.builder().name("TimeTale-3").group(group3).currentLesson(1).build();
        TimeTable timeTable4 = TimeTable.builder().name("TimeTale-4").group(group4).currentLesson(1).build();
        timeTableRepo.save(timeTable1);
        timeTableRepo.save(timeTable2);
        timeTableRepo.save(timeTable3);
        timeTableRepo.save(timeTable4);


        List<TimeTableStudent> timeTableStudents = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TimeTableStudent timeTableStudent = TimeTableStudent.builder()
                    .timeTable(timeTable1)
                    .student(students.get(i))
                    .paid(i)
                    .build();
            timeTableStudentRepo.save(timeTableStudent);
            timeTableStudents.add(timeTableStudent);
        }
        for (int i = 5; i < 10; i++) {
            TimeTableStudent timeTableStudent = TimeTableStudent.builder()
                    .timeTable(timeTable2).
                    student(students.get(i))
                    .paid(i)
                    .build();
            timeTableStudentRepo.save(timeTableStudent);
            timeTableStudents.add(timeTableStudent);
        }
        for (int i = 10; i < 15; i++) {
            TimeTableStudent timeTableStudent = TimeTableStudent.builder()
                    .timeTable(timeTable3).
                    student(students.get(i))
                    .paid(i)
                    .build();
            timeTableStudentRepo.save(timeTableStudent);
            timeTableStudents.add(timeTableStudent);
        }

        for (TimeTableStudent timeTableStudent : timeTableStudents) {
            List<StudentAttendance> studentAttendances = generate12Lesson(timeTableStudent);
            studentAttendanceRepo.saveAll(studentAttendances);
        }
    }

    private List<StudentAttendance> generate12Lesson(TimeTableStudent timeTableStudent) {
        List<StudentAttendance> studentAttendances = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            studentAttendances.add(
                    StudentAttendance.builder()
                            .lessonOrder(i)
                            .attendance(false)
                            .timeTableStudent(timeTableStudent)
                            .build());
        }
        return studentAttendances;
    }
}
