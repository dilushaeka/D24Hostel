package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.service.custom.StudentService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.StudentServiceImpl;

import java.time.LocalDate;

public class AddStudentFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public JFXButton btnSaveStudents;
    public JFXTextField txtCity;
    public JFXTextField txtGmail;

    private StudentService studentService;

    public void initialize(){
        studentService=new StudentServiceImpl();
    }

    public void btnSaveStudentsOnAction(ActionEvent actionEvent) {
        StudentDTO studentDTO = collectData();
        StudentDTO save = studentService.save(studentDTO);
        if(save!=null){
            new Alert(Alert.AlertType.INFORMATION,"Student Added Success").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Student Adding Failed").show();
        }
    }

    public StudentDTO collectData(){
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContactNo.getText();
        String city = txtCity.getText();
        String gmail = txtGmail.getText();

        StudentDTO student = new StudentDTO(id,name,address,city,contact,gmail,
                null);
        return student;
    }
}
