package ua.timonov.aplib.service;

import ua.timonov.aplib.dao.JobDao;
import ua.timonov.aplib.model.Job;
import ua.timonov.aplib.model.Position;

/**
 * Service for JobDao
 */
public class JobService {
    private JobDao jobDao;

    public void setJobDao(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    public Job getJobByPosition(String position) {
        return jobDao.getJobByPosition(position);
    }

    public Position getPositionById(int id) {
        return jobDao.getPositionById(id);
    }
}
