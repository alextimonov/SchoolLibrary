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
    public void add(SchoolClassDao schoolClass) {
        sessionFactory.getCurrentSession().save(schoolClass);
    }

    @Override
    public void update(SchoolClassDao schoolClass) {
        sessionFactory.getCurrentSession().update(schoolClass);
    }

    @Override
    public void delete(int id) {
        SchoolClass schoolClass = getById(id);
        sessionFactory.getCurrentSession().delete(schoolClass);
    }

    @Override
    public List<SchoolClassDao> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select class from SchoolClass class").list();
    }

    @Override
    public SchoolClass getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select class from SchoolClass class where class.id = :param");
        query.setParameter("param", id);
        return (SchoolClass) query.uniqueResult();
    }

    @Override
    public SchoolClass getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select class from SchoolClass class where class.name = :param");
        query.setParameter("param", name);
        return (SchoolClass) query.uniqueResult();
    }
}
