package bo.custom;

import bo.SuperBO;
import dto.UserDTO;

public interface UserBO extends SuperBO {

    boolean checkUser(String name, String password, String password1);

    boolean saveUser(UserDTO userDTO);

    String generateNextUserId();

}
