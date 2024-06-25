package uz.pdp.studycenterspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.studycenterspring.entity.Group;
import uz.pdp.studycenterspring.entity.TimeTable;
import uz.pdp.studycenterspring.projection.TimeTableData;
import uz.pdp.studycenterspring.repo.GroupRepo;
import uz.pdp.studycenterspring.repo.TimeTableRepo;
import uz.pdp.studycenterspring.services.interfaces.GroupService;
import uz.pdp.studycenterspring.services.interfaces.TimeTableService;

import java.util.List;

@Controller
@RequestMapping("timetable")
@RequiredArgsConstructor
public class TimeTableController {
    private final TimeTableRepo timeTableRepo;
    private final GroupRepo groupRepo;

    @GetMapping
    public String timeTableGet(
            Model model,
            @RequestParam(required = false) Integer groupId,
            @RequestParam(required = false) Integer timeTableId
    ) {
        List<Group> groups = groupRepo.findAll();
        model.addAttribute("groups", groups);

        if (groupId != null) {
            model.addAttribute("groupId", groupId);
            List<TimeTable> timeTables = timeTableRepo.findAllByGroupId(groupId);
            model.addAttribute("timeTables", timeTables);
        }
        if (timeTableId != null) {
            model.addAttribute("timeTableId", timeTableId);
            List<TimeTableData> timeTableData = timeTableRepo.getTimeTableData(timeTableId);
            model.addAttribute("timeTableData", timeTableData);
        }
        return "timetable";
    }

}
