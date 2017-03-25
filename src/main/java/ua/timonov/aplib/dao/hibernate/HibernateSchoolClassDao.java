package ua.timonov.aplib.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.dao.SchoolClassDao;
import ua.timonov.aplib.dto.EmployeeDto;
import ua.timonov.aplib.dto.SchoolClassDto;
import ua.timonov.aplib.exceptions.ForbidToAddException;
import ua.timonov.aplib.exceptions.NoItemInDatabaseException;

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
    @Transactional
    public SchoolClassDto add(SchoolClassDto newSchoolClass) {
        EmployeeDto newTeacher = newSchoolClass.getTeacher();
        Session session = sessionFactory.getCurrentSession();
        SchoolClassDto schoolClassDto = findSameSchoolClass(newSchoolClass, session);
        if (schoolClassDto != null) {
            throw new ForbidToAddException("Class " + schoolClassDto.getCourse() + "-" + schoolClassDto.getLetter() +
                    " is already in school");
        }
        else {
            schoolClassDto = findSchoolClassWithSameTeacher(newTeacher, session);
            if (schoolClassDto != null) {
                throw new ForbidToAddException("Teacher " + newTeacher.getName() + " " + newTeacher.getSurname() +
                        " is already curator of class " + schoolClassDto.getCourse() + "-" + schoolClassDto.getLetter());
            }
            else {
                session.save(newSchoolClass);
                return newSchoolClass;
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public SchoolClassDto update(SchoolClassDto schoolClassEdited) {
        EmployeeDto newTeacher = schoolClassEdited.getTeacher();
        Session session = sessionFactory.getCurrentSession();
        SchoolClassDto sameSchoolClass = findSameSchoolClass(schoolClassEdited, session);
        SchoolClassDto schoolClassWithSameTeacher = findSchoolClassWithSameTeacher(newTeacher, session);
        if ((sameSchoolClass != null && schoolClassEdited.getId() != sameSchoolClass.getId()) ||
                (schoolClassWithSameTeacher != null && newTeacher.getId() != schoolClassWithSameTeacher.getTeacher().getId())) {
            throw new ForbidToAddException("Class is already in school or teacher is already curator of another class.");
        }
        else {
            session.update(schoolClassEdited);
            return schoolClassEdited;
        }
    }

    @Transactional
    private SchoolClassDto findSchoolClassWithSameTeacher(EmployeeDto newTeacher, Session session) {
        Query queryFindTeacher = session.createQuery("select schoolClass from SchoolClassDto schoolClass where schoolClass.teacher.id = :param");
        queryFindTeacher.setParameter("param", newTeacher.getId());
        return (SchoolClassDto) queryFindTeacher.uniqueResult();
    }

    @Transactional
    private SchoolClassDto findSameSchoolClass(SchoolClassDto newSchoolClass, Session session) {
        Query queryFindClass = session.createQuery("select schoolClass from SchoolClassDto schoolClass " +
                "where schoolClass.course = :course and schoolClass.letter = :letter");
        queryFindClass.setParameter("course", newSchoolClass.getCourse());
        queryFindClass.setParameter("letter", newSchoolClass.getLetter());
        return (SchoolClassDto) queryFindClass.uniqueResult();
    }

    @Override
    @Transactional
    public SchoolClassDto delete(int id) {
        SchoolClassDto schoolClass = getSchoolClassById(id);
        if (schoolClass != null) {
            sessionFactory.getCurrentSession().delete(schoolClass);
            return schoolClass;
        }
        else {
            throw new NoItemInDatabaseException("There is no class with id = " + id + " in database!");
        }
    }

    @Override
    @Transactional
    public List<SchoolClassDto> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select schoolClass from SchoolClassDto schoolClass").list();
    }

    @Override
    @Transactional
    public SchoolClassDto getSchoolClassById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select schoolClass from SchoolClassDto schoolClass where schoolClass.id = :param");
        query.setParameter("param", id);
        SchoolClassDto foundSchoolClassDto = (SchoolClassDto) query.uniqueResult();
        return foundSchoolClassDto;
    }

    @Override
    @Transactional
    public SchoolClassDto getSchoolClassByName(int course, char letter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select schoolClass from SchoolClassDto schoolClass where " +
                "schoolClass.course = :paramCourse and schoolClass.letter = :paramLetter");
        query.setParameter("paramCourse", course);
        query.setParameter("paramLetter", letter);
        return (SchoolClassDto) query.uniqueResult();
    }

    @Override
    @Transactional
    public SchoolClassDto getSchoolClassByEmployee(EmployeeDto employeeDto) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select schoolClass from SchoolClassDto schoolClass where schoolClass.teacher.id = :param");
        query.setParameter("param", employeeDto.getId());
        return (SchoolClassDto) query.uniqueResult();
    }
}
