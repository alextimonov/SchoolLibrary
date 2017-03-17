package ua.timonov.aplib.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.dao.EmployeeDao;
import ua.timonov.aplib.model.EmployeeDb;

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
    public EmployeeDb add(EmployeeDb employee) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employee);
        return employee;
    }

    @Override
    @Transactional
    public EmployeeDb update(EmployeeDb employee) {
        Session session = sessionFactory.getCurrentSession();
        session.update(employee);
        return employee;
    }

    @Override
    @Transactional
    public EmployeeDb delete(int id) {
        EmployeeDb employee = getById(id);
        Session session = sessionFactory.getCurrentSession();
        session.delete(employee);
        return employee;
    }

    @Override
    @Transactional
    public List<EmployeeDb> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select employee from EmployeeDb employee").list();
    }

    @Override
    @Transactional
    public EmployeeDb getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select employee from EmployeeDb employee where employee.id = :param");
        query.setParameter("param", id);
        EmployeeDb employeeDb = (EmployeeDb) query.uniqueResult();
        return employeeDb;
    }

    @Override
    @Transactional
    public EmployeeDb getBySurname(String surname) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select employee from EmployeeDb employee where employee.surname like :param");
        query.setParameter("param", surname);
        return (EmployeeDb) query.uniqueResult();
    }
}
