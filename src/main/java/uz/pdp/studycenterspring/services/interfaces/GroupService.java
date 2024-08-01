package uz.pdp.studycenterspring.services.interfaces;

import org.springframework.stereotype.Service;
import uz.pdp.studycenterspring.entity.Group;

import java.util.List;


public interface GroupService {
    List<Group> findAll();

    void save(Group group);

    void deleteGroupByName(String name);

}
