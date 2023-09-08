package controllers;

import bo.BOFactory;
import bo.custom.RoomBO;
import com.jfoenix.controls.JFXButton;
import dto.RoomDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tm.RoomTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoomFormController implements Initializable {
    @FXML
    private Label lblMain;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TextField txtRoomId;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtKeyMoney;

    @FXML
    private TextField txtQty;

    @FXML
    private TableView<RoomTM> tblRoom;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colKeyMoney;

    @FXML
    private TableColumn<?, ?> colQty;


    RoomBO roomBO= (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        RoomTM selectedItem = tblRoom.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            boolean isDeleted = roomBO.deleteRoom(selectedItem.getRoomId());
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Room deleted!...").show();
                getAllRoom();
            } else {
                new Alert(Alert.AlertType.ERROR, "Room not deleted   !...").show();
            }
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        RoomDTO roomDTO=new RoomDTO();
        roomDTO.setRoomId(txtRoomId.getText());
        roomDTO.setRoomType(txtType.getText());
        roomDTO.setKeyMoney(Double.valueOf(txtKeyMoney.getText()));
        roomDTO.setQty(txtQty.getLength());
        if (btnSave.getText().equals("Save")){
            boolean isSaved = roomBO.saveRoom(roomDTO);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"room saved!").show();
//                getAll();
                clearAll();
            }else {
                new Alert(Alert.AlertType.ERROR,"room not saved!").show();
            }
        }else if (btnSave.getText().equals("Update")){
            boolean isUpdate = roomBO.updateRooms(roomDTO);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Room Updated!").show();
                getAllRoom();
                clearAll();
            }else {
                new Alert(Alert.AlertType.ERROR,"Room not Updated!").show();
            }
        }
    }

    private void clearAll() {
        txtRoomId.clear();
        txtType.clear();
        txtKeyMoney.clear();
        txtQty.clear();
    }

    private void getAllRoom() {
        ObservableList<RoomTM> observableList=FXCollections.observableArrayList();
        List<RoomDTO> roomDTOList =roomBO.getAllRoom();
        for(RoomDTO roomDTO:roomDTOList){
            observableList.add(new RoomTM(roomDTO.getRoomId(),roomDTO.getRoomType(),roomDTO.getKeyMoney(),roomDTO.getQty()));
        }
        tblRoom.setItems(observableList);
    }


    @FXML
    void txtKeyMoneyOnAction(ActionEvent event) {
        txtQty.requestFocus();
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {

    }

    @FXML
    void txtRoomIdOnAction(ActionEvent event) {
        txtType.requestFocus();
    }

    @FXML
    void txtRoomTypeOnAction(ActionEvent event) {
        txtKeyMoney.requestFocus();
    }

    @FXML
    void tblRoomOnMouseClicked(MouseEvent event) {
        RoomTM selectedItem = (RoomTM) tblRoom.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem != null) {
                btnDelete.setDisable(false);
                txtRoomId.setText(selectedItem.getRoomId());
                txtType.setText(selectedItem.getRoomType());
                txtKeyMoney.setText(String.valueOf(selectedItem.getKeyMoney()));
                txtQty.setText(Integer.toString(selectedItem.getQty()));
                btnSave.setText("Update");
                btnSave.setStyle("-fx-background-color: #1A5D1A");
            }

        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllRoom();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }


}
