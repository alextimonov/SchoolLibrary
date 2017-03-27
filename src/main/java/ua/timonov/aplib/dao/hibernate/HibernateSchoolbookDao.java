package ua.timonov.aplib.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.dao.SchoolbookDao;
import ua.timonov.aplib.dto.SchoolClassDto;
import ua.timonov.aplib.dto.SchoolbookDto;

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
    public SchoolbookDto add(SchoolbookDto schoolbook) {
        sessionFactory.getCurrentSession().save(schoolbook);
        return schoolbook;
    }

    @Override
    @Transactional
    public SchoolbookDto update(SchoolbookDto schoolbook) {
        sessionFactory.getCurrentSession().update(schoolbook);
        return schoolbook;
    }

    @Override
    @Transactional
    public SchoolbookDto delete(int id) {
        SchoolbookDto schoolbook = getById(id);
        sessionFactory.getCurrentSession().delete(schoolbook);
        return schoolbook;
    }

    @Override
    @Transactional
    public List<SchoolbookDto> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select book from SchoolbookDto book").list();
    }

    @Override
    @Transactional
    public SchoolbookDto getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select book from SchoolbookDto book where book.id = :param");
        query.setParameter("param", id);
        return (SchoolbookDto) query.getSingleResult();
    }

    @Override
    @Transactional
    public SchoolbookDto getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select book from SchoolbookDto book where book.id like :param");
        query.setParameter("param", name);
        return (SchoolbookDto) query.getSingleResult();
    }

    @Override
    public List<SchoolbookDto> getSchoolbooksByEmployeeId(int librarianId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select schoolbook from SchoolbookDto schoolbook where schoolbook.librarian.id = :param");
        query.setParameter("param", librarianId);
        return query.getResultList();
    }
}
