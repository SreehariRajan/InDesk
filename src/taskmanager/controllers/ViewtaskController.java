
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import taskmanager.context.Context;
import static taskmanager.context.Context.current_task_id;
import static taskmanager.context.Context.logged_in_user_level;
import taskmanager.models.EmployeeModel;
import taskmanager.models.TaskModel;
import taskmanager.models.TaskStatusModel;
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
       
       public Boolean is_recieved=true;
       
       
       
       @FXML
        private TableView<TaskModel> task_table;
        @FXML
        public TableColumn<TaskModel, String> id;

        @FXML
        public TableColumn<TaskModel, String> name;   
         @FXML
        public TableColumn<TaskModel, String> desc; 
         
         @FXML
        private TableView<TaskStatusModel> task_status_table;
        @FXML
        public TableColumn<TaskStatusModel, String> empid;

        @FXML
        public TableColumn<TaskStatusModel, Boolean> empstatus;   
         @FXML
        public TableColumn<TaskStatusModel, String> empremarks; 
         
         
         
         @FXML
            public ToggleGroup tasktype;
         
        @FXML
        private Label task_id;
        @FXML
        private Label task_name;
        @FXML
        private Label task_last_date;
        @FXML
        private Label task_desc;
        
        @FXML
        private TextField remarks;
         
                  
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
                                Parent root;
                                 if (is_recieved){
                                     root = FXMLLoader.load((getClass().getResource("../resources/views/viewtask_2_RECIEVER.fxml")));
                                 }else{
                                     root = FXMLLoader.load((getClass().getResource("../resources/views/viewtask_2_SENDER.fxml")));
                                 }
                                 
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


        
        DBConnection connect_=null;
                PreparedStatement stmt_=null;
                Connection con_=null;
            try
            {
                
                connect_ = new DBConnection();
                con_ = connect_.getConnection();
                String sql;
                RadioButton selectedRadioButton = (RadioButton) tasktype.getSelectedToggle();
                String toogleGroupValue = selectedRadioButton.getText();
                System.out.print(toogleGroupValue);
                if ("RECIEVED".equals(toogleGroupValue))
                    sql = "select tasks.id,tasks.name,tasks.description from tasks,task_assigned where task_assigned.task_id=tasks.id and task_assigned.employee_id=?";
                else
                    sql = "select tasks.id,tasks.name,tasks.description from tasks where tasks.created_by=?";

                stmt_ = con_.prepareStatement(sql);
                stmt_.setString(1, logged_in_user_id);
                
                ResultSet rs = stmt_.executeQuery();
                
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
                    connect_.closeConnection(con_, stmt_);
                } catch (SQLException ex) {
                    Logger.getLogger(AddtaskController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            



        tasktype.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) -> {
                RadioButton rb = (RadioButton)tasktype.getSelectedToggle();
                DBConnection connect=null;
                PreparedStatement stmt=null;
                Connection con=null;
                
            try
            {
                
                connect = new DBConnection();
                con = connect.getConnection();
                String sql;
                
                
                if (rb != null) {
                    String type_ = rb.getText();
                    // change the label
                    System.out.print(type_);
                
                if ("RECIEVED".equals(type_)){
                    is_recieved=true;
                    System.out.println("yes");
                    sql = "select tasks.id,tasks.name,tasks.description from tasks,task_assigned where task_assigned.task_id=tasks.id and task_assigned.employee_id=?";

                }
                else{
                    is_recieved=false;
                    sql = "select tasks.id,tasks.name,tasks.description from tasks where tasks.created_by=?";

                }
                }else{
                    is_recieved=true;
                    sql = "select tasks.id,tasks.name,tasks.description from tasks,task_assigned where task_assigned.task_id=tasks.id and task_assigned.employee_id=?";

                }
                stmt = con.prepareStatement(sql);
                stmt.setString(1, logged_in_user_id);
                
                ResultSet rs = stmt.executeQuery();
                ObservableList<TaskModel> tasks = FXCollections.observableArrayList();
                 while (rs.next()) {
                    TaskModel task = new TaskModel(rs.getString(1), rs.getString(2), rs.getString(3));
                    tasks.add(task);   
                }
                task_table.setItems(tasks);
               
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
     
        });

            
            
            
            
            
           
        //add your data to the table here.
//        task_table.setItems(data);
        }else{
             task_id.setText(current_task_id);                
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
                    task_last_date.setText(rs.getDate(3).toString());                    
                    
                }
                 
                 sql="";
                 
                 if (location.toString().contains("SENDER")){
                     if (task_status_table!=null){
                         System.out.print("Inside");
                     
                     empid.setCellValueFactory(new PropertyValueFactory<>("empid"));
                     empstatus.setCellValueFactory(new PropertyValueFactory<>("empstatus"));

                     empremarks.setCellValueFactory(new PropertyValueFactory<>("empremarks"));
                    
                    sql = "select employee_id,status,remarks from task_assigned where task_id=?";

                stmt = con.prepareStatement(sql);
                stmt.setString(1, current_task_id);
                
                ResultSet rs_ = stmt.executeQuery();
                
                 while (rs_.next()) {
                     System.out.print(rs_.getString(3)+"hello");
                    TaskStatusModel task_status = new TaskStatusModel(rs_.getString(1), rs_.getString(3),rs_.getBoolean(2));
                    
                    ObservableList<TaskStatusModel> tasks_status = FXCollections.observableArrayList(task_status);
                    task_status_table.getItems().addAll(tasks_status);
                    
                 }
                }
                
            }else if (location.toString().contains("RECIEVER")){
                
                
            
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
    
 
       
       public void cancelInViewtask(ActionEvent event) throws IOException{
           //button name is baack in viewtask page1
            Parent root = FXMLLoader.load((getClass().getResource("../resources/views/viewtask_1.fxml")));
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
       
        public void submitInViewtask(ActionEvent event) throws IOException, SQLException{
            
            DBConnection connect=null;
                PreparedStatement stmt=null;
                Connection con=null;
            try
            {
                
                current_task_id = task_id.getText();
                System.out.println(current_task_id);
                connect = new DBConnection();
                con = connect.getConnection();
                String sql = "update task_assigned set remarks=?,status=true where task_id=? and employee_id=?";

                stmt = con.prepareStatement(sql);
                stmt.setString(1, remarks.getText());
                stmt.setString(2, current_task_id);
                stmt.setString(3, logged_in_user_id);

                
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
       }
}