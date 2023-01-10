
package taskmanager.controllers;

/**
 *
 * @author Sreehari Rajan
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import taskmanager.context.Context;


/**
 *
 * @author Sreehari Rajan
 */
public class SideBarController extends Context implements Initializable{
       private Stage stage;
       private Scene scene;
       private Parent root;
       
       @FXML
        private Label dashboard_user_id;
        @FXML
        public Label dashboard_user_name;
        
        @Override
        public void initialize(URL location, ResourceBundle resources) {
            if (logged_in_user_id!=null){
                dashboard_user_id.setText("ID: "+logged_in_user_id);
                dashboard_user_name.setText(logged_in_user_name);
            }
        }

       
       public void gotoAddtask(ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load((getClass().getResource("../resources/views/addtask_1.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       }
       public void gotoViewtask(ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load((getClass().getResource("../resources/views/viewtask_1.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       }
       public void gotoUpdate(ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load((getClass().getResource("../resources/views/update_0.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       }
       
}