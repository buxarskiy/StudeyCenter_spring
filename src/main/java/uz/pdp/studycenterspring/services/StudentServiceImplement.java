package uz.pdp.studycenterspring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.studycenterspring.entity.Student;
import uz.pdp.studycenterspring.repo.StudentRepo;
import uz.pdp.studycenterspring.services.interfaces.StudentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImplement implements StudentService {
    private final StudentRepo studentRepo;

    @Override
    public List<Student> findAll() {
        return studentRepo.findAll();
    }

}
