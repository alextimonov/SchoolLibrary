package ua.timonov.aplib.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.dao.EmployeeDao;
import ua.timonov.aplib.dao.SchoolClassDao;
import ua.timonov.aplib.dao.SchoolbookDao;
import ua.timonov.aplib.dto.EmployeeDto;
import ua.timonov.aplib.dto.SchoolClassDto;
import ua.timonov.aplib.dto.SchoolbookDto;
import ua.timonov.aplib.exceptions.ForbidToDeleteException;
import ua.timonov.aplib.exceptions.NoItemInDatabaseException;

import java.util.List;

/**
 * Hibernate implementation of EmployeeDao
 */
public class HibernateEmployeeDao implements EmployeeDao {
    private SessionFactory sessionFactory;
    private SchoolClassDao schoolClassDao;
    private SchoolbookDao schoolbookDao;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSchoolClassDao(SchoolClassDao schoolClassDao) {
        this.schoolClassDao = schoolClassDao;
    }

    public void setSchoolbookDao(SchoolbookDao schoolbookDao) {
        this.schoolbookDao = schoolbookDao;
    }

    @Override
    @Transactional
    public EmployeeDto add(EmployeeDto employee) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employee);
        return employee;
    }

    @Override
    @Transactional
    public EmployeeDto update(EmployeeDto employee) {
        Session session = sessionFactory.getCurrentSession();
        session.update(employee);
        return employee;
    }

    @Override
    @Transactional
    public EmployeeDto delete(int id) {
        EmployeeDto employeeDto = getEmployeeById(id);
        SchoolClassDto schoolClassDto = schoolClassDao.getSchoolClassByTeacherId(id);
        if (schoolClassDto != null) {
            throw new ForbidToDeleteException("Employee " + employeeDto.getName() + " " + employeeDto.getSurname() +
                    " cannot be deleted as he(she) is a curator of class " + schoolClassDto.getCourse() + "-" +
                    schoolClassDto.getLetter() + ". See details.");
        }
        List<SchoolbookDto> schoolbooks = schoolbookDao.getSchoolbooksByEmployeeId(id);
        if (schoolbooks.size() > 0) {
            throw new ForbidToDeleteException("Employee " + employeeDto.getName() + " " + employeeDto.getSurname() +
                    " cannot be deleted as he(she) is responsible librarian for some books. See details.");
        }
        sessionFactory.getCurrentSession().delete(employeeDto);
        return employeeDto;
    }

    @Override
    @Transactional
    public List<EmployeeDto> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select employee from EmployeeDto employee").list();
    }

    @Override
    @Transactional
    public EmployeeDto getEmployeeById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select employee from EmployeeDto employee where employee.id = :param");
        query.setParameter("param", id);
        EmployeeDto employeeDto = (EmployeeDto) query.uniqueResult();
        if (employeeDto != null)
            return employeeDto;
        else
            throw new NoItemInDatabaseException("There is no employee with id = " + id + " in database.");
    }

    @Override
    @Transactional
    public EmployeeDto getBySurname(String surname) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select employee from EmployeeDto employee where employee.surname like :param");
        query.setParameter("param", surname);
        return (EmployeeDto) query.uniqueResult();
    }
}
