package ua.timonov.aplib.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.dao.SchoolbookDao;
import ua.timonov.aplib.model.SchoolbookDb;

import java.util.List;

/**
 * Hibernate implementation of SchoolbookDao
 */
public class HibernateSchoolbookDao implements SchoolbookDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public SchoolbookDb add(SchoolbookDb schoolbook) {
        sessionFactory.getCurrentSession().save(schoolbook);
        return schoolbook;
    }

    @Override
    @Transactional
    public SchoolbookDb update(SchoolbookDb schoolbook) {
        sessionFactory.getCurrentSession().update(schoolbook);
        return schoolbook;
    }

    @Override
    @Transactional
    public SchoolbookDb delete(int id) {
        SchoolbookDb schoolbook = getById(id);
        sessionFactory.getCurrentSession().delete(schoolbook);
        return schoolbook;
    }

    @Override
    @Transactional
    public List<SchoolbookDb> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select book from SchoolbookDb book").list();
    }

    @Override
    @Transactional
    public SchoolbookDb getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select book from SchoolbookDb book where book.id = :param");
        query.setParameter("param", id);
        return (SchoolbookDb) query.getSingleResult();
    }

    @Override
    @Transactional
    public SchoolbookDb getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select book from SchoolbookDb book where book.id like :param");
        query.setParameter("param", name);
        return (SchoolbookDb) query.getSingleResult();
    }
}
