package ua.timonov.aplib.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.timonov.aplib.dao.EmployeeDao;
import ua.timonov.aplib.model.Employee;

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
    public void add(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employee);
    }

    @Override
    public void update(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.update(employee);
    }

    @Override
    public void delete(int id) {
        Employee employee = getById(id);
        Session session = sessionFactory.getCurrentSession();
        session.delete(employee);
    }

    @Override
    public List<Employee> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select employee from Employee employee").list();
    }

    @Override
    public Employee getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select employee from Employee employee where employee.id = :param");
        query.setParameter("param", id);
        return (Employee) query.uniqueResult();
    }

    @Override
    public Employee getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select employee from Employee employee where employee.name like :param");
        query.setParameter("param", name);
        return (Employee) query.uniqueResult();
    }

    @Override
    public Employee getBySurname(String surname) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select employee from Employee employee where employee.surname like :param");
        query.setParameter("param", surname);
        return (Employee) query.uniqueResult();
    }
}
