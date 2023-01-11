
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
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import taskmanager.context.Context;

import taskmanager.utils.DBConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import taskmanager.models.EmployeeModel;
import taskmanager.utils.AddEmployeeToTask;
/**
 *
 * @author Sreehari Rajan
 */
public class AddtaskController extends Context implements Initializable {
    
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
       
        @FXML
        private TableView<EmployeeModel> employees_table;
         @FXML
        private Label message_label;
        @FXML
        public TableColumn<EmployeeModel, String> id;

        @FXML
        public TableColumn<EmployeeModel, String> name;   
         @FXML
        public TableColumn<EmployeeModel, Boolean> added; 
         @FXML
        private Button next; 
        
      
        
//id.setCellValueFactory(new PropertyValueFactory<>("ID"));
//
//name.setCellValueFactory(new PropertyValueFactory<>("Name"));
//
//employees_table.getColumns().addAll(nameColumn, surnameColumn);
//
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (employees_table!=null){
            
            
            
               
            employees_table.setRowFactory(tv -> {
                TableRow<EmployeeModel> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    TableRow<EmployeeModel> row_ = row;
                    if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                        EmployeeModel rowData = row_.getItem();
                        AddEmployeeToTask addObj = new AddEmployeeToTask();
                        try {
                            boolean inserted = addObj.add(rowData,current_task_id);
                            if (inserted==true){
                                rowData.setAdded(true);
                                employees_table.getItems().set(employees_table.getSelectionModel().getSelectedIndex(), rowData);
                                
                            }else{
                                
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(AddtaskController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("Double click on: "+rowData.getName());
                    }
                });
                return row ;
            });
        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        added.setCellValueFactory(new PropertyValueFactory<>("added"));
        
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
                String sql = "select * from employees where level>"+logged_in_user_level;
                stmt = con.prepareStatement(sql);
                
                ResultSet rs = stmt.executeQuery();
                
                 while (rs.next()) {
                    EmployeeModel employee = new EmployeeModel(rs.getString(1), rs.getString(2), false);
                    
                    ObservableList<EmployeeModel> employees = FXCollections.observableArrayList(employee);
                    employees_table.getItems().addAll(employees);
                    
                    
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
//        employees_table.setItems(data);
        }else{
                DBConnection connect=null;
                PreparedStatement stmt=null;
                Connection con=null;
            try
            {
                
                connect = new DBConnection();
                con = connect.getConnection();
                                 String sql;

                  sql = "select max(level) from employees";
               stmt = con.prepareStatement(sql);
                
                ResultSet rs = stmt.executeQuery();
                Boolean is_possible_to_add;
                is_possible_to_add=true;
                 while (rs.next()) {
                    if (rs.getInt(1)==logged_in_user_level){
                           is_possible_to_add=false;
                    }
                    
                }
                 if (is_possible_to_add==false){
                     next.setVisible(false);
                     message_label.setText("Cannot add a task. No employee under you.");
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

    // add your data here from any source 
    
         
        
    
       


  
       public void cancelInAddtask(ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load((getClass().getResource("../resources/views/viewtask_1.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       }
       public void nextInAddtask(ActionEvent event) throws IOException,SQLException{
           
                if (task_id.getText()!=null && task_name.getText()!=null && task_desc.getText()!=null && task_last_date.getValue()!=null && logged_in_user_id!=null){
              DBConnection connect=null;
                PreparedStatement stmt=null;
                Connection con=null;
            try
            {
                 String sql;
                 connect = new DBConnection();
                con = connect.getConnection();

               
                 
                    current_task_id = task_id.getText();
                    System.out.println(current_task_id);
                    Boolean exist;
                    exist=false;
                    
                 
                    
                    sql = "select * from tasks where id=?";
                    stmt = con.prepareStatement(sql);
                    stmt.setString(1, task_id.getText());
                    
                       ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                           exist=true;
                           }
                    if (exist==false){
                    
                    sql = "insert into tasks values(?,?,?,?,?,?)";
                    stmt = con.prepareStatement(sql);
                    stmt.setString(1, task_id.getText());
                    stmt.setString(2, task_name.getText());
                    stmt.setString(3, task_desc.getText());
                    stmt.setString(4, task_last_date.getValue().toString());
                    stmt.setString(5, logged_in_user_id);
                    stmt.setBoolean(6, false);


                    stmt.executeUpdate();

                    Parent root = FXMLLoader.load((getClass().getResource("../resources/views/addtask_2.fxml")));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    }else{
                        message_label.setText("Task ID already exist.");     
                    }
                
               
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
       public void backInAddtask(ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load((getClass().getResource("../resources/views/addtask_1.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       }
       public void finishInAddtask(ActionEvent event) throws IOException{
           //button name is Add  task
            Parent root = FXMLLoader.load((getClass().getResource("../resources/views/viewtask_1.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       }
       
}