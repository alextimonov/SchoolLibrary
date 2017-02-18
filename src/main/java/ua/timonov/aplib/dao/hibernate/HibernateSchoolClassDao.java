package ua.timonov.aplib.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.timonov.aplib.dao.SchoolClassDao;
import ua.timonov.aplib.model.SchoolClassDb;

import java.util.List;

/**
 * Created by Alex on 23.12.2016.
 */
public class HibernateSchoolClassDao implements SchoolClassDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SchoolClassDb add(SchoolClassDb schoolClass) {
        sessionFactory.getCurrentSession().save(schoolClass);
        return schoolClass;
    }

    @Override
    public SchoolClassDb update(SchoolClassDb schoolClass) {
        sessionFactory.getCurrentSession().update(schoolClass);
        return schoolClass;
    }

    @Override
    public SchoolClassDb delete(int id) {
        SchoolClassDb schoolClass = getById(id);
        sessionFactory.getCurrentSession().delete(schoolClass);
        return schoolClass;
    }

    @Override
    public List<SchoolClassDb> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select schoolClass from SchoolClass schoolClass").list();
    }

    @Override
    public SchoolClassDb getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select schoolClass from SchoolClass schoolClass where schoolClass.id = :param");
        query.setParameter("param", id);
        return (SchoolClassDb) query.uniqueResult();
    }

    @Override
    public SchoolClassDb getByName(int course, char letter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select schoolClass from SchoolClass schoolClass where " +
                "schoolClass.course = :paramCourse and schoolClass.letter = :paramLetter");
        query.setParameter("paramCourse", course);
        query.setParameter("paramLetter", letter);
        return (SchoolClassDb) query.uniqueResult();
    }
}
