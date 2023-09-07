package dao.custom.impl;

import dao.custom.UserDAO;
import entity.Student;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean check(String name, String password, String password1) {
        Session session= FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction= session.beginTransaction();

        List<User> userList=session.createNativeQuery("SELECT * FROM User WHERE userName =?",User.class).setParameter(1,name).getResultList();
        for (User user:userList) {
            transaction.commit();
            session.close();
            if (user.getUserPassword().equals(password) || user.getUserPassword().equals(password1)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean save(User user) {
        Session session=FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction= session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(User student) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public String generateNextId() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        System.out.println(session);
        Transaction transaction=session.beginTransaction();

        Query<String> query = session.createNamedQuery("User.findLatestUserId", String.class);
        query.setMaxResults(1);
        String latestUserId = query.uniqueResult();

        if (latestUserId != null){
            transaction.commit();
            session.close();

            int newUserID = Integer.parseInt(latestUserId.replace("U00-", "")) + 1;
            return String.format("U00-%03d",newUserID);
        }else {
            return "U00-001";
        }
    }

}
