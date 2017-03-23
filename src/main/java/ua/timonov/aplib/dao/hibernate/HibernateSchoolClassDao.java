package ua.timonov.aplib.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.timonov.aplib.dao.SchoolClassDao;
import ua.timonov.aplib.dto.EmployeeDto;
import ua.timonov.aplib.dto.SchoolClassDto;
import ua.timonov.aplib.dto.SchoolbookDto;
import ua.timonov.aplib.exceptions.ForbidToAddException;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 */
public class HibernateSchoolClassDao implements SchoolClassDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SchoolClassDto add(SchoolClassDto newSchoolClass) {
        EmployeeDto newTeacher = newSchoolClass.getTeacher();
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select schoolClass from SchoolClassDto schoolClass where schoolClass.teacher.id = :param");
        query.setParameter("param", newTeacher.getId());
        SchoolClassDto schoolClassDto = (SchoolClassDto) query.uniqueResult();
        if (schoolClassDto != null) {
            Response.status(Response.Status.FORBIDDEN);
            throw new ForbidToAddException("Teacher " + newTeacher.getName() + " " + newTeacher.getSurname() +
                    " is already curator of class " + schoolClassDto.getCourse() + "-" + schoolClassDto.getLetter());
        }
        else {
            session.save(newSchoolClass);
            return newSchoolClass;
        }
    }

    @Override
    public SchoolClassDto update(SchoolClassDto schoolClass) {
        sessionFactory.getCurrentSession().update(schoolClass);
        return schoolClass;
    }

    @Override
    public SchoolClassDto delete(int id) {
        SchoolClassDto schoolClass = getById(id);
        sessionFactory.getCurrentSession().delete(schoolClass);
        return schoolClass;
    }

    @Override
    public List<SchoolClassDto> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select schoolClass from SchoolClassDto schoolClass").list();
    }

    @Override
    public SchoolClassDto getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select schoolClass from SchoolClassDto schoolClass where schoolClass.id = :param");
        query.setParameter("param", id);
        return (SchoolClassDto) query.uniqueResult();
    }

    @Override
    public SchoolClassDto getByName(int course, char letter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select schoolClass from SchoolClassDto schoolClass where " +
                "schoolClass.course = :paramCourse and schoolClass.letter = :paramLetter");
        query.setParameter("paramCourse", course);
        query.setParameter("paramLetter", letter);
        return (SchoolClassDto) query.uniqueResult();
    }

    @Override
    public SchoolClassDto getSchoolClassByEmployee(EmployeeDto employeeDto) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select schoolClass from SchoolClassDto schoolClass where schoolClass.teacher.id = :param");
        query.setParameter("param", employeeDto.getId());
        return (SchoolClassDto) query.uniqueResult();
    }
}
