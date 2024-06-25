package uz.pdp.studycenterspring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.studycenterspring.entity.Group;
import uz.pdp.studycenterspring.repo.GroupRepo;
import uz.pdp.studycenterspring.services.interfaces.GroupService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImplement implements GroupService {
    private final GroupRepo groupRepo;

    @Override
    public List<Group> findAll() {
        return groupRepo.findAll();
    }

    @Override
    public void save(Group group) {
        groupRepo.save(group);
    }

    public void deleteGroupByName(String name) {
        Group group = groupRepo.findByName(name);
        if (group != null) {
            groupRepo.delete(group);
        }
    }
}
