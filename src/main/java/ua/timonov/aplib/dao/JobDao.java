package ua.timonov.aplib.dao;

import ua.timonov.aplib.model.Job;
import ua.timonov.aplib.model.Position;

import java.util.List;

/**
 * DAO interface for Job
 */
public interface JobDao {
    Job getJobByPosition(String position);
    Position getPositionById(int id);
    List<String> getAllPositions();
}