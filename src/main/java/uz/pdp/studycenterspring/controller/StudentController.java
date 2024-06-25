package uz.pdp.studycenterspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.studycenterspring.entity.Group;
import uz.pdp.studycenterspring.entity.Student;
import uz.pdp.studycenterspring.repo.GroupRepo;
import uz.pdp.studycenterspring.repo.StudentRepo;
import uz.pdp.studycenterspring.services.interfaces.StudentService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepo studentRepo;

    @GetMapping()
    private String showGroupPage(Model model) {
        List<Student> students = studentRepo.findAll();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/infoPage/{id}")
    private String infoPage(@PathVariable Integer id, Model model) {
        Optional<Student> byId = studentRepo.findById(id);
        if (byId.isPresent()) {
            Student student = byId.get();
            model.addAttribute("student", student);
            return "studentInfo";
        }
        return "redirect:/students";
    }

    @GetMapping("/search")
    public String searchStudents(@RequestParam("search") String search, Model model) {
        search = "%" + search + "%";
        List<Student> searchedStudents = studentRepo.findBySearch(search);
        model.addAttribute("students", searchedStudents);
        return "students";
    }


}
