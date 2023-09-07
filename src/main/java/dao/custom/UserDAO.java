package dao.custom;

import dao.CrudDAO;
import entity.User;

public interface UserDAO extends CrudDAO<User> {
    boolean check(String name, String password, String password1);

    boolean save(User user);

    String generateNextId();
}
