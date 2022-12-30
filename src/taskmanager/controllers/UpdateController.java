
package taskmanager.controllers;

/**
 *
 * @author Sreehari Rajan
 */
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;


/**
 *
 * @author Sreehari Rajan
 */
public class UpdateController {
       private Stage stage;
       private Scene scene;
       private Parent root;
       
       public void cancelInUpdate(ActionEvent event) throws IOException{
           //button name is baack in viewtask page1
            Parent root = FXMLLoader.load((getClass().getResource("../resources/views/Menu.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       }
       public void modifyInUpdate(ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load((getClass().getResource("../resources/views/update_1.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       }
       public void back1InUpdate(ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load((getClass().getResource("../resources/views/update_0.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       }
       public void nextInUpdate(ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load((getClass().getResource("../resources/views/update_2.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       }
       public void back2InUpdate(ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load((getClass().getResource("../resources/views/update_1.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       }
       public void finishUpdate(ActionEvent event) throws IOException{
           //button name is Add  task
            Parent root = FXMLLoader.load((getClass().getResource("../resources/views/Menu.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       }
}