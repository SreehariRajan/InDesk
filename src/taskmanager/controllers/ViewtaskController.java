
package taskmanager.controllers;

/**
 *
 * @author Sreehari Rajan
 */
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
//import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import taskmanager.context.Context;
import static taskmanager.context.Context.current_task_id;
import static taskmanager.context.Context.logged_in_user_level;
import taskmanager.models.EmployeeModel;
import taskmanager.models.TaskModel;
import taskmanager.utils.AddEmployeeToTask;
import taskmanager.utils.DBConnection;
//import javafx.scene.control.CheckBox;


/**
 *
 * @author Sreehari Rajan
 */
public class ViewtaskController extends Context implements Initializable  {
       private Stage stage;
       private Scene scene;
       private Parent root;
       
       @FXML
        private TableView<TaskModel> task_table;
        @FXML
        public TableColumn<TaskModel, String> id;

        @FXML
        public TableColumn<TaskModel, String> name;   
         @FXML
        public TableColumn<TaskModel, String> desc; 
         
         @FXML
            public ToggleGroup tasktype;
         
                  
         @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (task_table!=null){
            
       
        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        desc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        
//        final ObservableList<EmployeeModel> data = FXCollections.observableArrayList(
//            
//    );

            
            DBConnection connect=null;
                PreparedStatement stmt=null;
                Connection con=null;
            try
            {
                
                connect = new DBConnection();
                con = connect.getConnection();
                String sql;
                RadioButton selectedRadioButton = (RadioButton) tasktype.getSelectedToggle();
                String toogleGroupValue = selectedRadioButton.getText();
                System.out.print(toogleGroupValue);
                if ("RECIEVED".equals(toogleGroupValue))
                    sql = "select tasks.id,tasks.name,tasks.description from tasks,task_assigned where task_assigned.employee_id=?";
                else
                    sql = "select tasks.id,tasks.name,tasks.description from tasks where tasks.created_by=?";

                stmt = con.prepareStatement(sql);
                stmt.setString(1, logged_in_user_id);
                
                ResultSet rs = stmt.executeQuery();
                
                 while (rs.next()) {
                    TaskModel task = new TaskModel(rs.getString(1), rs.getString(2), rs.getString(3));
                    
                    ObservableList<TaskModel> tasks = FXCollections.observableArrayList(task);
                    task_table.getItems().addAll(tasks);
                    
                    
                }
               
            }
            catch(Exception e)
            {
              System.out.println(e.getLocalizedMessage());
            }
            finally {
                try {
                    connect.closeConnection(con, stmt);
                } catch (SQLException ex) {
                    Logger.getLogger(AddtaskController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            
           
        //add your data to the table here.
//        task_table.setItems(data);
        }
    }
       
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