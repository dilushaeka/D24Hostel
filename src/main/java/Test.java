import lk.ijse.hostelmanagementsystem.entity.custom.RoomType;
import lk.ijse.hostelmanagementsystem.entity.custom.StudentRoom;
import lk.ijse.hostelmanagementsystem.repo.custom.RoomRepo;
import lk.ijse.hostelmanagementsystem.repo.custom.impl.RoomRepoImpl;
import lk.ijse.hostelmanagementsystem.repo.custom.impl.StudentRoomRepoImpl;
import lk.ijse.hostelmanagementsystem.util.FactoryConfiguration;
import org.hibernate.Session;

import java.util.List;

public class Test {
    public static void main(String[] args) {
       /* RoomRepoImpl repo = new RoomRepoImpl();
        Session session = FactoryConfiguration.getInstance().getSession();
        RoomType roomType = new RoomType("RT-001","Type 1",2000,null);
        repo.getAvailableRooms(roomType,session);*/

        StudentRoomRepoImpl repo = new StudentRoomRepoImpl();
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            List<StudentRoom> notAvailableRooms = repo.getRemainingKeyMoneyList(session);

            System.out.println(notAvailableRooms);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
