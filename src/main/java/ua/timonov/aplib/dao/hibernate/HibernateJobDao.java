package ua.timonov.aplib.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.dao.JobDao;
import ua.timonov.aplib.dto.JobDto;
import ua.timonov.aplib.dto.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Hibernate implementation of JobDao
 */
public class HibernateJobDao implements JobDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    @Deprecated
    public JobDto getJobByPosition(String positionName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select job from JobDto job where job.position like :param");
        Position positionByName = Position.byName(positionName.toUpperCase());
        query.setParameter("param", positionByName);
        return (JobDto) query.uniqueResult();
    }

    @Override
    @Transactional
    public Position getPositionById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select job from JobDto job where job.id = :param");
        query.setParameter("param", id);
        JobDto job = (JobDto) query.uniqueResult();
        return job.getPosition();
    }

    @Override
    @Transactional
    public List<String> getAllPositions() {
        Session session = sessionFactory.getCurrentSession();
        List<JobDto> jobs = session.createQuery("select job from JobDto job").list();
        List<String> positions = new ArrayList<>();
        for (JobDto job : jobs) {
            positions.add(job.getPosition().toString().toLowerCase());
        }
        return positions;
    }
}
