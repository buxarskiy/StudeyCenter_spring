package uz.pdp.studycenterspring.services.interfaces;

import uz.pdp.studycenterspring.entity.TimeTable;

import java.util.List;

public interface TimeTableService {
    List<TimeTable> findAll();
}
