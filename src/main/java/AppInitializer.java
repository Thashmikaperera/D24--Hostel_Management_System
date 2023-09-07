import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

public class AppInitializer extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        //Parent load= FXMLLoader.load(getClass().getResource("/lk.ijse.D24HostelMangementSystem/view/loadingForm.fxml"));
        //stage.setScene(new Scene(load));
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/d24_hostel_management_systm/view/loadingForm.fxml"))));
        stage.setTitle("D24 Hostel Management System-Loading Page");
        stage.centerOnScreen();
        stage.show();
    }
}
