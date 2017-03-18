package ua.timonov.aplib.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.dao.BookInClassDao;
import ua.timonov.aplib.dto.BookInClassDto;
import ua.timonov.aplib.dto.SchoolClassDto;
import ua.timonov.aplib.dto.SchoolbookDto;

import java.util.List;

/**
 *
 */
public class HibernateBookInClassDao implements BookInClassDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<BookInClassDto> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select bookInClass from BookInClassDto bookInClass").list();
    }

    @Override
    public List<BookInClassDto> getByClass(SchoolClassDto schoolClassDto) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select bookInClass from BookInClassDto bookInClass where bookInClass.class_id = :param");
        query.setParameter("param", schoolClassDto.getId());
        return query.getResultList();
    }

    @Override
    public List<BookInClassDto> getByBook(SchoolbookDto schoolbookDto) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select bookInClass from BookInClassDto bookInClass where bookInClass.book_id = :param");
        query.setParameter("param", schoolbookDto.getId());
        return query.getResultList();
    }
}
