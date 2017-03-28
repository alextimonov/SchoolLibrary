package ua.timonov.aplib.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.dao.BookInClassDao;
import ua.timonov.aplib.dao.SchoolbookDao;
import ua.timonov.aplib.dto.BookInClassDto;
import ua.timonov.aplib.dto.SchoolClassDto;
import ua.timonov.aplib.dto.SchoolbookDto;
import ua.timonov.aplib.exceptions.ForbidToAddException;
import ua.timonov.aplib.exceptions.ForbidToDeleteException;
import ua.timonov.aplib.exceptions.NoItemInDatabaseException;

import java.util.List;

/**
 * Hibernate implementation of SchoolbookDao
 */
public class HibernateSchoolbookDao implements SchoolbookDao {

    private SessionFactory sessionFactory;
    private BookInClassDao bookInClassDao;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setBookInClassDao(BookInClassDao bookInClassDao) {
        this.bookInClassDao = bookInClassDao;
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
        SchoolbookDto schoolbookDto = getById(id);
        List<BookInClassDto> booksInClass = bookInClassDao.getByBook(schoolbookDto);
        if (booksInClass.size() > 0) {
            StringBuilder sb = makeClassList(booksInClass);
            throw new ForbidToDeleteException("Schoolbook \"" + schoolbookDto.getName() + "\" for course " +
                    schoolbookDto.getCourse() + " cannot be deleted as it is being used in classes:" + sb + ". See details");
        }
        sessionFactory.getCurrentSession().delete(schoolbookDto);
        return schoolbookDto;
    }

    private StringBuilder makeClassList(List<BookInClassDto> booksInClass) {
        StringBuilder sb = new StringBuilder();
        for (BookInClassDto bookInClass : booksInClass) {
            sb.append(" ").append(bookInClass.getSchoolClass().getCourse()).append("-")
                    .append(bookInClass.getSchoolClass().getLetter()).append(",");
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb;
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
        SchoolbookDto schoolbookDto = (SchoolbookDto) query.uniqueResult();
        if (schoolbookDto != null)
            return schoolbookDto;
        else
            throw new NoItemInDatabaseException("There is no schoolbook with id = " + id + " in database.");
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
    @Transactional
    public List<SchoolbookDto> getSchoolbooksByEmployeeId(int librarianId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select schoolbook from SchoolbookDto schoolbook where schoolbook.librarian.id = :param");
        query.setParameter("param", librarianId);
        return query.getResultList();
    }

    @Override
    @Transactional
    public BookInClassDto handoutSchoolbooks(SchoolClassDto schoolClassDto, SchoolbookDto schoolbookDto, int amountToHandout) {
        List<BookInClassDto> booksInClassDto = bookInClassDao.getByBook(schoolbookDto);
        int residue = getBookResidue(schoolbookDto, booksInClassDto);
        // TODO move to BookInClassDao
        if (residue >= amountToHandout)
            return bookInClassDao.handoutSchoolbooks(schoolClassDto, schoolbookDto, amountToHandout);
        else
            throw new ForbidToAddException("There are not enough books \"" + schoolbookDto.getName() + "\" in the library. " +
                    residue + " books are available, while you need " + amountToHandout + ". Do you agree to hand out only " +
                    residue + " books to " + schoolClassDto.getCourse() + "-" + schoolClassDto.getLetter() + " class?");
    }

    @Override
    @Transactional
    public BookInClassDto collectSchoolbooks(SchoolClassDto schoolClassDto, SchoolbookDto schoolbookDto, int amountToCollect) {
        BookInClassDto bookInClassDto = bookInClassDao.getByClassAndBook(schoolClassDto, schoolbookDto);
        int currentAmount = bookInClassDto.getBooksNumber();
        if (amountToCollect <= currentAmount)
            return bookInClassDao.collectSchoolbooks(schoolClassDto, schoolbookDto, amountToCollect);
        else
            throw new ForbidToAddException(schoolClassDto.getCourse() + "-" + schoolClassDto.getLetter() + " class has only " +
                    amountToCollect + " books \"" + schoolbookDto.getName() + "\". Do you agree to return " +
                    currentAmount + " books to library?");
    }

    private int getBookResidue(SchoolbookDto schoolbook, List<BookInClassDto> booksInClass) {
        int amountTotal = schoolbook.getAmountTotal();
        int amountInClasses = 0;
        for (BookInClassDto bookInClass : booksInClass) {
            amountInClasses += bookInClass.getBooksNumber();
        }
        return amountTotal - amountInClasses;
    }
}
