package dao.custom.impl;

import dao.custom.RoomDAO;
import entity.Room;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public String generateNextId() {
        return null;
    }

    @Override
    public boolean save(Room entity) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction= session.beginTransaction();

        session.persist(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room entity) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction= session.beginTransaction();

        session.update(entity);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean delete(Room student) {
        Session session=FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction= session.beginTransaction();

        session.remove(student);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public List<Room> getAll() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();

        Transaction transaction = session.beginTransaction();

        List<Room> roomArrayList = session.createNativeQuery("SELECT * FROM Room").addEntity(Room.class).list();

        transaction.commit();
        session.close();
        return roomArrayList;
    }
}
