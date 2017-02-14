package ua.timonov.aplib.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.dao.JobDao;
import ua.timonov.aplib.model.Job;
import ua.timonov.aplib.model.Position;

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
    public Job getJobByPosition(String position) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select job from Job job where job.position = :param");
        query.setParameter("param", Position.byName(position.toUpperCase()));
        return (Job) query.uniqueResult();
    }

    @Override
    @Transactional
    public Position getPositionById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select job from Job job where job.id = :param");
        query.setParameter("param", id);
        Job job = (Job) query.uniqueResult();
        return job.getPosition();
    }

    @Override
    @Transactional
    public List<String> getAllPositions() {
        Session session = sessionFactory.getCurrentSession();
        List<Job> jobs = session.createQuery("select job from Job job").list();
        List<String> positions = new ArrayList<>();
        for (Job job : jobs) {
            positions.add(job.getPosition().toString().toLowerCase());
        }
        return positions;
    }
}
