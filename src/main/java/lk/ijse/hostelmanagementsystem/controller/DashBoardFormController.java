package lk.ijse.hostelmanagementsystem.controller;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
/*import javafx.scene.layout.Pane;*/
import javafx.stage.Stage;

import java.io.IOException;
/*import java.util.Objects;*/

public class DashBoardFormController {
    public JFXButton btnHome;
    public JFXButton btnManageStudents;
    public JFXButton btnManageRooms;
    public JFXButton btnReserveRooms;
    public JFXButton btnDetails;
    public JFXButton btnRemaining;
    public AnchorPane dashBoardContext;
    public JFXButton btnLogout;
    public AnchorPane dashBoardMainContext;

    private void setUi(String location) throws IOException{
        dashBoardContext.getChildren().clear();
        dashBoardContext.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/"+ location +".fxml")));
    }
    public void btnLogoutOnAction() throws IOException {
        ((Stage)dashBoardMainContext.getScene().getWindow()).setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"))));
    }

    public void btnRemainingOnAction() throws IOException {
        setUi("RemainingKeyMoneyForm");
        new FadeIn(dashBoardContext).play();
          }

    public void btnDetailsOnAction() throws IOException {
        setUi("RoomReservationDetailsForm");
        new FadeIn(dashBoardContext).play();
    }

    public void btnReserveRoomsOnAction() throws IOException {
        setUi("ReserveRoomsForm");
        new FadeIn(dashBoardContext).play();
    }

    public void btnManageRoomsOnAction() throws IOException {
        setUi("ManageRoomForm");
        new FadeIn(dashBoardContext).play();
    }

    public void btnManageStudentsOnAction() throws IOException {
        setUi("ManageStudentsForm");
        new FadeIn(dashBoardContext).play();
    }

    public void btnHomeOnAction() throws IOException {
        setUi("HomeForm");
        new FadeIn(dashBoardContext).play();
    }
}
