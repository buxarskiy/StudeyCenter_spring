package uz.pdp.studycenterspring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.studycenterspring.entity.Group;
import uz.pdp.studycenterspring.entity.Student;

public interface GroupRepo extends JpaRepository<Group,Integer> {
    Group findByName(String name);
}
