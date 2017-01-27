package ua.timonov.aplib.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.timonov.aplib.dao.SchoolClassDao;
import ua.timonov.aplib.model.SchoolClass;

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
    public SchoolClass add(SchoolClass schoolClass) {
        sessionFactory.getCurrentSession().save(schoolClass);
        return schoolClass;
    }

    @Override
    public SchoolClass update(SchoolClass schoolClass) {
        sessionFactory.getCurrentSession().update(schoolClass);
        return schoolClass;
    }

    @Override
    public SchoolClass delete(int id) {
        SchoolClass schoolClass = getById(id);
        sessionFactory.getCurrentSession().delete(schoolClass);
        return schoolClass;
    }

    @Override
    public List<SchoolClass> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select schoolClass from SchoolClass schoolClass").list();
    }

    @Override
    public SchoolClass getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select schoolClass from SchoolClass schoolClass where schoolClass.id = :param");
        query.setParameter("param", id);
        return (SchoolClass) query.uniqueResult();
    }

    @Override
    public SchoolClass getByName(int course, char letter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select schoolClass from SchoolClass schoolClass where " +
                "schoolClass.course = :paramCourse and schoolClass.letter = :paramLetter");
        query.setParameter("paramCourse", course);
        query.setParameter("paramLetter", letter);
        return (SchoolClass) query.uniqueResult();
    }
}
