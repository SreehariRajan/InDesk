
package taskmanager.controllers;

/**
 *
 * @author Sreehari Rajan
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import taskmanager.utils.DBConnection;


/**
 *
 * @author Sreehari Rajan
 */
public class AddtaskController {
    
        @FXML
        private TextField task_id;
        @FXML
        private TextField task_name;
        @FXML
        private DatePicker task_last_date;
        @FXML
        private TextArea task_desc;
        
       private Stage stage;
       private Scene scene;
       private Parent root;
       
       
       
       public void cancelInAddtask(ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load((getClass().getResource("../resources/views/Menu.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       }
       public void nextInAddtask(ActionEvent event) throws IOException,SQLException{
           
           
              DBConnection connect=null;
                PreparedStatement stmt=null;
                Connection con=null;
            try
            {
              
                connect = new DBConnection();
                con = connect.getConnection();
                String sql = "insert into tasks values(?,?,?,?)";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, task_id.getText());
                stmt.setString(2, task_name.getText());
                stmt.setString(3, task_desc.getText());
                stmt.setString(4, task_last_date.getValue().toString());

                stmt.executeUpdate();
               
            }
            catch(Exception e)
            {
              System.out.println(e.getLocalizedMessage());
            }
            finally {
              connect.closeConnection(con, stmt);
            }

            Parent root = FXMLLoader.load((getClass().getResource("../resources/views/addtask_2.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       }
       public void backInAddtask(ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load((getClass().getResource("../resources/views/addtask_1.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       }
       public void finishInAddtask(ActionEvent event) throws IOException{
           //button name is Add  task
            Parent root = FXMLLoader.load((getClass().getResource("../resources/views/Menu.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       }
       
}