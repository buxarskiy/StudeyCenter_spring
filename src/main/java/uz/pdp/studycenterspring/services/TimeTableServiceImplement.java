package uz.pdp.studycenterspring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.studycenterspring.entity.TimeTable;
import uz.pdp.studycenterspring.repo.TimeTableRepo;
import uz.pdp.studycenterspring.services.interfaces.TimeTableService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeTableServiceImplement implements TimeTableService {
    private final TimeTableRepo timeTableRepo;

    @Override
    public List<TimeTable> findAll() {
        return timeTableRepo.findAll();
    }
}
