
package taskmanager.controllers;

/**
 *
 * @author Sreehari Rajan
 */
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
//import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
//import javafx.scene.control.CheckBox;


/**
 *
 * @author Sreehari Rajan
 */
public class ViewtaskController {
       private Stage stage;
       private Scene scene;
       private Parent root;
       
       public void cancelInViewtask(ActionEvent event) throws IOException{
           //button name is baack in viewtask page1
            Parent root = FXMLLoader.load((getClass().getResource("../resources/views/Menu.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       }
      
       public void viewInViewtask(ActionEvent event) throws IOException{
           
                
            Parent root = FXMLLoader.load((getClass().getResource("../resources/views/viewtask_2.fxml")));
           
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       }
       public void backToViewtask(ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load((getClass().getResource("../resources/views/viewtask_1.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       }
}