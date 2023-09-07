package bo.custom.impl;

import bo.custom.UserBO;
import dao.DAOFactory;
import dao.custom.UserDAO;
import dto.UserDTO;
import entity.User;

public class UserBOImpl implements UserBO {
    UserDAO userDAO=(UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean checkUser(String name, String password, String password1) {
        return userDAO.check(name,password,password1);
    }

    @Override
    public boolean saveUser(UserDTO userDTO) {
        return userDAO.save(new User(userDTO.getUserId(), userDTO.getUserName(), userDTO.getUserPassword(), userDTO.getUserPasswordHint(),  userDTO.getUserEmail()));
    }

    @Override
    public String generateNextUserId() {
        return userDAO.generateNextId();
    }
}
