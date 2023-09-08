package controllers;

import bo.BOFactory;
import bo.custom.UserBO;
import com.jfoenix.controls.JFXButton;
import dto.UserDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tm.UserTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {
    @FXML
    private Label lblMain;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtHint;

    @FXML
    private TableView<UserTM> tblUser;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colHint;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        
    }

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(txtUserId.getText());
        userDTO.setUserName(txtUserName.getText());
        userDTO.setAddress(txtAddress.getText());
        userDTO.setUserEmail(txtEmail.getText());
        userDTO.setUserPassword(txtPassword.getText());
        userDTO.setUserPasswordHint(txtHint.getText());

        if (btnSave.getText().equals("Save")){
            boolean isSaved = userBO.saveUser(userDTO);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"User saved Successfully!...").show();
                getAll();
            }else {
                new Alert(Alert.AlertType.ERROR, "User not saved!...").show();
            }
        }
    }

    private void getAll() {
        ObservableList<UserTM> observableList = FXCollections.observableArrayList();
        List<UserDTO> userDTOList = userBO.getAllUsers();
        for (UserDTO userDTO : userDTOList) {
            observableList.add(new UserTM(
                            userDTO.getUserId(),
                            userDTO.getUserName(),
                            userDTO.getAddress(),
                            userDTO.getUserEmail(),
                            userDTO.getUserPassword(),
                            userDTO.getUserPasswordHint()
                    )
            );
        }
        tblUser.setItems(observableList);
    }

    @FXML
    void txtAddressOnAction(ActionEvent event) {

    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {

    }

    @FXML
    void txtHintOnAction(ActionEvent event) {

    }

    @FXML
    void txtUserIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAll();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("userAddress"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("userPassword"));
        colHint.setCellValueFactory(new PropertyValueFactory<>("userPasswordHint"));

    }
}
