package uz.pdp.studycenterspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.studycenterspring.entity.Group;
import uz.pdp.studycenterspring.repo.GroupRepo;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("groups")
@RequiredArgsConstructor
public class GroupsController {
    private final GroupRepo groupRepo;

    @GetMapping()
    private String showGroupPage(Model model) {
        List<Group> groups = groupRepo.findAll();
        model.addAttribute("groups", groups);
        return "groups";
    }

    @GetMapping("/addPage")
    public String addPage() {
        return "addGroup";
    }

    @PostMapping("/createGroup")
    public String save(@ModelAttribute Group group) {
        groupRepo.save(group);
        return "redirect:/";
    }


    @GetMapping("/editGroup/{id}")
    public String deletePage(@PathVariable Integer id, Model model) {
        Optional<Group> byId = groupRepo.findById(id);

        if (byId.isPresent()) {
            Group group = byId.get();
            model.addAttribute("group", group);
            return "editGroup";
        }
        return "redirect:/groups";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute Group group, @PathVariable Integer id) {
        group.setId(id);
        groupRepo.save(group);
        return "redirect:/groups";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        groupRepo.deleteById(id);
        return "redirect:/groups";
    }
}
