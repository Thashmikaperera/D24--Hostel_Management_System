package controllers;

import bo.BOFactory;
import bo.custom.UserBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogingFormController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField userTxt;

    @FXML
    private JFXButton btnSignin;

    @FXML
    private JFXPasswordField passwordTxt;

    @FXML
    private ImageView btnHide;

    @FXML
    private ImageView btnShow;

    @FXML
    private JFXTextField txtPassword;

    UserBO userBO=(UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnSigninOnAction(ActionEvent event) throws IOException {
        String name=userTxt.getText();
        String password = passwordTxt.getText();
        String password1 = txtPassword.getText();

        boolean isValid = userBO.checkUser(name,password,password1);
        if (isValid){
            AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("/d24_hostel_management_systm/view/contentForm.fxml"));
            Stage stage= new Stage();
            stage.setScene(new Scene(anchorPane));
            Stage stage1= (Stage) root.getScene().getWindow();
            stage1.close();
            stage.setTitle("D24 Hostel Management System - Dashboard");
            stage.centerOnScreen();
            stage.show();
        }else {
            new Alert(Alert.AlertType.ERROR, "User Name and Password Incorrect").show();
        }
    }

    @FXML
    void imgHideOnaAction(MouseEvent event) {
        String password=passwordTxt.getText();
        txtPassword.setText(password);
        passwordTxt.setVisible(false);
        txtPassword.setVisible(true);
        btnHide.setVisible(false);
        btnShow.setVisible(true);
    }

    @FXML
    void imgShowOnaAction(MouseEvent event) {
        String password=txtPassword.getText();
        passwordTxt.setText(password);
        txtPassword.setVisible(false);
        passwordTxt.setVisible(true);
        btnShow.setVisible(false);
        btnHide.setVisible(true);
    }

    @FXML
    void passwordTxtOnAction(ActionEvent event) {
        btnSignin.fire();
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        btnSignin.fire();
    }

    @FXML
    void userTxtOnAction(ActionEvent event) {
        passwordTxt.requestFocus();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnHide.setVisible(false);
        txtPassword.setVisible(false);
    }

    @FXML
    void signupOnMouseClicked(MouseEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/d24_hostel_management_systm/view/registerForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("D24 Hostel Management System - Register Form");
        stage.centerOnScreen();
        stage.show();
    }
}
