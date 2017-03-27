package ua.timonov.aplib.service;

import ua.timonov.aplib.dao.JobDao;
import ua.timonov.aplib.dto.JobDto;
import ua.timonov.aplib.dto.Position;

/**
 * Service for JobDao
 */
public class JobService {
    private JobDao jobDao;

    public void setJobDao(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    public JobDto getJobByPosition(String position) {
        return jobDao.getJobByPosition(position);
    }

    public Position getPositionById(int id) {
        return jobDao.getPositionById(id);
    }
}
