package ua.timonov.aplib.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.dao.BookInClassDao;
import ua.timonov.aplib.dto.BookInClassDto;
import ua.timonov.aplib.dto.SchoolClassDto;
import ua.timonov.aplib.dto.SchoolbookDto;
import ua.timonov.aplib.exceptions.ForbidToAddException;
import ua.timonov.aplib.exceptions.ForbidToDeleteException;

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
        query.setParameter("schoolClass", schoolClassDto.getId());
        return (BookInClassDto) query.uniqueResult();
    }

    @Override
    @Transactional
    public BookInClassDto handoutSchoolbooks(SchoolClassDto schoolClassDto, SchoolbookDto schoolbookDto, int amountToHandout) {
        List<BookInClassDto> booksInClassDto = getByBook(schoolbookDto);
        int residue = getBookResidue(schoolbookDto, booksInClassDto);
        if (residue >= amountToHandout)
            return handoutChosenBooksAmount(schoolClassDto, schoolbookDto, amountToHandout);
        else
            throw new ForbidToAddException("There are not enough books \"" + schoolbookDto.getName() + "\" in the library. " +
                    residue + " books are available, while you need " + amountToHandout + ". Do you agree to hand out only " +
                    residue + " books to " + schoolClassDto.getCourse() + "-" + schoolClassDto.getLetter() + " class?");
    }

    @Transactional
    private BookInClassDto handoutChosenBooksAmount(SchoolClassDto schoolClassDto, SchoolbookDto schoolbookDto, int amountToHandout) {
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
    public BookInClassDto returnSchoolbooks(SchoolClassDto schoolClassDto, SchoolbookDto schoolbookDto, int amountToCollect) {
        BookInClassDto bookInClassDto = getByClassAndBook(schoolClassDto, schoolbookDto);
        int currentAmount = bookInClassDto.getBooksNumber();
        if (amountToCollect <= currentAmount)
            return returnChosenBooksAmount(schoolClassDto, schoolbookDto, amountToCollect);
        else
            throw new ForbidToAddException(schoolClassDto.getCourse() + "-" + schoolClassDto.getLetter() + " class has only " +
                    currentAmount + " books while you try to return " + amountToCollect + " books \"" + schoolbookDto.getName() +
                    "\". Do you agree to return " + currentAmount + " books to library?");
    }

    @Transactional
    private BookInClassDto returnChosenBooksAmount(SchoolClassDto schoolClassDto, SchoolbookDto schoolbookDto, int amountToReturn) {
        Session session = sessionFactory.getCurrentSession();
        BookInClassDto bookInClassDto = getByClassAndBook(schoolClassDto, schoolbookDto);
        if (bookInClassDto != null) {
            int currentAmount = bookInClassDto.getBooksNumber();
            bookInClassDto.setBooksNumber(currentAmount - amountToReturn);
            session.update(bookInClassDto);
        }
        else {
            bookInClassDto = new BookInClassDto(schoolClassDto, schoolbookDto, amountToReturn);
            session.save(bookInClassDto);
        }
        return bookInClassDto;
    }

    private int getBookResidue(SchoolbookDto schoolbook, List<BookInClassDto> booksInClass) {
        int amountTotal = schoolbook.getAmountTotal();
        int amountInClasses = 0;
        for (BookInClassDto bookInClass : booksInClass) {
            amountInClasses += bookInClass.getBooksNumber();
        }
        return amountTotal - amountInClasses;
    }

    @Override
    @Transactional
    public BookInClassDto deleteBookInClass(int bookInClassId) {
        Session session = sessionFactory.getCurrentSession();
        BookInClassDto bookInClassDto = getById(bookInClassId);
        if (bookInClassDto == null) {
            throw new ForbidToDeleteException("There's no record with id = " + bookInClassId);
        }
        if (bookInClassDto.getBooksNumber() > 0) {
            throw new ForbidToDeleteException("Class " + schoolClassDto.getCourse() + "-" + schoolClassDto.getLetter() +
                    " has " + bookInClassDto.getBooksNumber() + " books " + schoolbookDto.getName() +
                    " Record cannot be deleted!");
        }
        session.delete(getByClassAndBook(schoolClassDto, schoolbookDto));
        return bookInClassDto;
    }

    private BookInClassDto getById(int bookInClassId) {
        //TODO
        return null;
    }
}
