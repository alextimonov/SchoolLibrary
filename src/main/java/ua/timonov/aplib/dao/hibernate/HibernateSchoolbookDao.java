package ua.timonov.aplib.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.timonov.aplib.dao.SchoolbookDao;
import ua.timonov.aplib.model.Schoolbook;

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
    public void add(Schoolbook schoolbook) {
        sessionFactory.getCurrentSession().save(schoolbook);
    }

    @Override
    public void update(Schoolbook schoolbook) {
        sessionFactory.getCurrentSession().update(schoolbook);
    }

    @Override
    public Schoolbook delete(int id) {
        Schoolbook schoolbook = getById(id);
        sessionFactory.getCurrentSession().delete(schoolbook);
        return schoolbook;
    }

    @Override
    public List<Schoolbook> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select book from Schoolbook book").list();
    }

    @Override
    public Schoolbook getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select book from Schoolbook book where book.id = :param");
        query.setParameter("param", id);
        return (Schoolbook) query.getSingleResult();
    }

    @Override
    public Schoolbook getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select book from Schoolbook book where book.id like :param");
        query.setParameter("param", name);
        return (Schoolbook) query.getSingleResult();
    }
}
