package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import lk.ijse.hostelmanagementsystem.dto.custom.RoomDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.RoomTypeDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentRoomDTO;
/*import lk.ijse.hostelmanagementsystem.entity.custom.StudentRoom;*/
import lk.ijse.hostelmanagementsystem.service.custom.RoomService;
import lk.ijse.hostelmanagementsystem.service.custom.RoomTypeService;
import lk.ijse.hostelmanagementsystem.service.custom.StudentService;
import lk.ijse.hostelmanagementsystem.service.custom.TransactionalService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.RoomServiceImpl;
import lk.ijse.hostelmanagementsystem.service.custom.impl.RoomTypeServiceImpl;
import lk.ijse.hostelmanagementsystem.service.custom.impl.StudentServiceImpl;
import lk.ijse.hostelmanagementsystem.service.custom.impl.TransactionalServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ReserveRoomsFormController {
    public JFXTextField txtReservationId;
    public JFXComboBox<StudentDTO> cbStudent;
    public JFXTextField txtStudentName;
    public JFXComboBox<RoomTypeDTO> cbRoomType;
    public JFXComboBox<RoomDTO> cbRoom;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtPayingAmount;

    public TableView table;
    public JFXTextField txtContact;
    public DatePicker calDateFrom;
    public DatePicker calDateTo;
    public TableColumn colStudentId;
    public TableColumn colRoomTypeId;
    public TableColumn colRoom;
    public TableColumn colKeyMoney;
    public TableColumn colPayingAmount;
    public TableColumn colDateFrom;
    public TableColumn colDateTo;
    private final StudentService studentService = new StudentServiceImpl();
    private final RoomTypeService roomTypeService = new RoomTypeServiceImpl();
    private final RoomService roomService = new RoomServiceImpl();
    public AnchorPane mainPane;
    public AnchorPane pane1;
    public AnchorPane pane2;
    public AnchorPane pane3;

    public AnchorPane dashBoardContext;


    public void initialize(){
        setConverters();
        setStudentData();
        setRoomTypeData();
    }




    public void setConverters(){
        cbStudent.setConverter(new StringConverter<StudentDTO>() {
            @Override
            public String toString(StudentDTO student) {
                if(student!=null)return student.getId();
                return null;
            }

            @Override
            public StudentDTO fromString(String string) {
                return null;
            }
        });
        cbRoom.setConverter(new StringConverter<RoomDTO>() {
            @Override
            public String toString(RoomDTO room) {
                if(room!=null)return room.getId();
                return null;
            }

            @Override
            public RoomDTO fromString(String string) {
                return null;
            }
        });

        cbRoomType.setConverter(new StringConverter<RoomTypeDTO>() {
            @Override
            public String toString(RoomTypeDTO roomType) {
                if(roomType!=null)return roomType.getId();
                return null;
            }

            @Override
            public RoomTypeDTO fromString(String string) {
                return null;
            }
        });

    }

    public void setStudentData(){
        List<StudentDTO> all = studentService.getAll();
        cbStudent.setItems(FXCollections.observableArrayList(all));
    }

    public void setRoomTypeData(){
        cbRoomType.setItems(FXCollections.observableArrayList(roomTypeService.getAll()));
    }

    public void setRoomData(RoomTypeDTO type){
        System.out.println(type);
        List<RoomDTO> availableRooms = roomService.getAvailableRooms(type);
        System.out.println(availableRooms);
        cbRoom.setItems(FXCollections.observableArrayList(availableRooms));
    }

    public void cmbStudentOnAction(ActionEvent actionEvent) {
        StudentDTO st = cbStudent.getSelectionModel().getSelectedItem();
        if (st!=null){
            txtStudentName.setText(st.getName());
            txtContact.setText(st.getContact());
        }
    }

    public void cbRoomTypeIdOnAction(ActionEvent actionEvent) {
        RoomTypeDTO typ = cbRoomType.getSelectionModel().getSelectedItem();
        if(typ!=null){
            setRoomData(typ);
            txtKeyMoney.setText(String.valueOf(typ.getPrice()));
        }
    }

    public void cmbRoomOnAction(ActionEvent actionEvent) {
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String reservationId = txtReservationId.getText();
        StudentDTO student = cbStudent.getSelectionModel().getSelectedItem();
        RoomDTO room = cbRoom.getSelectionModel().getSelectedItem();
        double payment = Double.parseDouble(txtPayingAmount.getText());

        room.setAvailability("Not Available");

        StudentRoomDTO studentRoom = new StudentRoomDTO(reservationId,payment,
                LocalDate.now(),calDateFrom.getValue(),calDateTo.getValue()
                ,student,room);

        TransactionalService transactionalService = new TransactionalServiceImpl();
        boolean isAdded = transactionalService.reserveRoom(studentRoom);
        if(isAdded){
            new Alert(Alert.AlertType.INFORMATION,"Data Added").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Failed").show();
        }
    }

    public void studentRegistatinOnAction(ActionEvent actionEvent) throws IOException {
        dashBoardContext.getChildren().clear();
        dashBoardContext.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/ManageStudentsForm.fxml")));

    }
}
