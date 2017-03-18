package ua.timonov.aplib.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.dao.EmployeeDao;
import ua.timonov.aplib.dto.EmployeeDto;

import java.util.List;

/**
 * Hibernate implementation of EmployeeDao
 */
public class HibernateEmployeeDao implements EmployeeDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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
        EmployeeDto employee = getById(id);
        Session session = sessionFactory.getCurrentSession();
        session.delete(employee);
        return employee;
    }

    @Override
    @Transactional
    public List<EmployeeDto> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select employee from EmployeeDto employee").list();
    }

    @Override
    @Transactional
    public EmployeeDto getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select employee from EmployeeDto employee where employee.id = :param");
        query.setParameter("param", id);
        EmployeeDto employeeDb = (EmployeeDto) query.uniqueResult();
        return employeeDb;
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
