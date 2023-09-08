package bo.custom.impl;

import bo.custom.UserBO;
import dao.DAOFactory;
import dao.custom.UserDAO;
import dto.StudentDTO;
import dto.UserDTO;
import entity.Student;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO=(UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean checkUser(String name, String password, String password1) {
        return userDAO.check(name,password,password1);
    }

    @Override
    public boolean saveUser(UserDTO userDTO) {
        return userDAO.save(new User(userDTO.getUserId(), userDTO.getUserName(), userDTO.getAddress(), userDTO.getUserPassword(), userDTO.getUserPasswordHint(),  userDTO.getUserEmail()));
    }

    @Override
    public String generateNextUserId() {
        return userDAO.generateNextId();
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOArrayList = new ArrayList<>();
        List<User> userArrayList = userDAO.getAll();
        for (User user:userArrayList) {
            userDTOArrayList.add(new UserDTO(user.getUserId(),user.getUserName(), user.getAddress(),user.getUserEmail(),user.getUserPassword(),user.getUserPasswordHint()));
        }
        return userDTOArrayList;
    }
}
