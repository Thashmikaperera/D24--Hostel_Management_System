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
import javafx.scene.input.MouseEvent;
import tm.RoomTM;
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
        UserTM selectedItem = tblUser.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            boolean isDeleted = userBO.deleteUser(selectedItem.getUserId());
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "User deleted!...").show();
                getAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "User not deleted   !...").show();
            }
        }
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
        }else if (btnSave.getText().equals("Update")){
            boolean isUpdate = userBO.updateUser(userDTO);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Room Updated!").show();
                getAll();
                clearAll();
            }else {
                new Alert(Alert.AlertType.ERROR,"Room not Updated!").show();
            }
        }
    }

    private void clearAll() {
        txtUserId.clear();
        txtUserName.clear();
        txtPassword.clear();
        txtHint.clear();
        txtEmail.clear();
        txtAddress.clear();
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

    public void tblUserOnMouseClicked(MouseEvent mouseEvent) {
        UserTM selectedItem = (UserTM) tblUser.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem != null) {
                btnDelete.setDisable(false);
                txtUserId.setText(selectedItem.getUserId());
                txtUserName.setText(selectedItem.getUserName());
                txtEmail.setText(selectedItem.getUserEmail());
                txtAddress.setText(selectedItem.getUserAddress());
                txtPassword.setText(selectedItem.getUserPassword());
                txtHint.setText(selectedItem.getUserPasswordHint());
                btnSave.setText("Update");
                btnSave.setStyle("-fx-background-color: #1A5D1A");
            }

        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
