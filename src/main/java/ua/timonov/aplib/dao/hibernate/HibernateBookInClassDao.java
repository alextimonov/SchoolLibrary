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
    @Transactional
    public List<BookInClassDto> getByClass(SchoolClassDto schoolClassDto) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select bookInClass from BookInClassDto bookInClass where bookInClass.schoolClass.id = :param");
        query.setParameter("param", schoolClassDto.getId());
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<BookInClassDto> getByBook(SchoolbookDto schoolbookDto) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select bookInClass from BookInClassDto bookInClass where bookInClass.schoolbook.id = :param");
        query.setParameter("param", schoolbookDto.getId());
        return query.getResultList();
    }

    @Override
    @Transactional
    public BookInClassDto getByClassAndBook(SchoolClassDto schoolClassDto, SchoolbookDto schoolbookDto) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select bookInClass from BookInClassDto bookInClass where bookInClass.schoolbook.id = :book and " +
                "bookInClass.schoolClass.id = :schoolClass");
        query.setParameter("book", schoolbookDto.getId());
        query.setParameter("schoolClass", schoolbookDto.getId());
        return (BookInClassDto) query.uniqueResult();
    }

    @Override
    @Transactional
    public BookInClassDto handoutSchoolbooks(SchoolClassDto schoolClassDto, SchoolbookDto schoolbookDto, int amountToHandout) {
        Session session = sessionFactory.getCurrentSession();
        BookInClassDto bookInClassDto = getByClassAndBook(schoolClassDto, schoolbookDto);
        if (bookInClassDto != null) {
            int currentAmount = bookInClassDto.getBooksNumber();
            bookInClassDto.setBooksNumber(currentAmount + amountToHandout);
            session.update(bookInClassDto);
        }
        else {
            bookInClassDto = new BookInClassDto(schoolClassDto, schoolbookDto, amountToHandout);
            session.save(bookInClassDto);
        }
        return bookInClassDto;
    }

    @Override
    @Transactional
    public BookInClassDto collectSchoolbooks(SchoolClassDto schoolClassDto, SchoolbookDto schoolbookDto, int amountToCollect) {
        Session session = sessionFactory.getCurrentSession();
        BookInClassDto bookInClassDto = getByClassAndBook(schoolClassDto, schoolbookDto);
        if (bookInClassDto != null) {
            int currentAmount = bookInClassDto.getBooksNumber();
            bookInClassDto.setBooksNumber(currentAmount - amountToCollect);
            session.update(bookInClassDto);
        }
        else {
            bookInClassDto = new BookInClassDto(schoolClassDto, schoolbookDto, amountToCollect);
            session.save(bookInClassDto);
        }
        return bookInClassDto;
    }
}
