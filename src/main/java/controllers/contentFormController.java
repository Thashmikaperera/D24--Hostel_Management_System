package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class contentFormController implements Initializable {
    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnStudent;

    @FXML
    private JFXButton btnRoom;

    @FXML
    private JFXButton btnReservation;

    @FXML
    private JFXButton btnUser;

    @FXML
    private JFXButton btnExit;

    @FXML
    private AnchorPane root;

    @FXML
    void btnDashboadOnAction(ActionEvent event) throws IOException {
        AnchorPane load= FXMLLoader.load(getClass().getResource("/d24_hostel_management_systm/view/dashboardForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    void btnExitOnAction(ActionEvent event) {

    }

    @FXML
    void btnReservationOnAtion(ActionEvent event) throws IOException {
        AnchorPane load= FXMLLoader.load(getClass().getResource("/d24_hostel_management_systm/view/reservationForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    void btnRoomOnAction(ActionEvent event) throws IOException {
        AnchorPane load= FXMLLoader.load(getClass().getResource("/d24_hostel_management_systm/view/roomForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {
        AnchorPane load= FXMLLoader.load(getClass().getResource("/d24_hostel_management_systm/view/studentForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    void btnUserOnAction(ActionEvent event) throws IOException {
        AnchorPane load= FXMLLoader.load(getClass().getResource("/d24_hostel_management_systm/view/userForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadDashboardUi();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDashboardUi() throws IOException {
        AnchorPane load= FXMLLoader.load(getClass().getResource("/d24_hostel_management_systm/view/dashboardForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }
}
