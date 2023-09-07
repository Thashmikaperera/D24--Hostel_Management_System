package dao.custom.impl;

import dao.SuperDAO;
import dao.custom.StudentDAO;
import entity.Student;
import entity.User;
import util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public String generateNextId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
//        System.out.println("hi");

        Query<String> query = session.createNamedQuery("Student.findLatestUserId",String.class);
        query.setMaxResults(1);
        String latestUserId = query.uniqueResult();

        if (latestUserId != null){
            transaction.commit();
            session.close();
            int newUserId = Integer.parseInt(latestUserId.replace("S00-",""))+1;
            return  String.format("S00-%03d",newUserId);
        }else {
            return "S00-001";
        }
    }

    @Override
    public boolean save(Student entity) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction= session.beginTransaction();

        session.persist(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction= session.beginTransaction();

        session.update(entity);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean delete(Student student) {
        Session session=FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction= session.beginTransaction();

        session.remove(student);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public List<Student> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();

        Transaction transaction = session.beginTransaction();

        List<Student> studentArrayList = session.createNativeQuery("SELECT * FROM Student").addEntity(Student.class).list();

        transaction.commit();
        session.close();
        return studentArrayList;
    }


}
