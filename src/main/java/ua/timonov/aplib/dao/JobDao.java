package ua.timonov.aplib.dao;

import ua.timonov.aplib.dto.JobDto;
import ua.timonov.aplib.dto.Position;

import java.util.List;

/**
 * DAO interface for Job
 */
public interface JobDao {
    JobDto getJobByPosition(String position);
    Position getPositionById(int id);
    List<String> getAllPositions();
}