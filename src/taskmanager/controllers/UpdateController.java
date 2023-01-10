
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
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import taskmanager.context.Context;
import static taskmanager.context.Context.current_task_id;
import static taskmanager.context.Context.logged_in_user_id;
import taskmanager.models.EmployeeModel;
import taskmanager.models.TaskModel;
import taskmanager.utils.AddEmployeeToTask;
import taskmanager.utils.DBConnection;


/**
 *
 * @author Sreehari Rajan
 */
public class UpdateController extends Context implements Initializable {
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
        private TextField task_id;
        @FXML
        private TextField task_name;
        @FXML
        private DatePicker task_last_date;
        @FXML
        private TextArea task_desc;
        @FXML
        private Label message_label;
                  
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
             task_table.setRowFactory(tv -> {
                TableRow<TaskModel> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    TableRow<TaskModel> row_ = row;
                    if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                        TaskModel rowData = row_.getItem();
                        current_task_id = rowData.getId();
                        if (current_task_id!=null){
                            try{
                                 Parent root = FXMLLoader.load((getClass().getResource("../resources/views/update_1.fxml")));
                                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                                scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            }catch(Exception e)
                                {
                                  System.out.println(e.getLocalizedMessage());
                                }
           
                           
                        }
                        System.out.println("Double click on: "+rowData.getName());
                    }
                });
                return row ;
            });

            
            DBConnection connect=null;
                PreparedStatement stmt=null;
                Connection con=null;
            try
            {
                
                connect = new DBConnection();
                con = connect.getConnection();
                String sql;
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
        }else{
            if (location.toString().contains("update_1")){
                task_id.setText(current_task_id);
                task_id.setEditable(false);
                
                DBConnection connect=null;
                PreparedStatement stmt=null;
                Connection con=null;
            try
            {
                
                connect = new DBConnection();
                con = connect.getConnection();
                String sql;
                System.out.println(logged_in_user_id+current_task_id);

                sql = "select tasks.name,tasks.description,tasks.last_date from tasks where tasks.id=?";

                stmt = con.prepareStatement(sql);
                stmt.setString(1, current_task_id);
                
                ResultSet rs = stmt.executeQuery();
                
                 while (rs.next()) {
                    task_name.setText(rs.getString(1));
                    task_desc.setText(rs.getString(2));
                    task_last_date.setValue(rs.getDate(3).toLocalDate());                    
                    
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
            }
        }
    }
       
       public void backInUpdate_0(ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load((getClass().getResource("../resources/views/viewtask_1.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       }
       public void finalUpdate(ActionEvent event) throws IOException,SQLException{
           
            DBConnection connect=null;
                PreparedStatement stmt=null;
                Connection con=null;
                if (task_id.getText()!=null && task_name.getText()!=null && task_desc.getText()!=null && task_last_date.getValue()!=null && logged_in_user_id!=null){
                    try
                    {
                
                    
                
                    current_task_id = task_id.getText();
                    System.out.println(current_task_id);
                    connect = new DBConnection();
                    con = connect.getConnection();
                    String sql = "update tasks set name=?,description=?,last_date=? where id=?";
                    stmt = con.prepareStatement(sql);
                    stmt.setString(1, task_name.getText());
                    stmt.setString(2, task_desc.getText());
                    stmt.setString(3, task_last_date.getValue().toString());
                    stmt.setString(4, task_id.getText());

                    stmt.executeUpdate();

                    Parent root = FXMLLoader.load((getClass().getResource("../resources/views/viewtask_1.fxml")));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                
               
            }
            catch(Exception e)
            {
              System.out.println(e.getLocalizedMessage());
            }
            finally {
              connect.closeConnection(con, stmt);
            }
            }else{
                    message_label.setText("All fields are mandatory.");
                }
                
       }
       public void backInUpdate_1(ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load((getClass().getResource("../resources/views/update_0.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       }

}