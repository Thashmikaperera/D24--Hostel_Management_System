package controllers;

import bo.BOFactory;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tm.StudentTM;


import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class StudentFormController implements Initializable {
    @FXML
    private Label lblMain;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TextField txtSId;

    @FXML
    private TextField txtSName;

    @FXML
    private TextField txtSAddress;

    @FXML
    private TextField txtSContact;

    @FXML
    private DatePicker txtDOB;

    @FXML
    private RadioButton maleBtn;

    @FXML
    private RadioButton femaleBtn;

    @FXML
    private TableView<StudentTM> tblStudent;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colGender;


    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            boolean isDeleted = studentBO.deleteStudent(selectedItem.getStudentId());
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student deleted!...").show();
                getAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Student not deleted   !...").show();
            }
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        StudentDTO studentDTO=new StudentDTO();
        studentDTO.setStudentId(txtSId.getText());
        studentDTO.setStudentName(txtSName.getText());
        studentDTO.setStudentAddress(txtSAddress.getText());
        studentDTO.setStudentContact(txtSContact.getText());
        studentDTO.setDate(Date.valueOf(txtDOB.getValue()));
        studentDTO.setGender(maleBtn.isSelected()? "Male": "Female");
        studentDTO.setGender(femaleBtn.isSelected()? "Female" : "Male");
        if (btnSave.getText().equals("Save")){
            boolean isSaved = studentBO.saveStudent(studentDTO);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"student saved!").show();
                getAll();
                clearAll();
            }else {
                new Alert(Alert.AlertType.ERROR,"Student not saved!").show();
            }
        }else if (btnSave.getText().equals("Update")){
            boolean isUpdate = studentBO.updateStudent(studentDTO);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Student Updated!").show();
                getAll();
                clearAll();
            }else {
                new Alert(Alert.AlertType.ERROR,"Student not Updated!").show();
            }
        }
    }

    private void clearAll() {
        txtSId.clear();
        txtSName.clear();
        txtSAddress.clear();
        txtSContact.clear();
        txtDOB.setValue(null);
        maleBtn.setSelected(false);
        femaleBtn.setSelected(false);
    }

    @FXML
    void txtSAddress(ActionEvent event) {
        txtSContact.requestFocus();
    }

    @FXML
    void txtSContactOnAction(ActionEvent event) {
        txtDOB.requestFocus();
    }

    @FXML
    void txtSIdOnAction(ActionEvent event) {
        txtSName.requestFocus();
    }

    @FXML
    void txtSNameOnAction(ActionEvent event) {
        txtSAddress.requestFocus();
    }

    @FXML
    void tblStudentOnMouseClicked(MouseEvent event) {
        StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem != null) {
                btnDelete.setDisable(false);
                txtSId.setText(selectedItem.getStudentId());
                txtSName.setText(selectedItem.getStudentName());
                txtSAddress.setText(selectedItem.getStudentAddress());
                if (selectedItem.getGender().equals("Male")) {
                    maleBtn.setSelected(true);
                } else {
                    femaleBtn.setSelected(true);
                }
                txtSContact.setText(selectedItem.getStudentContact());
                txtDOB.setValue(selectedItem.getDate().toLocalDate());
                btnSave.setText("Update");
                btnSave.setStyle("-fx-background-color: #1A5D1A");
            }

        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAll();
        generateNextId();

        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("studentAddress"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("studentContact"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("date"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    private void generateNextId() {
        String nextId = studentBO.generateStudentNextId();
        txtSId.setText(nextId);
    }

    private void getAll() {
        ObservableList<StudentTM> observableList = FXCollections.observableArrayList();
        List<StudentDTO> customerDTOList = studentBO.getAllStudent();
        for (StudentDTO studentDTO : customerDTOList) {
            observableList.add(new StudentTM(
                            studentDTO.getStudentId(),
                            studentDTO.getStudentName(),
                            studentDTO.getStudentAddress(),
                            studentDTO.getStudentContact(),
                            studentDTO.getDate(),
                            studentDTO.getGender()
                    )
            );
        }
        tblStudent.setItems(observableList);
    }

    public void txtDOBOnAction(ActionEvent event) {

    }
}
