package crud;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Sika Dali
 */
public class CrudFX extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start (Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        Scene scene = new Scene(root);
        
        stage.setTitle("CRUD App");
        stage.setScene(scene);
        stage.show();
    }
}
