package controllers;

import bo.BOFactory;
import bo.custom.UserBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dto.UserDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

public class registerFormController implements Initializable {

    @FXML
    private JFXTextField userAddress;
    @FXML
    private JFXTextField userId;

    @FXML
    private JFXTextField userNameId;

    @FXML
    private JFXTextField emailId;

    @FXML
    private JFXPasswordField passwordId;

    @FXML
    private JFXTextField passwordHintId;

    @FXML
    private JFXButton btnRegister;

    UserBO userBO= (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    @FXML
    void userAddressOnAction(ActionEvent event) {

    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        String id = userId.getText();
        String name = userNameId.getText();
        String address = userAddress.getText();
        String email = emailId.getText();
        String password=passwordId.getText();
        String hint= passwordHintId.getText();

        UserDTO userDTO=new UserDTO(id,name,address ,email,password,hint);
        boolean isSaved= userBO.saveUser(userDTO);

        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,"Registation Successful").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"User not Saved").show();
        }
    }

    @FXML
    void emailOnAction(ActionEvent event) {

    }

    @FXML
    void passwordHintOnAction(ActionEvent event) {

    }

    @FXML
    void passwordOnAction(ActionEvent event) {

    }

    @FXML
    void userIdOnAction(ActionEvent event) {

    }

    @FXML
    void userNameOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateId();
    }

    private void generateId() {
        String nextId=userBO.generateNextUserId();
        userId.setText(nextId);
    }
}
